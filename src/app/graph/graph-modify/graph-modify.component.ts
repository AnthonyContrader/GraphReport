import { Component, OnInit, Input, Output, EventEmitter, ViewChild, ElementRef } from '@angular/core';
import { GraphService } from 'src/service/graph.service';
import { GraphDTO } from 'src/dto/graph.dto';
import { mtmDTO } from 'src/dto/mtm.dto';
import { Color, BaseChartDirective, Label } from 'ng2-charts';
import { ChartDataSets, ChartOptions, PositionType } from 'chart.js';
import { faSpinner } from '@fortawesome/free-solid-svg-icons';
import { DataSetService } from 'src/service/DataSetService';
import { DataSetDTO } from 'src/dto/dataSet.dto';
import { TipoGrafico } from 'src/dto/tipoGrafico.enum';
import { FontStyle } from 'src/dto/fontStyle.enum';

@Component({
  selector: 'app-graph-modify',
  templateUrl: './graph-modify.component.html',
  styleUrls: ['./graph-modify.component.css']
})
export class GraphModifyComponent implements OnInit {

  @Input('show') graph : GraphDTO;
  @Output('errore') err = new EventEmitter();
  @ViewChild("esporta") dom : ElementRef;
  wait = faSpinner;

  datasets: ChartDataSets[];
  labels: Label[];
  options: ChartOptions;
  legend: boolean;
  chartType: string;
  colors: Color[];
  az: boolean = false;
  assex: mtmDTO[];

  key = Object.keys; 
  enumFont: string[] = Object.keys(FontStyle).filter(x => isNaN(Number(x)));
  enumTipo: string[] = Object.keys(TipoGrafico).filter(x => isNaN(Number(x)));
  ready: boolean = false;
  assi: mtmDTO[];
  sc: number = 1;

  constructor(private graphService: GraphService,private service: GraphService,private dsService: DataSetService) { }

  ngOnInit(): void {
    if(this.graph.id == -1){
      this.graphService.getLastModify().subscribe(x => {
        this.graph=x;
        this.draw();
      });
    }else{
      this.draw();
    }  
  }

  draw(){
      this.init().then(() => { 
          Promise.all( 
            this.assi.map(x => { return this.getAssi(x).then(y => { x.dataSet=<DataSetDTO>y })})
          ).then(()=>{
            this.settaGrafico();
            this.ready=true;
          }).catch(() => this.err.emit(2));
       } ).catch(() => this.err.emit(1));
  }

  init(){
    return new Promise((response,reject) => {
      this.service.getIdAssi(this.graph.id).subscribe(
        x => {
          this.assi=x;
          response(true);
        },
        err =>{

          reject(err);
        },
        () =>{}
      );
  });
}

  getAssi(x){
    return new Promise((res) => {
        this.dsService.read(x.dataSet.id).subscribe(y => {
          res(y);
          });
        })
}

  settaGrafico(){
    this.datasets=[];
    this.chartType=this.graph.tipoGrafico.toString().toLowerCase();
    this.options={
        responsive: true,
        title: {
            fontSize: this.graph.fontSize,
            text: this.graph.titolo,
            fontFamily: this.graph.fontStyle.toString(),
            position: <PositionType>this.graph.posTitolo,
            display: this.graph.titoloBool,
        },
        legend:{
            display: this.graph.legenda,
            position: <PositionType>this.graph.posLegenda,
        },
      }

    this.assex = [];
    let assey: mtmDTO[] = [];
    let assez: mtmDTO[] = [];
    let n =-1;
    for(let a of this.assi){
        switch(a.asse){
            case "x":
                n++;
                this.assex[n]=a;
                break;
            case "y":
                assey[n]=a;
                break;
            case "z":
                assez[n]=a;
                break;
        }
          
    }

    for(let j=0;this.assex.length>j;j++){
        let pareto : {};
        let data : {} ;
        let arr : any[] = [];
        let arrPar : any[] = [0];
         if(assez[j]!=undefined){
            for(let i=0;this.assex[j].dataSet.valori.split("_").length>i;i++){
                arr[j]={
                    x: Number(this.assex[j].dataSet.valori.split("_")[i]),
                    y: Number(assey[j].dataSet.valori.split("_")[i]),
                    r: Number(assez[j].dataSet.valori.split("_")[i])
                };
                data={ data: arr[j], label: assez[j].dataSet.commento }
                this.datasets.push(data);
            }
        }else{
            if(this.graph.tipoGrafico===TipoGrafico.SCATTER){
                for(let i=0;this.assex[j].dataSet.valori.split("_").length>i;i++){
                    arr[i] = {
                        x: Number(this.assex[j].dataSet.valori.split("_")[i]),
                        y: Number(assey[j].dataSet.valori.split("_")[i])
                    };
                }
                data={ data: arr, label: assey[j].dataSet.commento }
                this.datasets.push(data);
            }else{
                this.labels = this.assex[j].dataSet.valori.split("_"); 
                for(let k=0; assey[j].dataSet.valori.split("_").length>k;k++){
                    arr[k]=Number(assey[j].dataSet.valori.split("_")[k].toString());
                    if(this.graph.pareto){
                        arrPar[k]+=Number(assey[j].dataSet.valori.split("_")[k].toString());
                        if(k!=assey[j].dataSet.valori.split("_").length-1)
                            arrPar[(k+1)]=arrPar[k];
                    }
                }
                data={ type: this.chartType,data: arr, label: assey[j].dataSet.commento };
                this.datasets.push(data);
                if(this.graph.pareto){
                    pareto={ type: 'line',data: arrPar, label: "Pareto: "+assey[j].dataSet.commento };
                    this.datasets.push(pareto);
                }
            }
        }
    }
  if(this.graph.tipoGrafico == TipoGrafico.BUBBLE)
      this.colors = [
          { // red
              backgroundColor: 'rgba(255,0,0,0.3)',
              borderColor: 'red',
              pointBackgroundColor: 'rgba(148,159,177,1)',
              pointBorderColor: '#fff',
              pointHoverBackgroundColor: '#fff',
              pointHoverBorderColor: 'rgba(148,159,177,0.8)'
          },
          { // dark grey
              backgroundColor: 'rgba(77,83,96,0.2)',
              borderColor: 'rgba(77,83,96,1)',
              pointBackgroundColor: 'rgba(77,83,96,1)',
              pointBorderColor: '#fff',
              pointHoverBackgroundColor: '#fff',
              pointHoverBorderColor: 'rgba(77,83,96,1)'
          },
          { // grey
              backgroundColor: 'rgba(148,159,177,0.2)',
              borderColor: 'rgba(148,159,177,1)',
              pointBackgroundColor: 'rgba(148,159,177,1)',
              pointBorderColor: '#fff',
              pointHoverBackgroundColor: '#fff',
              pointHoverBorderColor: 'rgba(148,159,177,0.8)'
          },
          { backgroundColor: 'red', },
          { backgroundColor: 'green', },
          { backgroundColor: 'blue', },
          { backgroundColor: 'purple', },
          { backgroundColor: 'yellow', },
          { backgroundColor: 'brown', },
          { backgroundColor: 'magenta', },
          { backgroundColor: 'cyan', },
          { backgroundColor: 'orange', },
          { backgroundColor: 'pink', },
        ];
      
      }

      sch(x){
        this.sc=x;
      }

      esporta(ext:string){
        let canvas : HTMLCanvasElement = <HTMLCanvasElement>this.dom.nativeElement;
        let img = canvas.toDataURL("image/"+ext+";base64",1.0).replace("image/png", "image/octet-stream");
        let a = document.createElement('a');
        a.href = img;
        a.download = this.graph.titolo+"_graph"+ext;
        a.click();
      }

}
