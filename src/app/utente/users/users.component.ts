import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/service/user.service';
import { UserDTO } from 'src/dto/userdto';
import { Router } from '@angular/router';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  users: UserDTO[];
  utente: UserDTO;
  usertoinsert: UserDTO = new UserDTO();

  constructor(private service: UserService, private router: Router) { }

  ngOnInit() {
    this.getUtente();
  }

  getUsers() {
    this.service.getAll().subscribe(users => this.users = users);
  }

  getUtente() {
    this.service.read(Number(localStorage.getItem('idUser'))).subscribe(utente => this.utente = utente);
  }

  delete(user: UserDTO) {
    this.service.delete(user.id).subscribe(() => this.getUsers());
  }

  update1(utente: UserDTO) {
    this.service.update(utente).subscribe(() => this.getUtente());
    alert('Motifica avvenuta con successo');
    this.router.navigate(['/utente-dashboard']);
  }

  update(user: UserDTO) {
    this.service.update(user).subscribe(() => this.getUsers());
  }

  insert(user: UserDTO) {
    this.service.insert(user).subscribe(() => this.getUsers());
  }

  clear(){
    this.usertoinsert = new UserDTO();
  }
}
