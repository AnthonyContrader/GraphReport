import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from '@angular/router';
import { DataSetService } from 'src/service/DataSetService';
import { DataSetDTO } from 'src/dto/dataSet.dto';
import { FormGroup, FormControl } from '@angular/forms';
import { rejects } from 'assert';
import { UnitaMisuraDTO } from 'src/dto/unitamisura.dto';

@Component({
    selector: 'app-datasetmodify',
    templateUrl: './dataset-modify.component.html',
    styleUrls: ['./dataset-modify.component.css', '../dataset.component.css']
})

export class DataSetModifyComponent implements OnInit{

    public idUt : number;
    public cat : number;
    public umList : UnitaMisuraDTO[];
    public dataSet : DataSetDTO[];
    public delForm : FormGroup;
    public addForm : FormGroup;
    public loaded : boolean;
    public matrice : string[][] = [];
    public needToSave : boolean = false;
    public err : number =0;

    constructor(private route: ActivatedRoute, private service:DataSetService){
        this.delForm = new FormGroup({
            del : new FormControl()
          });
        this.addForm = new FormGroup({
            add : new FormControl()
          });
    }

    ngOnInit(){
        this.loaded=false;
        this.idUt = Number(localStorage.getItem("idUser"));
        this.route.queryParams.subscribe(x => this.cat = x["id"]);
       // this.service.getListUnit().subscribe(x => this.umList = x);
        this.init().then(x=>{this.loaded=true;});
    }

    init(){
        return new Promise ((response,rejects)=>{this.service.getDataSet(this.idUt,this.cat).subscribe(x => {
            this.dataSet=x;
            for(let i=0; this.dataSet.length>i; i++)
                this.matrice[i]=this.dataSet[i].valori.split("_");
            response(true);
        })});
    }

    delete(form){
        if(form.del!=null){
            if(this.matrice.length>2){
                this.service.delete(form.del).subscribe(() => this.init());
                this.delForm.reset();
            }else
                this.err=1;
        }
    }

    add(form){
        if(form.add!=null){
            let valori : string = "";
            for(let i=0; this.dataSet[0].valori.split("_").length>i; i++){
                valori+=" _";
            }
            valori = valori.substring(0,valori.length-1);
            //CONTROLLARE DTO
         //   let dto = new DataSetDTO(null,null,valori,form.add,Number(localStorage.getItem('idUser')),this.idUt);
        //  this.service.insert(dto).subscribe(x => this.init());
            this.addForm.reset();
        }   
    }

    addrow(){
        for(let i =0; this.dataSet.length>i ; i++){
            this.dataSet[i].valori+="_";
        }
       // this.service.updateDS(this.dataSet).subscribe(()=>this.init());
    }

    delrow(index : number){
        let val : string;
        for(let i =0; this.dataSet.length>i ; i++){
            let x=this.dataSet[i].valori.split("_");
            val="";
            for(let j=0; x.length>j; j++){
                if(j!=index)
                    if(x[j]=="")
                        val += " _";
                    else
                        val += x[j]+"_";
            }
            this.dataSet[i].valori=val.substring(0,val.length-1);
        }
       // this.service.updateDS(this.dataSet).subscribe(()=>this.init());
        
    }

    salva(){
        if(this.needToSave){
            let val : string;
            for(let i=0;this.matrice.length>i;i++){
                val = "";
                for(let j=0;this.matrice[i].length>j;j++){
                    if( this.matrice[i][j]!=null)
                        if(this.matrice[i][j].replace(/ /g,"")=="")
                            val+=" _";
                        else
                            val+=this.matrice[i][j].replace(/ /g,"")+"_";
                }
                this.dataSet[i].valori=val.substring(0,val.length-1);
            }
           // this.service.updateDS(this.dataSet).subscribe();
            this.needToSave=false;
        }
    }

    needtosave(){
        this.needToSave=true;
    }

    closerror(){
        this.err=0;
    }

    addComm(n : number){
        let x = window.prompt("Digita nuovo commento","");
        this.dataSet[n].commento=x;
        this.service.update(this.dataSet[n]).subscribe(()=>this.init());
    }
}