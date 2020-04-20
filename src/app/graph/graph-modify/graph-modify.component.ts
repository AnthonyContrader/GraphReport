import { Component, OnInit, OnChanges, Input, Output, EventEmitter, ViewChild, ElementRef } from '@angular/core';
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

  dsList: DataSetDTO[];
  subList: DataSetDTO[];
  dsDistinct: string[];

  colorPreset: string[] = ['FF6284', '35A2EB', 'FFCF57', 'E8E9ED', '4BC0C0', '98BBCD', 'DCDCDC', 'F74649', '46C0BC', 'FDB45C', '5AAD70', '7B6EBC', 'E92DA7'];

  newAssi: number[] = [];
  zToo: boolean = false;

  datasets: ChartDataSets[];
  labels: Label[];
  options: ChartOptions;
  legend: boolean;
  chartType: string;
  colors: { backgroundColor: string, borderColor: string, pointBackgroundColor: string, pointBorderColor: string, pointHoverBackgroundColor: string, pointHoverBorderColor: string }[] = [];
  assex: mtmDTO[];
  first: boolean = false;

  key = Object.keys; 
  enumFont: string[] = Object.keys(FontStyle).filter(x => isNaN(Number(x)));
  enumTipo: string[] = Object.keys(TipoGrafico).filter(x => isNaN(Number(x)));
  ready: boolean = false;
  assi: mtmDTO[];
  sc: number = 2;

  constructor(private graphService: GraphService,private service: GraphService,private dsService: DataSetService) { }

  ngOnInit(): void {
    this.getDsByUser().then(
      res => {
        this.dsDistinct = this.dsList.map(x => x.titolo).filter((value, index, self) => { return self.indexOf(value) === index; }).sort();
        this.filtra(this.dsDistinct[0]);
      }
    );
  }

  getDsByUser(){
    return new Promise((ok,ops) => {
      this.dsService.getAll().subscribe( x => {
        this.dsList=x.filter(y => y.idUser==JSON.parse(localStorage.getItem('identity') || sessionStorage.getItem('identity')).id);
        return ok();
      });
    });
  }

  filtra(ds){
    this.subList = this.dsList.filter(x => x.titolo==ds);
  }

  ngOnChanges(): void {
    if(this.graph.id == -1){
      this.graphService.getLastModify().subscribe(x => {
        this.graph=x;
        this.draw();
      });
    }else{
      this.draw();
    }  
  }

  redraw(){
    this.service.read(this.graph.id).subscribe(x => {
      this.graph=x;
      this.draw();
    });
  }

  draw(){
      this.init().then(() => { 
          Promise.all( 
            this.assi.map(x => { return this.getAssi(x).then(y => { x.dataSet=<DataSetDTO>y })})
          ).then(()=>{
            if(this.assi.length>1){
              this.settaGrafico();
              this.ready=true;
            }else{
              this.first=true;
            }
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
    this.colors=[];
    if(this.graph.tipoGrafico.toString()=="POLARAREA")
      this.chartType="polarArea";
    else
      this.chartType=this.graph.tipoGrafico.toString().toLowerCase();
    this.options={
        responsive: true,
        title: {
            fontSize: this.graph.fontSize,
            text: this.graph.titolo,
            fontColor: this.graph.fontColor,
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
        this.colors.push(this.toChartColor(this.assex[j].colore,this.assex[j].tipoSet.toString()));
        let ti;
        if(this.assex[j].tipoSet.toString()=="POLARAREA")
          ti="polarArea";
        else
          ti=this.assex[j].tipoSet.toString().toLowerCase();
         if(assez[j]!=undefined){
            for(let i=0;this.assex[j].dataSet.valori.split("_").length>i;i++){
                arr[j]={
                    x: Number(this.assex[j].dataSet.valori.split("_")[i]),
                    y: Number(assey[j].dataSet.valori.split("_")[i]),
                    r: Number(assez[j].dataSet.valori.split("_")[i])
                };
                data={ data: arr[j], label: assez[j].dataSet.commento, type: ti }
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
                data={ data: arr, label: assey[j].dataSet.commento, type: ti  }
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
                data={ data: arr, label: assey[j].dataSet.commento, type: ti };
                this.datasets.push(data);
                if(this.graph.pareto){
                    pareto={ type: 'line',data: arrPar, label: "Pareto: "+assey[j].dataSet.commento };
                    this.datasets.push(pareto);
                }
            }
        }
    }

  }

  toChartColor(col: string,tipo:string){
    let a = col.match(/\w\w/g).map(x => parseInt(x, 16));
    let colore;
      switch(tipo.toLowerCase()){
        case "line":
        case "radar":
        case "bubble":
          colore={ 
            backgroundColor: "rgba("+a[0]+","+a[1]+","+a[2]+","+0.2+")",
            borderColor: "rgba("+a[0]+","+a[1]+","+a[2]+","+1+")",
            pointBackgroundColor: "rgba("+a[0]+","+a[1]+","+a[2]+","+1+")",
            pointBorderColor: '#fff',
            pointHoverBackgroundColor: '#fff',
            pointHoverBorderColor: "rgba("+a[0]+","+a[1]+","+a[2]+","+0.8+")"
            };
          break;
          case "bar":
            colore={ 
              backgroundColor: "rgba("+a[0]+","+a[1]+","+a[2]+","+0.6+")",
              };
            break;
      }
    return colore;
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

    addSet(){
      let pos;
      if(this.assex == undefined)
        pos=0;
      else
        pos=this.assex.length;

      this.graphService.addSet(new mtmDTO(null,new DataSetDTO(this.newAssi[0],null,null,null,null,null),this.graph.id,"x",this.graph.tipoGrafico,"#"+this.colorPreset[pos%13])).subscribe( x =>{
        this.graphService.addSet(new mtmDTO(null,new DataSetDTO(this.newAssi[1],null,null,null,null,null),this.graph.id,"y",this.graph.tipoGrafico,"#"+this.colorPreset[pos%13])).subscribe(y => {
          if(this.zToo)
            this.graphService.addSet(new mtmDTO(null,new DataSetDTO(this.newAssi[2],null,null,null,null,null),this.graph.id,"z",this.graph.tipoGrafico,"#"+this.colorPreset[pos%13])).subscribe( z => {
              this.draw();
            },
            err => { alert('ops3')},
            () => {}
            );
          else
            this.draw();
        },
        err =>{ alert('ops2') },
        () => {});
      },
      err=>{alert('ops')},
      () => {}
      );        
    }

    submitOption(){
      this.graph.modify=new Date();
      this.mix();
      this.service.update(this.graph).subscribe( () => 
        Promise.all( this.assex.map(x => {return this.mtmUpdate(x);})).then(x => this.redraw())
      );
    }

    mtmUpdate(x: mtmDTO){
      return new Promise((ok,ops) => {
        this.service.mtmUpdate(x).subscribe(x => {return ok(x);});
      });
    }

    mix(){
      if(this.graph.mixed){
        this.graph.tipoGrafico = TipoGrafico.BAR;
      }else{
        this.assex.forEach(x => x.tipoSet=this.graph.tipoGrafico)
      }
    }

}
