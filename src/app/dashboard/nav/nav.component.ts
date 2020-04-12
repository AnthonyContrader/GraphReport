import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/authJWT/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {

  selected : string = "home";

  constructor(private router: Router,private loginService: LoginService) { }

  ngOnInit(): void {
  }

  apriqualcosa(el : string){
    this.selected = el;

    //route alla pagina

  }

  logout(){
    this.loginService.logout();
    this.router.navigate(['login']);
  }

}
