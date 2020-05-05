import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { DiapositivaDTO } from 'src/dto/diapositiva.dto';
import { HttpClient } from '@angular/common/http';

import { SERVER_API_URL } from '../authJWT/app.constants';

@Injectable({
  providedIn: 'root'
})

export class DiapositivaService extends AbstractService<DiapositivaDTO>{

  constructor(http: HttpClient) {
    super(http);
    this.microservicesPath = '/diapositiva/api';
    this.generic = '/Diapositiva';
  }
}
