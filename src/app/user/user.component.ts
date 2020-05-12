import { Component, OnInit } from '@angular/core';
import { UserDTO } from 'src/dto/user.dto';
import { UserService } from 'src/service/user.service';
import { AccountService } from 'src/authJWT/account.service';
import { faKey, faMinus, faPlus, faUserMinus} from '@fortawesome/free-solid-svg-icons';

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
  modify = faKey;
  plus = faPlus;
  delRole = faKey;
  minus = faMinus;
  cancella = faUserMinus;


  constructor(private service: UserService, private accountService: AccountService) {
    this.isAdmin = JSON.parse(sessionStorage.getItem('identity') || localStorage.getItem('identity')).authorities.indexOf("ROLE_ADMIN")!=-1;
    this.getAuthorities();
  }

  ngOnInit(): void {
    this.getUsers();
  }

  getUsers(){
    return this.service.getAll().subscribe(users => this.users = users);
  }

  disactivate(user: UserDTO){
    this.service.deleteByLogin(user.login).subscribe(() => this.getUsers());
  }

  updateAuth(i, value: string){
    this.users[i].authorities.push(value);
    this.service.update(this.users[i]).subscribe(() => this.getUsers());
  }

  deleteAuth(i, value){
    const role = this.users[i].authorities.indexOf(value);
    if (role === -1){
    }else{
      this.users[i].authorities.splice(role, 1);
      const json = JSON.stringify(this.users[i].authorities);
      this.service.update(this.users[i]).subscribe(() => this.getUsers());
    }
  }

  getAuthorities(){
    this.service.authorities().subscribe(listAuthorities => {
      this.listAuthorities = listAuthorities;
    });
  }


}
