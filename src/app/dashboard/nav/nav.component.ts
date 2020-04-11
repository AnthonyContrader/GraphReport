import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {

  selected : string = "home";

  constructor() { }

  ngOnInit(): void {
  }

  apriqualcosa(el : string){
    this.selected = el;

    //route alla pagina

  }

}
