import { Component, OnInit, Output } from '@angular/core';
import { PresentazioneService } from 'src/service/presentazione.service';
import { PresentazioneDTO } from 'src/dto/presentazione.dto';
import { EventEmitter } from 'protractor';

@Component({
  selector: 'app-crud-presentazione',
  templateUrl: './crud-presentazione.component.html',
  styleUrls: ['./crud-presentazione.component.css']
})
export class CrudPresentazioneComponent implements OnInit {

  @Output('result') sucess =  new EventEmitter();

  presentazione: PresentazioneDTO;

  constructor(private service: PresentazioneService) {
    this.presentazione = new PresentazioneDTO(null, null, null, null);
  }

  ngOnInit(): void {
  }

  newPresentation(){
    this.presentazione.nome = this.presentazione.nome.trim();
    if(this.presentazione.nome != ''){
      this.presentazione.dataCreazione = new Date();
      this.presentazione.ultimaModifica = new Date();
      this.service.insert(this.presentazione).subscribe(
        results => {
          this.presentazione = new PresentazioneDTO(null, null, null, null);
          this.sucess.emit('true')
        },
        error => {this.sucess.emit('false') },
        () => {}
      );
    }
  }

}
