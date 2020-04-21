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
  newCategoria: CategoriaDTO = new CategoriaDTO(null, null);
  listUnita: UnitaMisuraDTO[];
  newUnita: UnitaMisuraDTO = new UnitaMisuraDTO(null, null, null);

  constructor(private service: UnitaService, private catService: CategoriaService) { }

  ngOnInit(): void {
    this.getunitaMisura();
    this.getCategoria();
  }

  getCategoria(){
    this.service.getCategoria().subscribe(categories => this.categories = categories);
  }

  delCategoria(categoria: CategoriaDTO){
    this.service.deleteCategoria(categoria.id).subscribe(() => this.getCategoria());
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

  insert(){
    this.service.insert(this.newUnita).subscribe(() => this.getunitaMisura());
  }

  insertCat(){
    this.service.insertCat(this.newCategoria).subscribe(() => this.getCategoria());
  }


}
