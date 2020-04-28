import { Component, OnInit } from '@angular/core';
import { UserDTO } from 'src/dto/user.dto';
import { UserService } from 'src/service/user.service';
import { Router } from '@angular/router';
import {faCheck,faTimes,faUndo} from '@fortawesome/free-solid-svg-icons'
import { AuthServerProvider } from 'src/authJWT/auth-jwt.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  inserisci = faCheck;
  chiudi = faTimes;
  cancella = faUndo;
  newUser: UserDTO = new UserDTO(null,null,null,null,null,false,null,"it-IT",['ROLE_USER'],null,new Date(),null,new Date(),null);
  users: UserDTO[];
  key: string = this.newUser.activationKey;


  constructor(
    private userService: UserService,
    private router: Router,
    private authJwtService: AuthServerProvider
    ){}

  ngOnInit(): void { }

  getUsers(){
    this.userService.getAll().subscribe(users => this.users = users);
  }

  insert(){
    this.authJwtService.getToken();
    this.userService.registration(this.newUser).subscribe();
    alert('Check your Email for the Account Activation!');
    this.router.navigate(['/login']);
  }

  back(){
    this.router.navigate(['/login']);
  }

  clear(){
    this.newUser = new UserDTO();
  }

}
