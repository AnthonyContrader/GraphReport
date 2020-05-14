import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { DiapositivaDTO } from 'src/dto/diapositiva.dto';
import { HttpClient } from '@angular/common/http';

import { SERVER_API_URL } from '../authJWT/app.constants';
import { Observable } from 'rxjs';
import { TestoDTO } from 'src/dto/testo.dto';

@Injectable({
  providedIn: 'root'
})

export class TestoService extends AbstractService<TestoDTO>{

  constructor(http: HttpClient) {
    super(http);
    this.microservicesPath = '/presentazione/api';
    this.generic = '/testo';
  }

  getAllByDiapositiva(id: number) : Observable<TestoDTO[]> {
    return this.http.get<any>(SERVER_API_URL + this.microservicesPath + this.generic + '/byDiapositiva/' + id);
  }
  
}
