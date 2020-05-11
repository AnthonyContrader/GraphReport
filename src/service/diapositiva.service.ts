import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { DiapositivaDTO } from 'src/dto/diapositiva.dto';
import { HttpClient } from '@angular/common/http';

import { SERVER_API_URL } from '../authJWT/app.constants';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class DiapositivaService extends AbstractService<DiapositivaDTO>{

  constructor(http: HttpClient) {
    super(http);
    this.microservicesPath = '/presentazione/api';
    this.generic = '/diapositiva';
  }

  getAllByPresentazione(id: number) : Observable<DiapositivaDTO[]> {
    return this.http.get<any>(SERVER_API_URL + this.microservicesPath + this.generic + '/byPresentazione/' + id);
  }
}
