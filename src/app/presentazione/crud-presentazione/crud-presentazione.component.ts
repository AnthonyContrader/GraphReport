import { Component, OnInit, Output, Input, EventEmitter } from '@angular/core';
import { PresentazioneService } from 'src/service/presentazione.service';
import { PresentazioneDTO } from 'src/dto/presentazione.dto';
import { faCheck, faTimes } from '@fortawesome/free-solid-svg-icons';


@Component({
  selector: 'app-crud-presentazione',
  templateUrl: './crud-presentazione.component.html',
  styleUrls: ['./crud-presentazione.component.css']
})
export class CrudPresentazioneComponent implements OnInit {

  @Output("modify") daModificare = new EventEmitter();

  ann = faTimes;
  ok = faCheck;

  error : number = 0;
  indice : number = -1;
  toDelete : number = -1;

  presentazione: PresentazioneDTO;
  listPresentazioniCompleta: PresentazioneDTO[];
  listPresentazioniFiltrata : PresentazioneDTO[];

  constructor(private service: PresentazioneService) {
    this.presentazione = new PresentazioneDTO(0, null, null, null);
  }

  ngOnInit(): void {
    this.getPresentazioni();

  }

  getPresentazioni(){
    this.service.getAllByUser(JSON.parse(localStorage.getItem('identity') || sessionStorage.getItem('identity')).id).subscribe(presentazioni => {
      this.listPresentazioniCompleta = presentazioni;
      this.listPresentazioniFiltrata = presentazioni;
    });
  }


  newPresentation(){
    if(this.presentazione.nome!=null){
      if(this.listPresentazioniFiltrata.findIndex(x => this.presentazione.nome == x.nome)!=-1){
        this.error = 2;
      }else{
        this.presentazione.nome = this.presentazione.nome.trim();
        if(this.presentazione.nome != ''){
          this.presentazione.dataCreazione = new Date();
          this.presentazione.ultimaModifica = new Date();
          this.service.insert(this.presentazione).subscribe(
            results => {
              this.presentazione = new PresentazioneDTO(null, null, null, null);
              this.getPresentazioni();
            },
            err => {
              this.error=1;
            }
          );
        }
      }
    }
  }

  edit(){
    this.daModificare.emit(this.listPresentazioniFiltrata[this.indice]);
    this.indice=-1;
  }

  del(){
    this.toDelete=this.listPresentazioniFiltrata[this.indice].id;
  }
  conf(x){
    if(x){
      this.service.delete(this.toDelete).subscribe( 
        () => {
          this.getPresentazioni();
          this.indice=-1;
        });
    }
    this.toDelete = -1;
  }

  filtraLista(){
    this.listPresentazioniFiltrata = this.listPresentazioniCompleta.filter(x => x.nome.toLowerCase().startsWith(this.presentazione.nome.toLowerCase()) );
  }


}
