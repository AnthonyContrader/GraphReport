import { Component, OnInit, ViewChild } from '@angular/core';
import { faCheck, faTimes } from '@fortawesome/free-solid-svg-icons';
import { GraphListComponent } from './graph-list/graph-list.component';
import { GraphDTO } from 'src/dto/graph.dto';
import { GraphService } from 'src/service/graph.service';

@Component({
  selector: 'app-graph',
  templateUrl: './graph.component.html',
  styleUrls: ['./graph.component.css']
})
export class GraphComponent implements OnInit {

  @ViewChild(GraphListComponent) childList: GraphListComponent;

  userId : number = JSON.parse(localStorage.getItem('identity') || sessionStorage.getItem('identity')).id;
  graph: GraphDTO = new GraphDTO(-1);
  op: string = null;
  whereId: number;
  ok = faCheck;
  ann= faTimes;
  err: number = 0;

  constructor(private service: GraphService) { }

  ngOnInit(): void {
  }

  confDel(del){
    this.op = 'del';
    this.whereId=del;
  }

  conf(b:boolean){

    if(b){
      switch(this.op){
        case 'del':
          this.service.delete(this.whereId).subscribe(
            () => { this.service.mtmDelete(this.whereId).subscribe(
              () => { this.childList.update() },
              err => { err = 5},
              () => {}
            )},
            err => { err = 4 },
            () => {}
            );
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
    if(result)
      this.childList.update();
    else
    this.err=1;
  }

  catch(e:number){
    this.err=e+1;
  }

}