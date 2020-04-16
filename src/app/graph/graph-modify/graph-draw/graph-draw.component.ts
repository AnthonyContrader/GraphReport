import { Component, OnInit, Input } from '@angular/core';
import { faSpinner } from '@fortawesome/free-solid-svg-icons';
import { ChartDataSets, ChartOptions } from 'chart.js';
import { Color, BaseChartDirective, Label } from 'ng2-charts';
import { GraphService } from 'src/service/graph.service';
import { mtmDTO } from 'src/dto/mtm.dto';
import { DataSetService } from 'src/service/DataSetService';
import { filter } from 'rxjs/operators';


@Component({
  selector: 'app-graph-draw',
  templateUrl: './graph-draw.component.html',
  styleUrls: ['./graph-draw.component.css']
})
export class GraphDrawComponent implements OnInit {

  @Input('toDraw') graph:number;

  wait = faSpinner;

  data: ChartDataSets[];
  label: Label[];
  option: ChartOptions;
  legend: boolean;
  tipo: string;

  ready: boolean = false;
  assi: mtmDTO[];

  constructor(private service: GraphService,private dsService: DataSetService) { }

  ngOnInit(): void {
    if(this.graph)
      this.init();
  }

  init(){
    let promise = new Promise((response,reject) => {
      this.service.getIdAssi(this.graph).subscribe(
        x => {
          this.assi=x;
          //this.assi.forEach(x => { this.dsService.read() });
          //this.dsService.getAll().pipe(filter(x => x.id==this.graph))
          return response(true);
        },
        err =>{

          return reject(false);
        },
        () =>{}
      );
    });
  }

}
