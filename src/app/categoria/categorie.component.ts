import { Component, OnInit } from '@angular/core';
import { CategoriaService } from 'src/service/categoria.service';
import { CategoriaDTO } from 'src/dto/categoriadto';

@Component({
  selector: 'app-categorie',
  templateUrl: './categorie.component.html',
  styleUrls: ['./categorie.component.css']
})
export class CategorieComponent implements OnInit {

  categorie: CategoriaDTO[];
  categoriainsert: CategoriaDTO = new CategoriaDTO();

  constructor(private service: CategoriaService) { }

  ngOnInit(): void {
    this.getCategoria();
  }

  getCategoria() {
    this.service.getAll().subscribe(categorie => this.categorie = categorie);
  }

  delete(categoria: CategoriaDTO) {
    this.service.delete(categoria.id).subscribe(() => this.getCategoria());
  }

  update(categoria: CategoriaDTO) {
    this.service.update(categoria).subscribe(() => this.getCategoria());
  }

  insert(categoria: CategoriaDTO) {
    this.service.insert(categoria).subscribe(() => this.getCategoria());
  }

  clear() {
    this.categoriainsert = new CategoriaDTO();
  }

}
