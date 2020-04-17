import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/authJWT/login.service';
import { Router } from '@angular/router';
import { faCaretDown } from '@fortawesome/free-solid-svg-icons';


@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {

  nome : string;
  isAdmin : boolean;
  selected : string = this.router.url.split('/')[2];

  arrowDown = faCaretDown;

  constructor(private router: Router,private loginService: LoginService) {
    if(localStorage.getItem('identity')){
      this.nome = JSON.parse(localStorage.getItem('identity')).firstName || JSON.parse(localStorage.getItem('identity')).login;
    }else{
      this.nome = JSON.parse(sessionStorage.getItem('identity')).firstName || JSON.parse(sessionStorage.getItem('identity')).login;
    }
    this.isAdmin = JSON.parse(sessionStorage.getItem('identity') || localStorage.getItem('identity')).authorities.indexOf("ROLE_ADMIN")!=-1;
   }

  ngOnInit(): void {
  }

  apriqualcosa(el : string){
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

      default:
        this.router.navigate([path]);
        break;
    }
  }

  logout(){
    this.loginService.logout();
    this.router.navigate(['login']);
  }

}
