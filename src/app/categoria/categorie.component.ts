import { Component, OnInit } from '@angular/core';
import { CategoriaService } from 'src/service/categoria.service';
import { CategoriaDTO } from 'src/dto/categoriadto';
import { Usertype } from 'src/dto/usertype';

@Component({
  selector: 'app-categorie',
  templateUrl: './categorie.component.html',
  styleUrls: ['./categorie.component.css']
})
export class CategorieComponent implements OnInit {

  public usertype: string;
  categorie: CategoriaDTO[];
  categoriainsert: CategoriaDTO = new CategoriaDTO();

  constructor(private service: CategoriaService) { }

  ngOnInit(): void {
    this.getCategoria();
    this.usertype = localStorage.getItem('usertype').toString();
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
    let x = this.categoriainsert.nome.trim();
    if(x != null) {
      if(x !== "") {
        this.service.insert(categoria).subscribe(() => this.getCategoria());
      }
    }
  }


}
