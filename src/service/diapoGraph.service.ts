import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { DiapositivaDTO } from 'src/dto/diapositiva.dto';
import { HttpClient } from '@angular/common/http';

import { SERVER_API_URL } from '../authJWT/app.constants';
import { Observable } from 'rxjs';
import { TestoDTO } from 'src/dto/testo.dto';
import { DiapoGraphDTO } from 'src/dto/diapograph.dto';

@Injectable({
  providedIn: 'root'
})

export class DiapoGraphService extends AbstractService<DiapoGraphDTO>{

  constructor(http: HttpClient) {
    super(http);
    this.microservicesPath = '/presentazione/api';
    this.generic = '/diapograph';
  }

  getAllByDiapositiva(id: number) : Observable<DiapoGraphDTO[]> {
    return this.http.get<any>(SERVER_API_URL + this.microservicesPath + this.generic + '/byDiapositiva/' + id);
  }
  
}
