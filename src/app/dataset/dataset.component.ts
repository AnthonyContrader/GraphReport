import { Component, OnInit } from '@angular/core';
import { DataSetService } from 'src/service/DataSetService';
import { DataSetDTO } from 'src/dto/dataSet.dto';
import { UserDTO } from 'src/dto/user.dto';
import { CategoriaDTO } from 'src/dto/categoria.dto';
import { UnitaService } from 'src/service/unita.service';
import { UnitaMisuraDTO } from 'src/dto/unitamisura.dto';

@Component({
  selector: 'app-dataset',
  templateUrl: './dataset.component.html',
  styleUrls: ['./dataset.component.css']
})
export class DatasetComponent implements OnInit {
dataset : DataSetDTO = new DataSetDTO(null,null,"","",null,null);
ListCategoria : CategoriaDTO[];
ListUnita : UnitaMisuraDTO[];
ListaFiltrata : UnitaMisuraDTO[];
userid: number = JSON.parse(localStorage.getItem('identity') || sessionStorage.getItem('identity')).id;

  
  constructor(private service:DataSetService, private serviceum : UnitaService) {
  }

  ngOnInit(){
    this.dataset.idUser = this.userid;
    this.serviceum.getCategoria().subscribe(nome => this.ListCategoria=nome);
    this.serviceum.getAll().subscribe(nome => this.ListUnita=nome);
  }

  createDataset(){
    this.service.insert(this.dataset).subscribe();
    //alert(JSON.stringify(this.dataset));
  }

  filtra(categoriacliccata){
    this.ListaFiltrata = this.ListUnita.filter(x => x.categoriaId == categoriacliccata);
  }

}
