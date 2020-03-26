import { Component, OnInit } from '@angular/core';
import { CategoriaService } from 'src/service/categoria.service';
import { CategoriaDTO } from 'src/dto/categoriadto';

@Component({
  selector: 'app-categoria',
  templateUrl: './categoria.component.html',
  styleUrls: ['./categoria.component.css']
})
export class CategoriaComponent implements OnInit {

  categorie: CategoriaDTO [];
  categoriatoinsert: CategoriaDTO = new CategoriaDTO();

  constructor(private service: CategoriaService) { }

  ngOnInit() {
    this.getCategorie();
  }

  getCategorie() {
    this.service.getAll().subscribe(categorie => this.categorie = categorie);
  }

  delete(categoria: CategoriaDTO) {
    this.service.delete(categoria.id).subscribe(() => this.getCategorie());
  }

  update(categoria: CategoriaDTO) {
    this.service.update(categoria).subscribe(() => this.getCategorie());
  }

  insert(categoria: CategoriaDTO) {
    this.service.insert(categoria).subscribe(() => this.getCategorie());
  }

  clear() {
    this.categoriatoinsert = new CategoriaDTO();
  }

}
