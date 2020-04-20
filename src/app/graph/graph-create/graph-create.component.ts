import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { GraphDTO } from 'src/dto/graph.dto';
import { FontStyle } from 'src/dto/fontStyle.enum';
import { TipoGrafico } from 'src/dto/tipoGrafico.enum';
import { GraphService } from 'src/service/graph.service';

@Component({
  selector: 'app-graph-create',
  templateUrl: './graph-create.component.html',
  styleUrls: ['./graph-create.component.css']
})
export class GraphCreateComponent implements OnInit {

  @Output('resOp') riuscito = new EventEmitter();

  graph: GraphDTO;
  enumFont= Object.keys(FontStyle).filter(x => isNaN(Number(x)));
  enumTipo= Object.keys(TipoGrafico).filter(x => isNaN(Number(x)));
  mix: boolean;
  
  keys=Object.keys;

  constructor(private service: GraphService) { 
    this.graph = new GraphDTO(null,null,true,"Nuovo Titolo",FontStyle.HELVETICANEUE,"#666666",20,TipoGrafico.BAR,"top",true,"bottom",false,false,null,null);
  }

  ngOnInit(): void {
    
  }

  crea(){
    this.graph.utente = JSON.parse(localStorage.getItem('identity') || sessionStorage.getItem('identity')).id;
    this.graph.created = new Date();
    this.graph.modify = new Date();
    this.service.insert(this.graph).subscribe(
      result => { this.riuscito.emit("true") },
      error =>  { this.riuscito.emit("false") },
      () => {}
    );
  }

}
