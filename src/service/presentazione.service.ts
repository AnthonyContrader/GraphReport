import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


import { SERVER_API_URL } from '../authJWT/app.constants';
import { GraphDTO } from 'src/dto/graph.dto';

@Injectable({
    providedIn: 'root'
  })
  export class PresentazioneService extends AbstractService<GraphDTO>{

    constructor(http: HttpClient) {
        super(http);
        this.microservicesPath = '/diapositiva/api';
        this.generic="/Diapositiva";
      }

    getGraphListByUser(id: number, page: number, size: number, sort: string[]) : Observable<any>{
      return this.http.get<any>(SERVER_API_URL+this.microservicesPath+"/getByUt?id="+id+"&page="+page+"&size="+size+"&sort="+sort);
    }

    getLastModifyByUser(user:number) : Observable<GraphDTO>{
      return this.http.get<any>(SERVER_API_URL+this.microservicesPath+"/lastModify/"+user);
    }

    mtmDelete(id: number): Observable<any> {
        return this.http.delete(SERVER_API_URL + this.microservicesPath + "/mtsDelete/" + id);
    }

  }
