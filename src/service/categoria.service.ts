import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { HttpClient } from '@angular/common/http';
import { CategoriaDTO } from 'src/dto/categoriadto';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CategoriaService extends AbstractService<CategoriaDTO> {

  constructor(http: HttpClient) {

    super(http);
    this.type = 'categoria';
  }

  findAll(daCercare: string): Observable<CategoriaDTO[]> {
    return this.http.get<any>('http://localhost:8080/categoria/findAll?cerca=' + daCercare);
  }

}
