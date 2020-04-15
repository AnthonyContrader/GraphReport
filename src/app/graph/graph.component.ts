import { Component, OnInit } from '@angular/core';
import { GraphDTO } from 'src/dto/graph.dto';
import { GraphService } from 'src/service/graph.service';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/compiler';

CUSTOM_ELEMENTS_SCHEMA 

@Component({
  selector: 'app-graph',
  templateUrl: './graph.component.html',
  styleUrls: ['./graph.component.css']
})
export class GraphComponent implements OnInit {

  loaded: boolean = false;
  listGraph : GraphDTO[];
  userId : number = JSON.parse(localStorage.getItem('identity') || sessionStorage.getItem('identity')).id;

  constructor(private service : GraphService) { }

  ngOnInit(): void {
    this.service.getGraphListByUser(this.userId,0,20,['titolo']).subscribe(x => {this.listGraph=x; this.loaded=true;});
  }

}