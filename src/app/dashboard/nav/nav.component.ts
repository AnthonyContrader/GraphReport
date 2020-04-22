import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/authJWT/login.service';
import { Router } from '@angular/router';
import { faCaretDown, faBars } from '@fortawesome/free-solid-svg-icons';


@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {

  nome : string;
  image : string;
  imageDefault = "assets\\imgProf\\imgDef.png";
  isAdmin : boolean;
  selected : string = this.router.url.split('/')[2];

  arrowDown = faCaretDown;
  menu = faBars;
  vnav: boolean = true;

  constructor(private router: Router,private loginService: LoginService) {
    if(localStorage.getItem('identity')){
      this.nome = JSON.parse(localStorage.getItem('identity')).firstName || JSON.parse(localStorage.getItem('identity')).login;
      this.image = JSON.parse(localStorage.getItem('identity')).imageUrl || this.imageDefault;
    }else{
      this.nome = JSON.parse(sessionStorage.getItem('identity')).firstName || JSON.parse(sessionStorage.getItem('identity')).login;
      this.image = JSON.parse(sessionStorage.getItem('identity')).imageUrl || this.imageDefault;
    }
    this.isAdmin = JSON.parse(sessionStorage.getItem('identity') || localStorage.getItem('identity')).authorities.indexOf("ROLE_ADMIN")!=-1;
   }

  ngOnInit(): void {
  }

  apriqualcosa(el : string, close?: boolean){
    close = close || false;
    this.selected = el;
    let path="dashboard/";

    switch(el){
      case 'graph':
        this.router.navigate([path+el]);
        break;

      case 'ds':
        this.router.navigate([path+el]);
        break;

      case 'unita':
        this.router.navigate([path+el]);
        break;

      case 'pr':
        this.router.navigate([path+el]);
        break;

      case 'lu':
        this.router.navigate([path+el]);
        break;

      case 'csv':
        this.router.navigate([path+el]);
        break;

      default:
        this.router.navigate([path]);
        break;
    }

    if(close){
      this.verticalnav();
    }
  }

  logout(){
    this.loginService.logout();
    this.router.navigate(['login']);
  }

  verticalnav(){
    if(this.vnav)
      this.vnav=false;
    else
      this.vnav=true;
  }

}
