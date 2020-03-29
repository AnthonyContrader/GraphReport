import { Component, OnInit } from '@angular/core';
import { DataSetService } from 'src/service/DataSetService';
import { DataSetDTO } from 'src/dto/DataSetDTO';
import { UnitaMisuraDTO } from 'src/dto/unitamisuradto';
import { CategoriaDTO } from 'src/dto/categoriadto';
import { UserDTO } from 'src/dto/userdto';
import { FormGroup, FormControl} from '@angular/forms';

@Component({
  selector: 'app-dataset',
  templateUrl: './dataset.component.html',
  styleUrls: ['./dataset.component.css']
})
export class DatasetComponent implements OnInit {

  public utId : number;
  public idDaVedere : number;
  public usertype : string;
  public dtoList : DataSetDTO[];
  public catList : CategoriaDTO[];
  public umList : UnitaMisuraDTO[];
  public utList : UserDTO[];
  public nDS : DataSetDTO[];
  public nDSlogged : DataSetDTO[];
  public createForm : FormGroup;
  public err : number = 0;
  public first : boolean = false;
  public del : number = 0;

  constructor(private service:DataSetService) {
    this.createForm = new FormGroup({
      cat : new FormControl(),
      ump :  new FormControl(),
      ums :  new FormControl()
    });
   }

  ngOnInit(){
    this.utId = Number(localStorage.getItem('idUser').toString());
    this.idDaVedere=this.utId;
    this.usertype = localStorage.getItem('usertype').toString();
    this.caricaDS(this.utId);
    this.service.getListCat().subscribe(x => this.catList = x);
    this.service.getListUnit().subscribe(x => this.umList = x);
    if(this.usertype=="ADMIN"){
      this.service.getListUser().subscribe(x => this.utList = x);
    }
  }

  caricaDS(id : number, err : number = 0){
    this.service.countDS(this.utId).subscribe(x => this.nDSlogged = x);
    this.service.countDS(id).subscribe(x => this.nDS = x);
    this.service.getAllByUser(id).subscribe(x => this.dtoList = x);
    this.err = err;
    this.idDaVedere=id;
  }

  createDS(formValue){
    let dtop = new DataSetDTO(0,this.utId,Number(formValue.cat),null,Number(formValue.ump),null," ","");
    let nuovo : boolean = true;
    let i : number = 0 ;
    if(formValue!=null && formValue.cat!=null && formValue.ump!=null && formValue.ums!=null){
      while(this.nDSlogged.length>0 && this.nDSlogged.length>i && nuovo){
        if(this.nDSlogged[i].categoria==formValue.cat)
          nuovo=false;
        i++;
      }
      if(nuovo){
        this.service.createDS(dtop).subscribe(x => {
          if(x==0){
            dtop.unitaMisura=formValue.ums;
            this.service.createDS(dtop).subscribe(y => {this.caricaDS(this.utId,y)});
          }else{
            this.err=1;
          }
        });
      }else
      this.err=2;
    }else
    this.err=3;
    this.createForm.reset();
  }

  dele(d : number){
    this.del=d;
  }

  deleteDS(d : number){
    this.service.deleteDS(this.utId,this.del).subscribe(() => this.caricaDS(this.utId));
    this.del=0;
  }

  closerror(){
    this.err=0;

  }

}
