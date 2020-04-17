import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { GraphService } from 'src/service/graph.service';
import { GraphDTO } from 'src/dto/graph.dto';
import { faTrashAlt } from '@fortawesome/free-solid-svg-icons';
import { TipoGrafico } from 'src/dto/tipoGrafico.enum';

@Component({
  selector: 'app-graph-list',
  templateUrl: './graph-list.component.html',
  styleUrls: ['./graph-list.component.css']
})
export class GraphListComponent implements OnInit {

  @Input('search') id : number;
  @Output('toDelete') delete = new EventEmitter();
  @Output('toShow') modify = new EventEmitter();

  elimina = faTrashAlt;

  listGraph: GraphDTO[];
  owner: boolean;
  tgList: string[] = Object.keys(TipoGrafico).filter(x => isNaN(Number(x)));

  constructor(private service: GraphService) { }

  ngOnInit(): void {
    this.owner=(this.id === JSON.parse(localStorage.getItem('identity') || sessionStorage.getItem('identity')).id);
    this.update();
  }

  update(){
    this.service.getGraphListByUser(this.id,0,20,['titolo']).subscribe(x => this.listGraph=x);
  }

  del(d:number){
    this.delete.emit(d);
  }

  show(s:number){
    this.modify.emit(this.listGraph.filter(x => x.id == s));
  }



}
