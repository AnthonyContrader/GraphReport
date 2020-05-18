import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL } from 'src/authJWT/app.constants';
import { DataSetDTO } from 'src/dto/dataSet.dto';
import { UserDTO } from 'src/dto/user.dto';
import { CategoriaDTO } from 'src/dto/categoria.dto';
import { UnitaMisuraDTO } from 'src/dto/unitamisura.dto';

/**
 * I service sono decorati da @Injectable.
 * Qui trovate, oltre ai metodi ereditati dall'Abstract,
 *  il metodo per il login (in mirror con il backend).
 *
 * @author Vittorio Valent
 *
 * @see AbstractService
 */
@Injectable({
  providedIn: 'root'
})
export class DataSetService extends AbstractService<DataSetDTO>{
  

  constructor(http: HttpClient) {
    super(http);
    this.microservicesPath = '/dataset/api';
    this.generic="/datasets";
  }

  getDatasetByUser(id :number): Observable<DataSetDTO[]>{
    return this.http.get<any>(SERVER_API_URL + this.microservicesPath + this.generic + '/user/' + id)
  }

  insertList(list: DataSetDTO[]) : Observable<any>{
    return this.http.post<any>(SERVER_API_URL + this.microservicesPath + "/insertList", list);
  }
  
  getDatasetByUserTitolo(ut: number,tit: string){
    return this.http.get<any>(SERVER_API_URL + this.microservicesPath + this.generic + '/getDatasetByUserTitolo/'+ut+'/'+tit);
  }

  updateDS(dtoList: DataSetDTO[]) : Observable<boolean>{
    return this.http.post<any>(SERVER_API_URL + this.microservicesPath + '/updateDS',dtoList);
  }

  deleteDS(ut: number,tit:string): Observable<boolean>{
    return this.http.get<any>(SERVER_API_URL + this.microservicesPath +  '/deleteDS/' +ut+'/'+tit);
  }

  deleteDsByUser(ut: number): Observable<boolean>{
    return this.http.delete<any>(SERVER_API_URL + this.microservicesPath + this.generic + '/user/' +ut);
  }


  // getAllByUser(id: number): Observable<DataSetDTO[]> {
  //   return this.http.get<any>(SERVER_API_URL + this.microservicesPath + this.generic + '/getAllByUser?id=' + id);
  // }

  // countDS(id: number): Observable<DataSetDTO[]> {
  //   return this.http.get<any>(SERVER_API_URL + this.microservicesPath + this.generic + '/countDS?id=' + id);
  // }

  // getListCat(): Observable<CategoriaDTO[]> {
  //   return this.http.get<any>(SERVER_API_URL +'/unita/api/categorias');
  // }

  // getListUnit(): Observable<UnitaMisuraDTO[]> {
  //   return this.http.get<any>(SERVER_API_URL +'/unita/api/unitamisuras');
  // }

  // getListUser(): Observable<UserDTO[]> {
  //   return this.http.get<any>(SERVER_API_URL +'gateway/api/users');
  // }

  // deleteDS(ut: number,cat:number): Observable<boolean>{
  //   return this.http.get<any>(SERVER_API_URL + this.microservicesPath + this.generic + '/deletedataset?ut='+ut+'&cat='+cat);
  // }

  // getDataSet(ut: number,cat:number){
  //   return this.http.get<any>(SERVER_API_URL + this.microservicesPath + this.generic + '/getDataSet?id='+ut+'&cat='+cat);
  // }

  // updateDS(dtoList: DataSetDTO[]) : Observable<boolean>{
  //   return this.http.post<any>(SERVER_API_URL + this.microservicesPath + this.generic + '/updateDS',dtoList);
  // }

  // findAll(daCercare: string): Observable<DataSetDTO[]> {
  //   return this.http.get<any>(SERVER_API_URL + this.microservicesPath + this.generic + '/findAll?cerca=' + daCercare);
  // }

}
