import { Component, OnInit, Input} from '@angular/core';
import { UnitaMisuraDTO } from 'src/dto/unitamisura.dto';
import { UnitaService } from 'src/service/unita.service';
import { CategoriaService } from 'src/service/categoria.service';
import { CategoriaDTO } from 'src/dto/categoria.dto';


@Component({
  selector: 'app-unita',
  templateUrl: './unita.component.html',
  styleUrls: ['./unita.component.css']
})
export class UnitaComponent implements OnInit {

  categories: CategoriaDTO[];
  insertCat: CategoriaDTO = new CategoriaDTO();
  listUnita: UnitaMisuraDTO[];
  newUnita: UnitaMisuraDTO = new UnitaMisuraDTO();

  constructor(private service: UnitaService, private catService: CategoriaService) { }

  ngOnInit(): void {
    this.getunitaMisura();
  }

  getCategoria(){
    this.catService.getAll().subscribe(categories => this.categories = categories);
  }

  getunitaMisura() {
  this.service.getAll().subscribe(listUnita => this.listUnita = listUnita);
  }

  delete(unitamisura: UnitaMisuraDTO){
    this.service.delete(unitamisura.id).subscribe(() => this.getunitaMisura());
  }

  update(unitamisura: UnitaMisuraDTO){
    this.service.update(unitamisura).subscribe(() => this.getunitaMisura());
  }

  insert(unitamisura: UnitaMisuraDTO){
    this.service.insert(unitamisura).subscribe(() => this.getunitaMisura());
  }

  //newCat(categoria: CategoriaDTO){
   // this.service.newCat(categoria).subscribe(() => this.getCategoria());
  //}


}
