import { Component, OnInit } from '@angular/core';
import { PresentazioneService } from 'src/service/presentazione.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  utente;

  constructor(private p : PresentazioneService) {
    this.utente= JSON.parse(localStorage.getItem('identity')) || JSON.parse(sessionStorage.getItem('identity')).login;
    p.getAll().subscribe();
   }

  ngOnInit(): void {
  }

}
