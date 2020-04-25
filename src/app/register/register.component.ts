import { Component, OnInit } from '@angular/core';
import { UserDTO } from 'src/dto/user.dto';
import { UserService } from 'src/service/user.service';
import { Router } from '@angular/router';
import {faCheck,faTimes,faUndo} from '@fortawesome/free-solid-svg-icons'

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  inserisci = faCheck;
  chiudi = faTimes;
  cancella = faUndo;
  newUser: UserDTO = new UserDTO(null,null,null,null,null,true,null,["ROLE_USER"],"RegisterForm",new Date(),"RegisterForm",new Date(),null);
  users: UserDTO[];

  constructor(private userService: UserService, private router: Router) {

   }

  ngOnInit(): void {
    this.getUsers();
  }

  getUsers(){
    this.userService.getAll().subscribe(users => this.users = users);
  }

  insert(){
    this.userService.insert(this.newUser).subscribe(() => this.getUsers());
    this.router.navigate(['/login']);
  }

  back(){
    this.router.navigate(['/login']);
  }

  clear(){
    this.newUser = new UserDTO();
  }

}
