import { Component, OnInit } from '@angular/core';
import { GraphDTO } from 'src/dto/graph.dto';
import { GraphService } from 'src/service/graph.service';

@Component({
  selector: 'app-graph',
  templateUrl: './graph.component.html',
  styleUrls: ['./graph.component.css']
})
export class GraphComponent implements OnInit {

  listGraph : GraphDTO[];
  userId : number = JSON.parse(localStorage.getItem('identity') || sessionStorage.getItem('identity')).id;

  constructor(private service : GraphService) { }

  ngOnInit(): void {
    //this.service.getGraphListByUser(this.userId).subscribe(x => this.listGraph=x);
  }

}