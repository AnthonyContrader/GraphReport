import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { FontStyle } from 'src/dto/FontStyle';
import { TipoGrafico } from 'src/dto/TipoGrafico';
import { ActivatedRoute } from '@angular/router';
import { GraphService } from 'src/service/GraphService';
import { ChartDataSets, ChartOptions, PositionType } from 'chart.js';
import { Label, Color } from 'ng2-charts';
import { GraphDTO } from 'src/dto/GraphDTO';
import { mtmDTO } from 'src/dto/mtmDTO';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
    selector: "app-graph-modify",
    templateUrl: "./graph-modify.component.html",
    styleUrls: ["./graph-modify.component.css"]
})
export class GraphModifyComponent implements OnInit{

    keys = Object.keys;
    public enumTipoGrafico = TipoGrafico;
    public enumFont = FontStyle;

    @ViewChild("esporta") dom : ElementRef;
    @ViewChild("download") dwn : ElementRef;

    public err: number =0;

    public esportalink : string = ""; 
    public esportaname : string = "";
    
    public isAddsetCollapsed : boolean = true;
    public isOptionCollapsed : boolean = false;
    public isExportCollapsed : boolean = false;

    public idGraph : number;
    public owner : boolean;
    public graph : GraphDTO;
    public assiList : mtmDTO[];
    public pronto : boolean = false;
    public umList;
    public formCrea: FormGroup;

    public chartTitle : string;
    public chartLegendBool: boolean;
    public chartTitleBool: boolean;
    public chartParetoBool: boolean;
    public chartType: TipoGrafico;
    public chartFont: FontStyle;
    public chartTitlePos: PositionType = "top" ;
    public chartLegendPos: PositionType = "bottom" ;
    public datasets: ChartDataSets[] =[];
    public labels: Label[];
    public chartTitSize : number = 20;
    public colors: Color[] = [
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
        }
      ];
    public options: ChartOptions;
   
    constructor(private service: GraphService, private route: ActivatedRoute){
        this.formCrea = new FormGroup ({
            x : new FormControl({value:null}),
            y : new FormControl({value:null}),
            z : new FormControl({value:null})
        });
        this.idGraph=Number(this.route.snapshot.paramMap.get('id'));
        this.owner=this.route.snapshot.paramMap.get('owner').toString()=="true";
    }

    ngOnInit(){
        this.init();
    }

    init(){
            this.service.read(this.idGraph).subscribe(x=>{
                this.graph=x;
                this.service.getDSByGraph(this.idGraph).subscribe(y=>{
                    this.assiList=y;
                    this.service.getAssiList(Number(localStorage.getItem("idUser").toString()),this.assiList[0].dataSet.categoria).subscribe(x => this.umList=x);
                    this.settaGrafico();
                    this.pronto=true;
                })
            });
    }

    settaGrafico(){
        this.chartTitle=this.graph.titolo;
        this.chartLegendBool=this.graph.legenda;
        this.chartType=this.graph.tipografico;
        this.chartFont=this.graph.fontStyle;
        this.chartTitlePos=<PositionType>this.graph.posTitolo;
        //Pareto da fare
        this.options={
            responsive: true,
            title: {
                fontSize: this.chartTitSize,
                text: this.chartTitle,
                fontFamily: this.chartFont.toString(),
                position: this.chartTitlePos,
                display: this.chartTitleBool,
            },
            legend:{
                display: this.chartLegendBool,
                position: this.chartLegendPos,
            },
          }

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

        for(let j=0;assex.length>j;j++){
            let data : {} ;
            let arr : any[] = [];
             if(assez[j]!=undefined){
                for(let i=0;assex[j].dataSet.valore.split("_").length>i;i++){
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
                }else{
                    this.labels = assex[j].dataSet.valore.split("_"); 
                    for(let k=0; assey[j].dataSet.valore.split("_").length>k;k++)
                        arr[k]=Number(assey[j].dataSet.valore.split("_")[k].toString());
                    data={ type: this.chartType,data: arr, label: assey[j].dataSet.commento };
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

    esporta(ext : string){
        this.esportaname = this.graph.titolo;
        let canvas : HTMLCanvasElement = <HTMLCanvasElement>this.dom.nativeElement;
        this.esportalink=canvas.toDataURL("image/"+ext+";base64",1.0);
        setTimeout(() => {this.dwn.nativeElement.click();}, 500);
    }

    addSet(form){
        if(form.x!=null && form.y!=null){
            let d = new mtmDTO(null,form.x,this.graph.id,"x");
            let dt = new mtmDTO(null,form.y,this.graph.id,"y");
            let dto : mtmDTO[];
            if( !isNaN(Number(form.z))){
                let dtz : mtmDTO = new mtmDTO(null,form.z,this.graph.id,"z");
                dto = [d,dt,dtz];
            }else{
                dto  = [d,dt];
            }
            this.service.insertMtM(dto).subscribe(()=>this.init());
        }else{
            this.err=1;
        }
        this.formCrea.reset();
    }

    closerror(){
        this.err=0;
    }

    salvaOpzioni(){}
}