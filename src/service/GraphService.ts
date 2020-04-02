import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { HttpClient } from '@angular/common/http';
import { GraphDTO } from 'src/dto/GraphDTO';
import { Observable } from 'rxjs';
import { UserDTO } from 'src/dto/userdto';
import { DataSetDTO } from 'src/dto/DataSetDTO';
import { mtmDTO } from 'src/dto/mtmDTO';

@Injectable({
    providedIn: 'root'
  })
  export class GraphService extends AbstractService<GraphDTO>{

    constructor(http: HttpClient) {
        super(http);
        this.type = 'graph';
      }

      public getUtenti() : Observable<UserDTO[]>{
        return this.http.get<any>("http://localhost:8080/user/getall");
      }

      getGraphListByUser(id : number) : Observable<GraphDTO[]>{
        return this.http.get<any>("http://localhost:8080/"+this.type+"/getAllByUser?id="+id);
      }

      getDsList(id : number) : Observable<DataSetDTO[]>{
        return this.http.get<any>("http://localhost:8080/dataset/countDS?id="+id);
      }

      getAssiList(id : number,cat:number) : Observable<DataSetDTO[]>{
        return this.http.get<any>("http://localhost:8080/dataset/getUMList?id="+id+"&cat="+cat);
      }

      insertMtM(dto : mtmDTO[]): Observable<boolean>{
        return this.http.post<any>("http://localhost:8080/"+this.type+"/addset",dto);
      }

      delete(id:number): Observable<boolean>{
        return this.http.get<any>("http://localhost:8080/"+this.type+"/delete?id="+id);
      }

      findAll(daCercare: string): Observable<GraphDTO[]> {
        return this.http.get<any>('http://localhost:8080/graph/findAll?cerca=' + daCercare);
      }

<<<<<<< HEAD
      
  }
=======
  }
>>>>>>> b1572589ca8d5713d1848b49dcfa96a2ba281689
