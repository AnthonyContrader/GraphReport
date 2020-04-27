import { Component, OnInit, ViewChild } from '@angular/core';
import { faCheck, faTimes } from '@fortawesome/free-solid-svg-icons';
import { GraphListComponent } from './graph-list/graph-list.component';
import { GraphDTO } from 'src/dto/graph.dto';
import { GraphService } from 'src/service/graph.service';
import { GraphModifyComponent } from './graph-modify/graph-modify.component';
import { UserDTO } from 'src/dto/user.dto';
import { UserService } from 'src/service/user.service';

@Component({
  selector: 'app-graph',
  templateUrl: './graph.component.html',
  styleUrls: ['./graph.component.css']
})
export class GraphComponent implements OnInit {

  @ViewChild(GraphListComponent) childList: GraphListComponent;
  @ViewChild(GraphModifyComponent) childModify: GraphModifyComponent;

  userId : number = JSON.parse(localStorage.getItem('identity') || sessionStorage.getItem('identity')).id;
  ut : number;
  isAdmin: boolean = JSON.parse(sessionStorage.getItem('identity') || localStorage.getItem('identity')).authorities.indexOf("ROLE_ADMIN")!=-1;
  userList : UserDTO[] = [];
  graph: GraphDTO = new GraphDTO(-1);
  op: string = null;
  whereId: number;
  ok = faCheck;
  ann= faTimes;
  err: number = 0;

  constructor(private service: GraphService,private userService: UserService) { 
    this.userService.getAll().subscribe(x => this.userList=x);
    this.ut = this.userId;
  }

  ngOnInit(): void {
  }

  confDel(del){
    this.op = 'del';
    this.whereId=del;
  }

  conf(b:boolean){
    let del=this.whereId;
    if(b){
      switch(this.op){
        case 'del':
          this.service.delete(this.whereId).subscribe( () => { 
            this.childUpdate();
          });
          break;
      }
    }

    this.op=null;
    this.whereId=0;

  }

  modify(m : GraphDTO){
    this.graph=m[0];
  }

  checkOp(result:boolean){
    if(result){
      this.childUpdate();
    }else
      this.err=1;
  }

  childUpdate(){
    this.childList.update(this.ut);
    this.graph=new GraphDTO(-1);
  }

  catch(e:number){
    this.err=e+1;
  }

}