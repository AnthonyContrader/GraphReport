import { Component, OnInit } from '@angular/core';
import { GraphService } from 'src/service/GraphService';
import { GraphDTO } from 'src/dto/GraphDTO';
import { DataSetDTO } from 'src/dto/DataSetDTO';
import { FormGroup, FormControl } from '@angular/forms';
import { UserDTO } from 'src/dto/userdto';
import { mtmDTO } from 'src/dto/mtmDTO';


@Component({
    selector: "app-graph",
    templateUrl: "./graph.component.html",
    styleUrls: ["./graph.component.css"]
})
export class GraphComponent implements OnInit{

    public graphList : GraphDTO[];
    public dsList : DataSetDTO[];
    public assiList : DataSetDTO[];
    public formCrea : FormGroup;
    public utList : UserDTO[];
    public utLogged : number = Number(localStorage.getItem('idUser'));
    public usertype : string = localStorage.getItem('usertype').toString() ;
    public abilita : boolean = false;
    public err : number =0;
    public conf : number =0;
    public idDaVis : number = Number(localStorage.getItem('idUser'));

    constructor(private service : GraphService){
        this.formCrea = new FormGroup({
            titolo : new FormControl(),
            ds : new FormControl(),
            x : new FormControl({value:null,disabled:true}),
            y : new FormControl({value:null,disabled:true})
        });
    }

    ngOnInit(){
        if(this.usertype=="ADMIN")
            this.service.getUtenti().subscribe(x => this.utList=x);
        this.service.getDsList(this.utLogged).subscribe(x => this.dsList=x);
        this.init(this.utLogged);
    }
    
    init(id : number){
        this.service.getGraphListByUser(id).subscribe(x => {this.graphList=x;this.idDaVis=id});
    }

    caricaDS(id : number){
        this.init(id);
    }

    caricaUM(cat){
        if(cat!="null"){
            this.service.getAssiList(this.utLogged,cat).subscribe(x => this.assiList=x);
            this.formCrea.get('x').enable();
            this.formCrea.get('y').enable();
        }else{
            this.formCrea.reset();
            this.assiList=null;
            this.formCrea.get('x').disable();
            this.formCrea.get('y').disable();
        }
    }

    creaGraph(form){
        if(form!=null && form.titolo!=null && form.titolo.toString().trim()!="" && form.ds!=null && form.x!=null && form.y!=null){
            let g = new GraphDTO(null,form.titolo.toString().trim(),0,0,"top_center",false,false);
            this.service.insert(g).subscribe(graph=>{
                let d = new mtmDTO(null,form.x,graph.id,"x");
                let dt = new mtmDTO(null,form.y,graph.id,"y");
                let dto : mtmDTO[] = [d,dt];
                this.service.insertMtM(dto).subscribe(()=>this.init(this.utLogged));
            });
            
        }else{
            this.err=1;
        }
        this.formCrea.reset();
        this.assiList=null;
        this.formCrea.get('x').disable();
        this.formCrea.get('y').disable();
    }

    closerror(){
        this.err=0;
    }

    confdel(id : number){
        this.conf=id;
    }

    delete(id : number){
        this.service.delete(id).subscribe(()=>{this.init(this.utLogged);this.conf=0;});
    }

}