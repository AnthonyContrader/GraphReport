import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/service/user.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-activate',
  templateUrl: './activate.component.html',
  styleUrls: ['./activate.component.css']
})
export class ActivateComponent implements OnInit {

  key: string;
  error: boolean = false;
  success: boolean = false;


  constructor(private service: UserService, private route: ActivatedRoute, private router: Router) {
    this.route.queryParams.subscribe(x => this.key = x['key']);
    this.service.activation(this.key).subscribe(
    ok => {this.success = true;  },
    nonok => {this.error = true; }
    );
   }

  ngOnInit(): void {

  }

  redirect(){
    this.router.navigate(['']);
  }


}
