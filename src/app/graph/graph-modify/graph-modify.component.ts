import { Component, OnInit, Input } from '@angular/core';
import { GraphService } from 'src/service/graph.service';

@Component({
  selector: 'app-graph-modify',
  templateUrl: './graph-modify.component.html',
  styleUrls: ['./graph-modify.component.css']
})
export class GraphModifyComponent implements OnInit {

  @Input('show') graph;

  opt:number=null;

  constructor(private graphService: GraphService) { }

  ngOnInit(): void {
    if(this.graph == -1)
      this.graphService.getLastModify().subscribe(x => {
        this.graph=x;
        this.opt=x.id;
      });
  }

}
