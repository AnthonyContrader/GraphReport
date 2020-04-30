import { Component, OnInit } from '@angular/core';
import { DataSetService } from 'src/service/DataSetService';
import { DataSetDTO } from 'src/dto/dataSet.dto';
import { UserDTO } from 'src/dto/user.dto';
import { CategoriaDTO } from 'src/dto/categoria.dto';
import { UnitaService } from 'src/service/unita.service';
import { UnitaMisuraDTO } from 'src/dto/unitamisura.dto';
import { FormGroup, FormControl } from '@angular/forms';
import { UserService } from 'src/service/user.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-dataset',
  templateUrl: './dataset.component.html',
  styleUrls: ['./dataset.component.css']
})
export class DatasetComponent implements OnInit {
dataset : DataSetDTO = new DataSetDTO(null,null,"","",null,null);
ListaDatasetByUser : DataSetDTO[];
matrice : string[][] = [];
pathModify : string;
del : string = '';
createForm : FormGroup;
err : number = 0;
loaded : boolean;
isAdmin : boolean = JSON.parse(localStorage.getItem('identity') || sessionStorage.getItem('identity')).authorities.indexOf("ROLE_ADMIN")!=-1;
tit : string;

ListCategoria : CategoriaDTO[];
ListUnita : UnitaMisuraDTO[];
ListaFiltrata : UnitaMisuraDTO[];
ListaFiltrata2 : UnitaMisuraDTO[];

utList : UserDTO[];
userid = JSON.parse(localStorage.getItem('identity') || sessionStorage.getItem('identity')).id;


//recuperare il numero di datasets con lo stesso titolo per ogni utente 
n : [];
Ntitolo = (titolo)=> {
  this.n=[];
  return this.ListaDatasetByUser.filter(element =>{  return element.titolo == titolo });
}
 
findUmNome = (id)=> { 
  return this.ListUnita.find(x => x.id == id).nome;
}



  constructor(private route: ActivatedRoute, private service:DataSetService, private serviceum : UnitaService, private serviceut : UserService) {
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
    this.serviceut.getAll().subscribe(utente => this.utList= utente);
    //this.service.getAll().subscribe(datasets => this.ListDataset=datasets);
    this.service.getDatasetByUser(this.dataset.idUser).subscribe(Listavalori => this.ListaDatasetByUser=Listavalori);
    this.pathModify = "../datasetmodify";
    this.route.queryParams.subscribe(x => this.tit = x["id"]);
    this.init().then(x=>{this.loaded=true;});
  }

  init(){
    return new Promise ((response,rejects)=>{this.service.getDatasetByUser(this.userid).subscribe(x => {
        this.ListaDatasetByUser=x;
        for(let i=0; this.ListaDatasetByUser.length>i; i++)
            this.matrice[i]=this.ListaDatasetByUser[i].valori.split("_");
        response(true);
    })});
}

  createDataset(){
    this.service.insert(this.dataset).subscribe();
    //alert(JSON.stringify(this.dataset));
  }

  caricaDS(id : number, err : number = 0){
    this.service.getDatasetByUser(id).subscribe(x => this.ListaDatasetByUser = x);
    this.err = err;
    this.userid=id;
  }

  filtra(categoriacliccata){
    this.ListaFiltrata = this.ListUnita.filter(x => x.categoriaId == categoriacliccata);
  }

  filtra2(categoriacliccata2){
    this.ListaFiltrata2 = this.ListUnita.filter(y => y.categoriaId == categoriacliccata2);
  }

  dele(d : string){
    this.del=d;
  }

  deleteDS(){
    this.service.deleteDS(this.userid,this.del).subscribe(x => 
      this.service.getDatasetByUser(this.userid).subscribe(x => this.ListaDatasetByUser = x)
      );
    this.del='';
  }

  createDS(formValue){
    let dtop = new DataSetDTO(null,String(formValue.tit),"",null,this.dataset.idUser,Number(formValue.ump));
    let dtop2 = new DataSetDTO(null,String(formValue.tit),"",null,this.dataset.idUser,Number(formValue.ums));
    let nuovo : boolean = true;
    let i : number = 0 ;
    let z=String(formValue.tit).trim(); 
    if(z!=null && z!='' && z!='null'){
    if(formValue!=null && formValue.cat!=null && formValue.ump!=null && formValue.cat2!=null && formValue.ums!=null){
      while(this.ListaDatasetByUser.length>0 && this.ListaDatasetByUser.length>i && nuovo){
        if(this.ListaDatasetByUser[i].titolo==formValue.tit)
          nuovo=false;
        i++;
      }
      if(nuovo){
        this.service.insert(dtop).subscribe(z => {
          this.service.insert(dtop2).subscribe(x => {
            this.service.getDatasetByUser(this.userid).subscribe(x => this.ListaDatasetByUser = x);
          });
        });
        
      }else{
          this.err=1;
      }
      }else
      this.err=2;
    }else
    this.err=3;
    
    this.createForm.reset();
  }

  addComm(n : number){
    let x = window.prompt("Digita nuovo commento","");
    this.ListaDatasetByUser[n].commento=x;
    this.service.update(this.ListaDatasetByUser[n]).subscribe(()=>this.init());
  }
  
  closerror(){
    this.err=0;
}

}
