import { Component, OnInit, Input } from '@angular/core';
import { DiapositivaService } from 'src/service/diapositiva.service';
import { PresentazioneService } from 'src/service/presentazione.service';


@Component({
  selector: 'app-presentazione',
  templateUrl: './presentazione.component.html',
  styleUrls: ['./presentazione.component.css']
})
export class PresentazioneComponent implements OnInit {

  stamp : boolean = true;
  daMod : number = -1;

  constructor(private presService: PresentazioneService, private diapService: DiapositivaService) { }

  ngOnInit(): void {
  }

  update(id){
    this.daMod = id;
    this.stamp = false;
  }

}
