import { Component, OnInit } from '@angular/core';
import { DataSetService } from 'src/service/DataSetService';
import { DataSetDTO } from 'src/dto/DataSetDTO';
import { UnitaMisuraDTO } from 'src/dto/unitamisuradto';
import { CategoriaDTO } from 'src/dto/categoriadto';
import { UserDTO } from 'src/dto/userdto';

@Component({
  selector: 'app-dataset',
  templateUrl: './dataset.component.html',
  styleUrls: ['./dataset.component.css']
})
export class DatasetComponent implements OnInit {

  public utId : number;
  public usertype : string;
  public dtoList : DataSetDTO[];
  public catList : CategoriaDTO[];
  public umList : UnitaMisuraDTO[];
  public utList : UserDTO[];
  public nDS : DataSetDTO[];

  constructor(private service:DataSetService) {
    
   }

  ngOnInit(){
    this.utId = Number(localStorage.getItem('idUser').toString());
    this.usertype = localStorage.getItem('usertype').toString();
    this.service.getAllByUser().subscribe(x => this.dtoList = x);
    this.service.countDS().subscribe(x => this.nDS = x);
    this.service.getListCat().subscribe(x => this.catList = x);
    this.service.getListUnit().subscribe(x => this.umList = x);
    if(this.usertype=="ADMIN"){
      this.service.getListUser().subscribe(x => this.utList = x);
    }
  }

}
