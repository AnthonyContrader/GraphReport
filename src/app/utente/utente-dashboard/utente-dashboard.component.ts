import { Component, OnInit } from '@angular/core';
import { UserDTO } from 'src/dto/userdto';

/**
 * Componente della dashboard admin. Nell'ngOnInit recupera
 * l'utente loggato per il messaggio di benvenuto.
 */
@Component({
  selector: 'app-utente-dashboard',
  templateUrl: './utente-dashboard.component.html',
  styleUrls: ['./utente-dashboard.component.css']
})
export class UtenteDashboardComponent implements OnInit {

  user: UserDTO;

  constructor() { }

  ngOnInit() {
    this.user = JSON.parse(localStorage.getItem('currentUser'));
  }

}
