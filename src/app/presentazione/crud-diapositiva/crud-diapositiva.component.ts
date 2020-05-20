import { Component, OnInit, Input, Inject, Output, EventEmitter, ViewChild, TemplateRef, ViewContainerRef } from '@angular/core';
import { DiapositivaService } from 'src/service/diapositiva.service';
import { PresentazioneDTO } from 'src/dto/presentazione.dto';
import { faPlus, faMinus, faCheck, faTimes, faBars } from '@fortawesome/free-solid-svg-icons';
import { DiapositivaDTO } from 'src/dto/diapositiva.dto';
import { Colore } from 'src/dto/colore.obj';
import {CdkDragDrop, moveItemInArray} from '@angular/cdk/drag-drop';
import { DiapoFullDTO } from 'src/dto/diapoFull.dto';
import {MatSnackBar} from '@angular/material/snack-bar';
import {MatDialog} from '@angular/material/dialog';
import pdfMake from "pdfmake/build/pdfmake";
import pdfFonts from "pdfmake/build/vfs_fonts";
import { TestoService } from 'src/service/testo.service';
import { DiapoGraphService } from 'src/service/diapoGraph.service';
pdfMake.vfs = pdfFonts.pdfMake.vfs;

@Component({
  selector: 'app-crud-diapositiva',
  templateUrl: './crud-diapositiva.component.html',
  styleUrls: ['./crud-diapositiva.component.css']
})
export class CrudDiapositivaComponent implements OnInit {

  @Input("id") idMod : PresentazioneDTO;
  @Output("back") goBack = new EventEmitter();
  @ViewChild("dialogExport") modal : TemplateRef<any>;

  add = faPlus;
  del = faMinus;
  ann = faTimes;
  ok = faCheck;
  btnMenu = faBars;

  listDiapo : DiapoFullDTO[] = [];
  acciaf : DiapoFullDTO;
  error : number = 0;
  toDelete : number = -1;
  deleted = false;
  selezionato = -1;
  old=0;
  ready : boolean = false;
  lavagna : boolean = false;

  wait: boolean = false;

  progress=0;
  da: number = 1;
  a: number = 1;
  current:number = -1;
  toExp = false;
  dialogRef;
  docDefinition = { 
    content: [],
    pageSize: {
      width: 600,
      height: 'auto'
    },
    pageMargins: [0,0,0,0],
    info: {
      title: '',
      author: 'GraphReport',
      }
  };

  constructor(private service: DiapositivaService,private _snackBar: MatSnackBar, private dialog: MatDialog, private serviceTesto: TestoService, private serviceGrafico: DiapoGraphService) { 
  }

  ngOnInit(): void {
    this.updateList();
  }

  updateList(){
    this.service.getAllByPresentazione(this.idMod.id).subscribe( x => {
      this.listDiapo = [];
      if(x.length>0){
        Promise.all(
          x.map((y,i,l) =>{ 
            this.listDiapo.push(new DiapoFullDTO(y));
            return new Promise((ok,no) =>{
              this.serviceTesto.getAllByDiapositiva(this.listDiapo[i].diapositiva.id).subscribe(x => {
                this.listDiapo[i].testi = x;
                this.serviceGrafico.getAllByDiapositiva(this.listDiapo[i].diapositiva.id).subscribe(y => {
                  this.listDiapo[i].grafici = y;
                  return ok();
                });
              })
            });
          })
        ).then(() => {
          let sel = (this.selezionato==-1) ? 0 : this.selezionato;
          this.cambiaDiapo(sel);
          this.lavagna = true;
        });
      }else{
        this.lavagna = false;
      }
      this.ready = true;
    });
  }

  newDiapo(){
    let dto = new DiapositivaDTO(0,new Colore(255,255,255,100),this.listDiapo.length,false,"16:9",false,"Nuovo Titolo","1_1",50,new Colore(250,0,0,100),this.idMod);
    this.service.insert(dto).subscribe( x => {
      this.listDiapo.push(new DiapoFullDTO(x));
      if(this.listDiapo.length==1) this.cambiaDiapo(0);
      this.lavagna=true;
    }, () => this.error=1 );
  }

  drop(event: CdkDragDrop<DiapoFullDTO[]>) {
    moveItemInArray(this.listDiapo, event.previousIndex, event.currentIndex);
    this.listDiapo.forEach((dto,index,list) => { 
      dto.diapositiva.ordine = index;
      if(dto.diapositiva.id == this.acciaf.diapositiva.id){ 
        this.selezionato=dto.diapositiva.ordine;
        this.old = this.selezionato;
      } 
    });
  }

  toDel(){
    this.toDelete = this.listDiapo[this.selezionato].diapositiva.id;
  }

  conf(bool){
    if(bool){
      this.service.delete(this.toDelete).subscribe( () => {
        this.listDiapo.splice(this.selezionato,1);
        if(this.listDiapo.length==0){
          this.lavagna = false;
          this.selezionato = -1;
        }else{
          this.cambiaDiapo(0);
          this.deleted = true;
        }
      });
    }
    this.toDelete = -1;
  }

  registra(valore){
    if(!this.deleted){
      this.listDiapo[this.old] = JSON.parse(valore);
      this.old=this.selezionato;
    }else{ 
      this.deleted=false;
      this.old = 0; 
    }
  }

  cambiaDiapo(sel : number){
    if(sel!=this.selezionato){
      this.selezionato=sel;
      this.acciaf = this.listDiapo[sel];
    }
  }

  saveAll(){
    let snackBarRef = this._snackBar.open('Salvataggio in corso');
    Promise.all( this.listDiapo.map( x => {
      return new Promise((ok,err) => {
        this.service.update(x.diapositiva).subscribe(y => {
          this.serviceTesto.updateList(x.testi).subscribe();
          this.serviceTesto.deleteList(x.testiDel).subscribe();
          return ok('ok');
        });
      });
    })).then( x => {
      snackBarRef.dismiss();
      snackBarRef = this._snackBar.open("Salvataggio Completato!","",{duration: 3000});
    });
  }

  back(){
    this.goBack.emit();
  }

  exportSetting(){
    this.dialogRef = this.dialog.open(this.modal);
  }

  toImage(index,next){
    this.wait = true;
    if(next!=-1){
      this.current = index;
      if(index==next){
        this.docDefinition.content=[];
      }
    }
    this.toExp=true;
    this.cambiaDiapo(index-1);
  }

  toDownload(value){
    this.dialogRef.close();
    if(this.current==-1){
      this.toExp=false;
      this.da=1;
      this.a=1;
      let a = document.createElement('a');
      a.href = value;
      a.download = "diapositiva.png";
      a.click();
      this.wait=false;
      this.cambiaDiapo(0);
    }else{
      this.progress=(100/(this.a-this.da+1)*this.current);
      this.docDefinition.content.push({ 
        margin: 0,
        image: value,
        pageBreak: (this.current!=this.da) ? "before" : "",
        width: 600,
        height: (600*Number.parseInt(this.listDiapo[this.current-1].diapositiva.ratio.split(":")[1])/Number.parseInt(this.listDiapo[this.current-1].diapositiva.ratio.split(":")[0])),
        pageOrientation: 'landscape',
       });
       this.docDefinition.info.title=this.idMod.nome;
       if(this.current<this.a){
         this.cambiaDiapo(this.current++);
       }else{
        pdfMake.createPdf(this.docDefinition).download(this.idMod.nome,() => {
          this.cambiaDiapo(0);
          this.wait=false;
          this.toExp=false;
          this.da=1;
          this.a=1;
          this.current=-1;
        });
       }
    }
    
  }

}
