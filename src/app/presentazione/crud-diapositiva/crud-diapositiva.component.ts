import { Component, OnInit, Input, Inject } from '@angular/core';
import { DiapositivaService } from 'src/service/diapositiva.service';
import { PresentazioneDTO } from 'src/dto/presentazione.dto';
import { faPlus, faMinus, faCheck, faTimes } from '@fortawesome/free-solid-svg-icons';
import { DiapositivaDTO } from 'src/dto/diapositiva.dto';
import { Colore } from 'src/dto/colore.obj';
import {CdkDragDrop, moveItemInArray} from '@angular/cdk/drag-drop';
import { DiapoFullDTO } from 'src/dto/diapoFull.dto';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-crud-diapositiva',
  templateUrl: './crud-diapositiva.component.html',
  styleUrls: ['./crud-diapositiva.component.css']
})
export class CrudDiapositivaComponent implements OnInit {

  @Input("id") idMod : PresentazioneDTO;

  add = faPlus;
  del = faMinus;
  ann = faTimes;
  ok = faCheck;

  listDiapo : DiapoFullDTO[] = [];
  acciaf : DiapoFullDTO;
  error : number = 0;
  toDelete : number = -1;
  deleted = false;
  selezionato = -1;
  old=0;
  ready : boolean = false;
  lavagna : boolean = false;

  constructor(private service: DiapositivaService,private _snackBar: MatSnackBar) { 
  }

  ngOnInit(): void {
    this.updateList();
  }

  updateList(){
    this.service.getAllByPresentazione(this.idMod.id).subscribe( x => {
      this.listDiapo = [];
      if(x.length>0){
        x.forEach((y,i,l) => this.listDiapo.push(new DiapoFullDTO(y)));
        let sel = (this.selezionato==-1) ? 0 : this.selezionato;
        this.cambiaDiapo(sel);
        this.lavagna = true;
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
          this.cambiaDiapo(this.selezionato);
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
        this.service.update(x.diapositiva).subscribe(y => {return ok('ok');});
      });
    })).then( x => {
      snackBarRef.dismiss();
      snackBarRef = this._snackBar.open("Salvataggio Completato!","",{duration: 3000});
    });
  }

}
