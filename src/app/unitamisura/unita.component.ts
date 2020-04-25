import { Component, OnInit, Input} from '@angular/core';
import { UnitaMisuraDTO } from 'src/dto/unitamisura.dto';
import { UnitaService } from 'src/service/unita.service';
import { CategoriaService } from 'src/service/categoria.service';
import { CategoriaDTO } from 'src/dto/categoria.dto';
import { faTrash, faPencilAlt, faExternalLinkAlt } from '@fortawesome/free-solid-svg-icons';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-unita',
  templateUrl: './unita.component.html',
  styleUrls: ['./unita.component.css']
})
export class UnitaComponent implements OnInit {

  showDiv: boolean = false;
  isAdmin: boolean;
  mostra = faExternalLinkAlt;
  cancella = faTrash;
  modifica = faPencilAlt;
  categories: CategoriaDTO[];
  newCategoria: CategoriaDTO = new CategoriaDTO(null, null);
  UnitaByCategoria: UnitaMisuraDTO[];
  newUnita: UnitaMisuraDTO = new UnitaMisuraDTO(null, null, null);

  constructor(private service: UnitaService, private catService: CategoriaService) {
    this.isAdmin = JSON.parse(sessionStorage.getItem('identity') || localStorage.getItem('identity')).authorities.indexOf("ROLE_ADMIN")!=-1;
   }

  ngOnInit(): void {
    this.getCategoria();
  }

  getCategoria(){
    this.service.getCategoria().subscribe(categories => this.categories = categories);
  }

  delCategoria(categoria: CategoriaDTO){
    this.service.deleteCategoria(categoria.id).subscribe(() => this.getCategoria());
  }

  updateCat(categoria: CategoriaDTO){
    this.service.updateCategoria(categoria).subscribe(() => this.getCategoria());
  }

  getunitaByCategoria(idcliccato: number) {
  this.service.getAllByCategoria(idcliccato).subscribe(UnitaByCategoria =>{
    this.UnitaByCategoria = UnitaByCategoria;
    this.showDiv = true;
  });

  }

  delete(unitamisura: UnitaMisuraDTO){
    this.service.delete(unitamisura.id).subscribe(() => this.getunitaByCategoria(unitamisura.categoriaId));
  }

  update(unitamisura: UnitaMisuraDTO){
    this.service.update(unitamisura).subscribe(() => this.getunitaByCategoria(unitamisura.categoriaId));
  }

  insert(){
    let x = this.newUnita.nome.trim();
    if(x != null){
      if(x != ""){
        this.service.insert(this.newUnita).subscribe(() => this.getunitaByCategoria(this.newUnita.categoriaId));
      }
    }

  }

  insertCat(){
    let x = this.newCategoria.nome.trim();
    if(x != null){
      if(x !== ""){
        this.service.insertCat(this.newCategoria).subscribe(() => this.getCategoria());
      }
    }



  }





}
