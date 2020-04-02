import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { FontStyle } from 'src/dto/FontStyle';
import { TipoGrafico } from 'src/dto/TipoGrafico';
import { ActivatedRoute } from '@angular/router';
import { GraphService } from 'src/service/GraphService';
import { ChartDataSets, ChartOptions, PositionType } from 'chart.js';
import { Label, Color } from 'ng2-charts';
import { GraphDTO } from 'src/dto/GraphDTO';
import { mtmDTO } from 'src/dto/mtmDTO';

@Component({
    selector: "app-graph-modify",
    templateUrl: "./graph-modify.component.html",
    styleUrls: ["./graph-modify.component.css"]
})
export class GraphModifyComponent implements OnInit{

    @ViewChild("esporta",{read: ElementRef}) dom : ElementRef;

    public isAddsetCollapsed : boolean = true;
    public isOptionCollapsed : boolean = false;
    public isExportCollapsed : boolean = false;

    public idGraph : number;
    public owner : boolean;
    public graph : GraphDTO;
    public assiList : mtmDTO[];
    public pronto : boolean = false;

    public chartTitle : string;
    public legend: boolean;
    public chartType: TipoGrafico;
    public chartFont: FontStyle;
    public chartTitlePos: PositionType = "top" ;
    public datasets: ChartDataSets[] =[];
    public labels: Label[];

    public options: (ChartOptions);
    public colors : Color[] = [
        { // grey
          backgroundColor: 'rgba(148,159,177,0.2)',
          borderColor: 'rgba(148,159,177,1)',
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
        { // red
          backgroundColor: 'rgba(255,0,0,0.3)',
          borderColor: 'red',
          pointBackgroundColor: 'rgba(148,159,177,1)',
          pointBorderColor: '#fff',
          pointHoverBackgroundColor: '#fff',
          pointHoverBorderColor: 'rgba(148,159,177,0.8)'
        }
      ];
    
    constructor(private service: GraphService, private route: ActivatedRoute){
        this.idGraph=Number(this.route.snapshot.paramMap.get('id'));
        this.owner=this.route.snapshot.paramMap.get('owner').toString()=="true";
    }

    ngOnInit(){
        this.legend = true;
        this.init();
    }

    init(){
        this.service.read(this.idGraph).subscribe(x=>{
            this.graph=x;
            this.service.getDSByGraph(this.idGraph).subscribe(y=>{
                this.assiList=y;
                this.settaGrafico();
                this.pronto=true;
            })
        });
    }

    settaGrafico(){
        this.chartTitle=this.graph.titolo;
        this.legend=this.graph.legenda;
        this.chartType=this.graph.tipografico;
        this.chartFont=this.graph.fontStyle;
        //Posizione titolo
        //Pareto da fare
        this.options={
            responsive: true,
            title: {
                text: this.chartTitle,
                //fontFamily: this.chartFont.toString(),
                position: this.chartTitlePos,
                display: true
            }
        };

        let assex: mtmDTO[] = [];
        let assey: mtmDTO[] = [];
        let assez: mtmDTO[] = [];
        let n =-1;
        for(let x of this.assiList){
            switch(x.asse){
                case "x":
                    n++;
                    assex[n]=x;
                    break;
                case "y":
                    assey[n]=x;
                    break;
                case "z":
                    assez[n]=x;
                    break;
            }
              
        }

        let data : {} ;
        let arr : any[] = [];
        for(let j=0;assex.length>j;j++){
             if(assez[j]!=undefined){
                for(let i=0;3>i;i++){ //assex[j].dataSet.valore.split("_").length
                    arr[j]={
                        x: Number(assex[j].dataSet.valore.split("_")[i]),
                        y: Number(assey[j].dataSet.valore.split("_")[i]),
                        r: Number(assez[j].dataSet.valore.split("_")[i])
                    };
                    data={ data: arr[j], label: assez[j].dataSet.commento }
                    this.datasets.push(data);
                }
            }else{
                if(this.graph.tipografico.toString()=="scatter"){
                    for(let i=0;assex[j].dataSet.valore.split("_").length>i;i++){
                        arr[i] = {
                            x: Number(assex[j].dataSet.valore.split("_")[i]),
                            y: Number(assey[j].dataSet.valore.split("_")[i])
                        };
                    }
                    data={ data: arr, label: assey[j].dataSet.commento }
                    this.datasets.push(data);
                    console.warn(assey[j].dataSet.commento);
                }else{
                    this.labels = assex[j].dataSet.valore.split("_");
                    for(let k=0; assey[j].dataSet.valore.split("_").length>k;k++)
                        arr[k]=Number(assey[j].dataSet.valore.split("_")[k].toString());
                    data={ data: arr, label: assey[j].dataSet.commento };
                    this.datasets.push(data);
                    
                }
            }
        }
      

    }

    public chartClicked({ event, active }: { event: MouseEvent, active: {}[] }): void {
        //console.log(event, active);
      }
    
    public chartHovered({ event, active }: { event: MouseEvent, active: {}[] }): void {
        //console.log(event, active);
    }

    addsetcollapse() {
        if (this.isAddsetCollapsed === false) {
          this.closeAll();
          this.isAddsetCollapsed = true;
        } else { this.isAddsetCollapsed = false; }
      }

    optioncollapse() {
    if (this.isOptionCollapsed === false) {
        this.closeAll();
        this.isOptionCollapsed = true;
    } else { this.isOptionCollapsed = false; }
    }

    exportcollapse() {
    if (this.isExportCollapsed === false) {
        this.closeAll();
        this.isExportCollapsed = true;
    } else { this.isExportCollapsed = false; }
    }

    closeAll(){
    this.isAddsetCollapsed = false;
    this.isOptionCollapsed = false;
    this.isExportCollapsed = false;
    }

    esportaPNG(){
        console.warn(this.dom.toDataURL("image/png;base64"));
    }

}