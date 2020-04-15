import { Component, OnInit, ViewChild } from '@angular/core';
import { faCheck, faTimes } from '@fortawesome/free-solid-svg-icons';
import { GraphListComponent } from './graph-list/graph-list.component';

@Component({
  selector: 'app-graph',
  templateUrl: './graph.component.html',
  styleUrls: ['./graph.component.css']
})
export class GraphComponent implements OnInit {

  @ViewChild(GraphListComponent) childList: GraphListComponent;

  userId : number = JSON.parse(localStorage.getItem('identity') || sessionStorage.getItem('identity')).id;
  graph: number = -1;
  op: string = null;
  whereId: number;
  ok = faCheck;
  ann= faTimes;
  err: number = 0;

  constructor() { }

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
          console.warn('elimina grafico '+this.whereId);
          console.warn('elimina set relativi');
          break;
      }
    }

    this.op=null;
    this.whereId=0;

  }

  modify(m:number){
    this.graph=m;
  }

  checkOp(result:boolean){
    if(result)
      this.childList.update();
    else
    this.err=1;
  }

}