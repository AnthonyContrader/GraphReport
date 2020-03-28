import { Component, OnInit } from '@angular/core';
import { DataSetService } from 'src/service/DataSetService';
import { DataSetDTO } from 'src/dto/DataSetDTO';
import { UnitaMisuraDTO } from 'src/dto/unitamisuradto';
import { CategoriaDTO } from 'src/dto/categoriadto';
import { UserDTO } from 'src/dto/userdto';
import { FormBuilder} from '@angular/forms';

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
  public createForm;

  constructor(private service:DataSetService,private formBuilder: FormBuilder) {
    this.createForm = this.formBuilder.group({
      cat : '',
      ump : '',
      ums : ''
    });
   }

  ngOnInit(){
    this.utId = Number(localStorage.getItem('idUser').toString());
    this.usertype = localStorage.getItem('usertype').toString();
    this.caricaDS(this.utId);
    this.service.getListCat().subscribe(x => this.catList = x);
    this.service.getListUnit().subscribe(x => this.umList = x);
    if(this.usertype=="ADMIN"){
      this.service.getListUser().subscribe(x => this.utList = x);
    }
  }

  caricaDS(id : number){
    this.service.countDS(id).subscribe(x => this.nDS = x);
    this.service.getAllByUser(id).subscribe(x => this.dtoList = x);
  }

  createDS(formValue){
    let dtop = new DataSetDTO(0,this.utId,Number(formValue.cat),null,Number(formValue.ump),null," _","");
    let dtos = new DataSetDTO(0,this.utId,Number(formValue.cat),null,Number(formValue.ums),null," _","");
    this.service.createDS(dtop,dtos);
    
  }

}
