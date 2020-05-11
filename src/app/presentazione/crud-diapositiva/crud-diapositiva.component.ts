import { Component, OnInit, Input } from '@angular/core';
import { DiapositivaService } from 'src/service/diapositiva.service';
import { PresentazioneDTO } from 'src/dto/presentazione.dto';
import { faPlus, faMinus, faCheck, faTimes } from '@fortawesome/free-solid-svg-icons';
import { DiapositivaDTO } from 'src/dto/diapositiva.dto';
import { Colore } from 'src/dto/colore.obj';
import {CdkDragDrop, moveItemInArray} from '@angular/cdk/drag-drop';
import { DiapoFullDTO } from 'src/dto/diapoFull.dto';

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
  selezionato = 0;
  old=0;
  ready : boolean = false;

  constructor(private service: DiapositivaService) { 
  }

  ngOnInit(): void {
    this.updateList();
  }

  updateList(){
    this.service.getAllByPresentazione(this.idMod.id).subscribe( x => {
      x.forEach((x,i,l) => this.listDiapo.push(new DiapoFullDTO(x)));
      this.cambiaDiapo(this.selezionato);
      this.ready=true;} 
    );
  }

  newDiapo(){
    this.service.insert(new DiapositivaDTO(0,new Colore(255,255,255,100),this.listDiapo.length,false,"16:9",false,"","0_0",50,new Colore(255,255,255,100),this.idMod))
        .subscribe( () => this.updateList(), () => this.error=1 );
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
      this.service.delete(this.toDelete).subscribe( () => this.updateList() );
    }
    this.toDelete = -1;
  }

  registra(valore){
    this.listDiapo[this.old] = JSON.parse(valore);
    this.old=this.selezionato;
  }

  cambiaDiapo(sel : number){
    this.selezionato=sel;
    this.acciaf = this.listDiapo[sel];
  }

}
