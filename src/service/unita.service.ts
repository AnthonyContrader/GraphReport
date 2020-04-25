import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { UnitaMisuraDTO } from 'src/dto/unitamisura.dto';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CategoriaDTO } from 'src/dto/categoria.dto';
import { SERVER_API_URL } from 'src/authJWT/app.constants';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';

@Injectable({
  providedIn: 'root'
})
export class UnitaService extends AbstractService<UnitaMisuraDTO>{

  constructor(http: HttpClient) {
    super(http);
    this.microservicesPath = '/unita/api';
    this.generic = '/unitamisuras';
  }

  getListNomi(list): Observable<UnitaMisuraDTO[]> {
    return this.http.post<any>(SERVER_API_URL + this.microservicesPath + '/unitamisurabynome', list);
  }

  getCategoria(): Observable<CategoriaDTO[]> {
    return this.http.get<any>(SERVER_API_URL + this.microservicesPath + '/categorias');
  }

  insertCat(categoria: CategoriaDTO): Observable<CategoriaDTO[]> {
    return this.http.post<any>(SERVER_API_URL + this.microservicesPath + '/categorias', categoria);
  }

  deleteCategoria(id: number): Observable<CategoriaDTO[]> {
    return this.http.delete<any>(SERVER_API_URL + this.microservicesPath + '/categorias/' + id);
  }

  updateCategoria(categoria: CategoriaDTO): Observable<CategoriaDTO[]> {
    return this.http.put<any>(SERVER_API_URL + this.microservicesPath + '/categorias', categoria);
  }

  getAllByCategoria(categoriaId: number): Observable<UnitaMisuraDTO[]>{
    return this.http.get<any>(SERVER_API_URL + this.microservicesPath + '/unitabycategoria/' + categoriaId);
  }
}
