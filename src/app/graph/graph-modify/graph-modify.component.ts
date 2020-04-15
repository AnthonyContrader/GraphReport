import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-graph-modify',
  templateUrl: './graph-modify.component.html',
  styleUrls: ['./graph-modify.component.css']
})
export class GraphModifyComponent implements OnInit {

  @Input('show') graph: number;

  constructor() { }

  ngOnInit(): void {
  }

}
