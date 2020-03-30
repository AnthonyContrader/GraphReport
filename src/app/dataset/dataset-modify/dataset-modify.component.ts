import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from '@angular/router';
import { DataSetService } from 'src/service/DataSetService';
import { UnitaMisuraDTO } from 'src/dto/unitamisuradto';
import { DataSetDTO } from 'src/dto/DataSetDTO';

@Component({
    selector: 'app-datasetmodify',
    templateUrl: './dataset-modify.component.html',
    styleUrls: ['./dataset-modify.component.css', '../dataset.component.css']
})

export class DataSetModifyComponent implements OnInit{

    public cat : number;
    public umList : UnitaMisuraDTO[];
    public dataSet : DataSetDTO[];

    constructor(private route: ActivatedRoute, private service:DataSetService){
        
    }

    ngOnInit(){
        this.route.queryParams.subscribe(x => {this.cat = x['id']});
        this.service.getListUnit().subscribe(x => this.umList = x);
        this.service.getDataSet(Number(localStorage.getItem('idUser')),this.cat).subscribe(x => this.dataSet = x);
    }

}