import { Component, OnInit } from '@angular/core';
import { UserDTO } from 'src/dto/userdto';
import { UserService } from 'src/service/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  usertoinsert: UserDTO = new UserDTO();
  users: UserDTO[];
  
  constructor(private service: UserService, private router: Router) { }

  ngOnInit() {
    this.getUsers();
  }

  getUsers() {
    this.service.getAll().subscribe(users => this.users = users);
  }

  insert(user: UserDTO) {
    this.usertoinsert.usertype=1;
    this.service.insert(user).subscribe(() => this.getUsers());
    this.router.navigate(['/login']);
  }

  back(){
    this.router.navigate(['/login']);
  }

  clear(){
    this.usertoinsert = new UserDTO();
  }

 
}
