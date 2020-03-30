import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from '@angular/router';
import { DataSetService } from 'src/service/DataSetService';
import { UnitaMisuraDTO } from 'src/dto/unitamisuradto';
import { DataSetDTO } from 'src/dto/DataSetDTO';
import { FormGroup, FormControl } from '@angular/forms';

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

    constructor(private route: ActivatedRoute, private service:DataSetService){
        this.delForm = new FormGroup({
            del : new FormControl()
          });
        this.addForm = new FormGroup({
            add : new FormControl()
          });
    }

    ngOnInit(){
        this.idUt = Number(localStorage.getItem('idUser'));
        this.route.queryParams.subscribe(x => {this.cat = x['id']});
        this.init();
    }

    init(){
        this.service.getListUnit().subscribe(x => this.umList = x);
        this.service.getDataSet(this.idUt,this.cat).subscribe(x => this.dataSet = x);
    }

    delete(form){
        if(form.del!=null){
            this.service.delete(form.del).subscribe(() => this.init());
            this.delForm.reset();
        }
    }

    add(form){
        if(form.add!=null){
            let valore : string;
            for(let ds of this.dataSet){
                valore=" _";
            }
            valore = valore.substring(0,valore.length-1);
            let dto = new DataSetDTO(0,Number(localStorage.getItem('idUser')),this.cat,null,form.add,null,valore,"");
            this.service.insert(dto).subscribe(x => this.init());
            this.addForm.reset();
        }   
    }

    addrow(){
        for(let i =0; this.dataSet.length>i ; i++){
            this.dataSet[i].valore+="_";
        }
        this.service.updateDS(this.dataSet).subscribe();
    }

    delrow(index : number){
        console.warn(index);
        let val : string;
        for(let i =0; this.dataSet.length>i ; i++){
            let x=this.dataSet[i].valore.split("_");
            val="";
            for(let j=0; x.length>j; j++){
                if(j!=index)
                    if(x[j]=="" || x[j]==null)
                        val += " _";
                    else
                        val += x[j]+"_";
            }
            this.dataSet[i].valore=val.substring(0,val.length-1);
        }
        this.service.updateDS(this.dataSet).subscribe();
    }
}