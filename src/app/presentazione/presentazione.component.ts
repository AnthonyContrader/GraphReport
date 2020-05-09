import { Component, OnInit, Input } from '@angular/core';
import { DiapositivaService } from 'src/service/diapositiva.service';
import { PresentazioneService } from 'src/service/presentazione.service';
import { PresentazioneDTO } from 'src/dto/presentazione.dto';


@Component({
  selector: 'app-presentazione',
  templateUrl: './presentazione.component.html',
  styleUrls: ['./presentazione.component.css']
})
export class PresentazioneComponent implements OnInit {

  stamp : boolean = true;
  daMod : PresentazioneDTO;

  constructor(private presService: PresentazioneService, private diapService: DiapositivaService) { }

  ngOnInit(): void {
  }

  update(id){
    this.daMod = id;
    this.stamp = false;
  }

}
