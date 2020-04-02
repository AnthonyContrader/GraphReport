import { Component, OnInit } from '@angular/core';
import { GraphService } from 'src/service/GraphService';
import { GraphDTO } from 'src/dto/GraphDTO';
import { DataSetDTO } from 'src/dto/DataSetDTO';
import { FormGroup, FormControl } from '@angular/forms';
import { UserDTO } from 'src/dto/userdto';
import { mtmDTO } from 'src/dto/mtmDTO';
import { Router } from '@angular/router';


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
    public owner : boolean;

    constructor(private service : GraphService, private router : Router){
        this.formCrea = new FormGroup({
            titolo : new FormControl(),
            ds : new FormControl(),
            x : new FormControl({value:null,disabled:true}),
            y : new FormControl({value:null,disabled:true}),
            z : new FormControl({value:null,disabled:true})
        });
    }

    ngOnInit(){
        if(this.usertype=="ADMIN")
            this.service.getUtenti().subscribe(x => this.utList=x);
        this.service.getDsList(this.utLogged).subscribe(x => this.dsList=x);
        this.init(this.utLogged);
    }
    
    init(id : number){
        this.service.getGraphListByUser(id).subscribe(x => {
            this.graphList=x;
            if(id==this.utLogged)
                this.owner=true;
            else
                this.owner=false;
        });
    }

    caricaDS(id : number){
        this.init(id);
    }

    caricaUM(cat){
        if(cat!="null"){
            this.service.getAssiList(this.utLogged,cat).subscribe(x => this.assiList=x);
            this.formCrea.get('x').enable();
            this.formCrea.get('y').enable();
            this.formCrea.get('z').enable();
        }else{
            this.formCrea.reset();
            this.assiList=null;
            this.formCrea.get('x').disable();
            this.formCrea.get('y').disable();
            this.formCrea.get('z').disable();
        }
    }

    creaGraph(form){
        if(form!=null && form.titolo!=null && form.titolo.toString().trim()!="" && form.ds!=null && form.x!=null && form.y!=null){
            let t = 0;
            if(form.z!=null)
                t = 6;
            let g = new GraphDTO(null,form.titolo.toString().trim(),0,t,"top",false,false);
            this.service.insert(g).subscribe(graph=>{
                let d = new mtmDTO(null,form.x,graph.id,"x");
                let dt = new mtmDTO(null,form.y,graph.id,"y");
                let dtz : mtmDTO;
                let dto : mtmDTO[];
                if( form.z!=null){
                    dtz = new mtmDTO(null,form.z,graph.id,"z");
                    dto = [d,dt,dtz];
                }else{
                    dto  = [d,dt];
                }
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

    toView(id : Number){
        let path : string;
        if(this.usertype=="ADMIN")
            path="/admin-dashboard";
        else
            path="/utente-dashboard";
        this.router.navigate([path+'/graphModify',id,this.owner]);
    }

}