import { Component, OnInit } from '@angular/core';
import { FontStyle } from 'src/dto/FontStyle';
import { TipoGrafico } from 'src/dto/TipoGrafico';
import { ActivatedRoute } from '@angular/router';
import { GraphService } from 'src/service/GraphService';

@Component({
    selector: "app-graph-modify",
    templateUrl: "./graph-modify.component.html",
    styleUrls: ["./graph-modify.component.css", "../graph.component.css"]
})
export class GraphModifyComponent implements OnInit{

    public idGraph : number;
    public owner : boolean;

    constructor(private service: GraphService, private route: ActivatedRoute){
        this.idGraph=Number(this.route.snapshot.paramMap.get('id'));
        this.owner=this.route.snapshot.paramMap.get('owner').toString()=="true";
    }

    ngOnInit(){

    }

}