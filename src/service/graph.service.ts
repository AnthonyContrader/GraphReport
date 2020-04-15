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
        this.type = '/graph/api';
      }

      public getUtenti() : Observable<UserDTO[]>{
        return this.http.get<any>("http://localhost:8080/user/getall");
      }

      getGraphListByUser(id: number, page: number, size: number, sort: string[]) : Observable<any>{
        return this.http.get<any>(SERVER_API_URL+this.type+"/getByUt?id="+id+"&page="+page+"&size="+size+"&sort="+sort);
      }

//      getDsList(id : number) : Observable<DataSetDTO[]>{
//        return this.http.get<any>("http://localhost:8080/dataset/countDS?id="+id);
//      }

//      getAssiList(id : number,cat:number) : Observable<DataSetDTO[]>{
//        return this.http.get<any>("http://localhost:8080/dataset/getUMList?id="+id+"&cat="+cat);
//      }

      insertMtM(dto : mtmDTO[]): Observable<boolean>{
        return this.http.post<any>("http://localhost:8080/"+this.type+"/addset",dto);
      }

      delete(id:number): Observable<boolean>{
        return this.http.get<any>("http://localhost:8080/"+this.type+"/delete?id="+id);
      }

      getDSByGraph(id : number): Observable<mtmDTO[]>{
        return this.http.get<any>("http://localhost:8080/"+this.type+"/getAllByGraph?id="+id);
      }

      findAll(daCercare: string): Observable<GraphDTO[]> {
        return this.http.get<any>('http://localhost:8080/graph/findAll?cerca=' + daCercare);
      }

  }
