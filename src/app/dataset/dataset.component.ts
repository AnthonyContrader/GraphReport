import { Component, OnInit } from '@angular/core';
import { DataSetService } from 'src/service/DataSetService';
import { DataSetDTO } from 'src/dto/DataSetDTO';
import { UnitaMisuraDTO } from 'src/dto/unitamisuradto';
import { CategoriaDTO } from 'src/dto/categoriadto';

@Component({
  selector: 'app-dataset',
  templateUrl: './dataset.component.html',
  styleUrls: ['./dataset.component.css']
})
export class DatasetComponent implements OnInit {

  public dtoList : DataSetDTO[];
  public catList : CategoriaDTO[];
  public umList : UnitaMisuraDTO[];
  public nDS : DataSetDTO[];

  constructor(private service:DataSetService) {
    
   }

  ngOnInit(){
    this.service.getAllByUser().subscribe(dataList => this.dtoList = dataList);
    this.service.countDS().subscribe(nDS => this.nDS = nDS)
  }

}
