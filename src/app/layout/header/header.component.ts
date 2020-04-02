import { Component, OnInit } from '@angular/core';
import { UserDTO } from 'src/dto/userdto';
import { Router } from '@angular/router';
import { SearchService } from 'src/service/searchservice';
import { FormGroup, FormControl } from '@angular/forms';




@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  user: UserDTO = new UserDTO ();
  ricerca: FormGroup;
  constructor(private router: Router, private service: SearchService) {
    this.ricerca = new FormGroup({
      testo : new FormControl()
  });

  }

  ngOnInit() {
    this.user = JSON.parse(localStorage.getItem('currentUser'));
  }

  logout() {
    localStorage.clear();
    this.router.navigateByUrl('');
  }

  search(ricerca) {
    let path: string;
    let testo = ricerca.testo;
    if (localStorage.getItem('usertype').toString() === 'ADMIN') {
      path = '/admin-dashboard';
    } else {
      path = '/utente-dashboard';
    }
    this.ricerca.reset();
    this.router.navigate([path + '/search', testo]);
  }



}
