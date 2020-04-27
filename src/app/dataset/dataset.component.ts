import { Component, OnInit } from '@angular/core';
import { DataSetService } from 'src/service/DataSetService';
import { DataSetDTO } from 'src/dto/dataSet.dto';
import { UserDTO } from 'src/dto/user.dto';
import { CategoriaDTO } from 'src/dto/categoria.dto';
import { UnitaService } from 'src/service/unita.service';
import { UnitaMisuraDTO } from 'src/dto/unitamisura.dto';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-dataset',
  templateUrl: './dataset.component.html',
  styleUrls: ['./dataset.component.css']
})
export class DatasetComponent implements OnInit {
dataset : DataSetDTO = new DataSetDTO(null,null,"","",null,null);
ListaDatasetByUser : DataSetDTO[];
pathModify : string;
del : number = 0;
createForm : FormGroup;
err : number = 0;

ListCategoria : CategoriaDTO[];
ListUnita : UnitaMisuraDTO[];
ListaFiltrata : UnitaMisuraDTO[];
ListaFiltrata2 : UnitaMisuraDTO[];

userid = JSON.parse(localStorage.getItem('identity') || sessionStorage.getItem('identity')).id;


//recuperare il numero di datasets con lo stesso titolo per ogni utente 
n : [];
Ntitolo = (titolo)=> {
  this.n=[];
  return this.ListaDatasetByUser.filter(element =>{  return element.titolo == titolo });
}

  constructor(private service:DataSetService, private serviceum : UnitaService) {
    this.createForm = new FormGroup({
      cat : new FormControl(),
      cat2: new FormControl(),
      ump :  new FormControl(),
      ums :  new FormControl(),
      tit : new FormControl(),
    });
  }

  ngOnInit(){
    this.dataset.idUser = this.userid;
    this.serviceum.getCategoria().subscribe(nome => this.ListCategoria=nome);
    this.serviceum.getAll().subscribe(nome => this.ListUnita=nome);
    //this.service.getAll().subscribe(datasets => this.ListDataset=datasets);
    this.service.getDatasetByUser(this.dataset.idUser).subscribe(Listavalori => this.ListaDatasetByUser=Listavalori);
    this.pathModify = "../datasetmodify";
  }

  createDataset(){
    this.service.insert(this.dataset).subscribe();
    //alert(JSON.stringify(this.dataset));
  }

  filtra(categoriacliccata){
    this.ListaFiltrata = this.ListUnita.filter(x => x.categoriaId == categoriacliccata);
  }

  filtra2(categoriacliccata2){
    this.ListaFiltrata2 = this.ListUnita.filter(y => y.categoriaId == categoriacliccata2);
  }

  dele(d : number){
    this.del=d;
  }

  // deleteDS(d : number){
  //   this.service.delete(this.utId,this.del).subscribe(() => this.caricaDS(this.utId));
  //   this.del=0;
  // }

  createDS(formValue){
    let dtop = new DataSetDTO(null,String(formValue.tit),"",null,this.dataset.idUser,Number(formValue.ump));
    let dtop2 = new DataSetDTO(null,String(formValue.tit),"",null,this.dataset.idUser,Number(formValue.ums));
    let nuovo : boolean = true;
    let i : number = 0 ;
    if(formValue!=null && formValue.cat!=null && formValue.ump!=null && formValue.tit!=null){
      while(this.ListaDatasetByUser.length>0 && this.ListaDatasetByUser.length>i && nuovo){
        if(this.ListaDatasetByUser[i].titolo==formValue.tit)
          nuovo=false;
        i++;
      }
      if(nuovo){
        this.service.insert(dtop).subscribe();
        if(formValue.cat2!=null && formValue.ums!=null){
            this.service.insert(dtop2).subscribe();
          }else{
            this.err=1;
          }
        
      }else
      this.err=2;
    }else
    this.err=3;
    this.createForm.reset();
  }

}
