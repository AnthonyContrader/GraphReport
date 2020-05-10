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
  listAuthorities: any[];
  user: UserDTO;
  selezionato: string;



  constructor(private service: UserService, private accountService: AccountService) {
    this.isAdmin = JSON.parse(sessionStorage.getItem('identity') || localStorage.getItem('identity')).authorities.indexOf("ROLE_ADMIN")!=-1;
  }

  ngOnInit(): void {
    this.getUsers();
    this.getAuthorities();
  }

  getUsers(){
    return this.service.getAll().subscribe(users => this.users = users);
  }

  disactivate(user: UserDTO){
    this.service.deleteByLogin(user.login).subscribe(() => this.getUsers());
  }

  /*Controllare Array Authorities, errore sull'ID --> Undefined */
  updateAuth(i){
    if(this.users[i] != null){
      this.users[i].authorities.push(this.listAuthorities[i]);
      //let json = JSON.stringify(this.users[i]);
      //alert(json);
      this.service.update(this.users[i]).subscribe(() => this.getUsers());
    }
  }

  getAuthorities(){
    this.service.authorities().subscribe(listAuthorities => {
      this.listAuthorities = listAuthorities;
    });
  }


}
