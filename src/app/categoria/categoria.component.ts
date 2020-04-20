import { Component, OnInit } from '@angular/core';
import { CategoriaDTO } from 'src/dto/categoria.dto';
import { CategoriaService } from 'src/service/categoria.service';

@Component({
  selector: 'app-categoria',
  templateUrl: './categoria.component.html',
  styleUrls: ['./categoria.component.css']
})
export class CategoriaComponent implements OnInit {

  categorie: CategoriaDTO[];
  newCategoria: CategoriaDTO = new CategoriaDTO(null,null);



  constructor(private service: CategoriaService) {

  }

  ngOnInit(): void {
    this.getCategoria();
  }

  getCategoria(){
    this.service.getAll().subscribe(categorie => this.categorie = categorie);
  }

  delete(categoria: CategoriaDTO){
    this.service.delete(categoria.id).subscribe(() => this.getCategoria());
  }

  update(categoria: CategoriaDTO){
    this.service.update(categoria).subscribe(() => this.getCategoria());
  }

  insert(categoria: CategoriaDTO){
    this.service.insert(categoria).subscribe(() => this.getCategoria());
  }

}
