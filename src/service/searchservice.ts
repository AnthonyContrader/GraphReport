import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { HttpClient } from '@angular/common/http';
import { CategoriaDTO } from 'src/dto/categoriadto';
import { Observable } from 'rxjs';
import { DataSetDTO } from 'src/dto/DataSetDTO';
import { GraphDTO } from 'src/dto/GraphDTO';
import { UnitaMisuraDTO } from 'src/dto/unitamisuradto';
import { UserDTO } from 'src/dto/userdto';

@Injectable({
  providedIn: 'root'
})
export class SearchService extends AbstractService<CategoriaDTO> {

  constructor(http: HttpClient) {

    super(http);
    this.type = '';
  }

  findOnCategorie(daCercare: string): Observable<CategoriaDTO[]> {
    return this.http.get<any>('http://localhost:8080/categoria/findAll?cerca=' + daCercare);
  }

  findOnDataSet(daCercare: string): Observable<DataSetDTO[]> {
    return this.http.get<any>('http://localhost:8080/dataset/findAll?cerca=' + daCercare);
  }

  findOnGraph(daCercare: string): Observable<GraphDTO[]> {
    return this.http.get<any>('http://localhost:8080/graph/findAll?cerca=' + daCercare);
  }

  findOnUnitaMisura(daCercare: string): Observable<UnitaMisuraDTO[]> {
    return this.http.get<any>('http://localhost:8080/unitamisura/findAll?cerca=' + daCercare);
  }

  findOnUser(daCercare: string): Observable<UserDTO[]> {
    return this.http.get<any>('http://localhost:8080/user/findAll?cerca=' + daCercare);
  }



}
