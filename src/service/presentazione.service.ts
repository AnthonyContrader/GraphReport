import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


import { SERVER_API_URL } from '../authJWT/app.constants';
import { PresentazioneDTO } from 'src/dto/presentazione.dto';

@Injectable({
    providedIn: 'root'
  })
  export class PresentazioneService extends AbstractService<PresentazioneDTO>{

    constructor(http: HttpClient) {
      super(http);
      this.microservicesPath = '/presentazione/api';
      this.generic = '/Presentazione';
    }

    getAllByUser(id: number) : Observable<PresentazioneDTO[]> {
      return this.http.get<any>(SERVER_API_URL + this.microservicesPath + this.generic + '/byUser/' + id);
    }

  }
