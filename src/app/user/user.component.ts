import { Component, OnInit } from '@angular/core';
import { UserDTO } from 'src/dto/user.dto';
import { UserService } from 'src/service/user.service';
import { AccountService } from 'src/authJWT/account.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  users: UserDTO[];
  isAdmin: boolean;


  constructor(private service: UserService, private accountService: AccountService) {
    this.isAdmin = JSON.parse(sessionStorage.getItem('identity') || localStorage.getItem('identity')).authorities.indexOf("ROLE_ADMIN")!=-1;
  }

  ngOnInit(): void {
    this.getUsers();

  }

  getUsers(){
    return this.service.getAll().subscribe(users => this.users = users);
  }


}
