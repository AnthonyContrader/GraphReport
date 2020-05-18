import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
//import { DataSetDTO } from 'src/dto/DataSetDTO';
import { GraphDTO } from 'src/dto/graph.dto';
import { UserDTO } from 'src/dto/user.dto';
import { mtmDTO } from 'src/dto/mtm.dto';

import { SERVER_API_URL } from '../authJWT/app.constants';

@Injectable({
    providedIn: 'root'
  })
  export class GraphService extends AbstractService<GraphDTO>{

    constructor(http: HttpClient) {
        super(http);
        this.microservicesPath = '/graph/api';
        this.generic="/graphs";
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

    getIdAssi(id : number): Observable<mtmDTO[]>{
      return this.http.get<any>(SERVER_API_URL+this.microservicesPath+"/getMtMByGraph/"+id);
    }

    addSet(dto: mtmDTO): Observable<any>{
      return this.http.post<any>(SERVER_API_URL+this.microservicesPath+"/mt-ms",dto);
    }

    mtmUpdate(dto: mtmDTO): Observable<any>{
      return this.http.put<any>(SERVER_API_URL + this.microservicesPath + "/mt-ms", dto);

    }

    deleteGraphByUser(ut: number): Observable<boolean>{
      return this.http.delete<any>(SERVER_API_URL + this.microservicesPath + this.generic + '/user/' +ut);
    }

  }
