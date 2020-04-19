import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { CategoriaDTO } from 'src/dto/categoria.dto';
import { HttpClient } from '@angular/common/http';



@Injectable({
  providedIn: 'root'
})

export class CategoriaService extends AbstractService<CategoriaDTO>{

  constructor(http:HttpClient){
    super(http);
    this.microservicesPath = '/categoria/api';
    this.generic = '/categorias';
  }


  /*findAll(daCercare: string): Observable<CategoriaDTO[]> {
    return this.http.get<any>('http://localhost:8080/categoria/findAll?cerca=' + daCercare);
  }*/

}
