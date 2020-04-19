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

  getAllCategoria(): Observable<CategoriaDTO[]> {
    return this.http.get<any>(SERVER_API_URL + this.microservicesPath + '/categorias/getAllCategorias');
  }


  /*findAll(daCercare: string): Observable<UnitaMisuraDTO[]> {
    return this.http.get<any>('http://localhost:8080/unitamisura/findAll?cerca=' + daCercare);
  }*/
}
