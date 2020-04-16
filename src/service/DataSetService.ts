import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
//import { CategoriaDTO } from 'src/dto/categoriadto';
//import { UnitaMisuraDTO } from 'src/dto/unitamisuradto';
//import { UserDTO } from 'src/dto/userdto';
import { SERVER_API_URL } from 'src/authJWT/app.constants';
import { DataSetDTO } from 'src/dto/dataSet.dto';

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

  getAllByUser(id: number): Observable<DataSetDTO[]> {
    return this.http.get<any>(SERVER_API_URL + this.microservicesPath + '/getAllByUser?id=' + id);
  }

//  countDS(id: number): Observable<DataSetDTO[]> {
//    return this.http.get<any>('http://localhost:8080/' + this.type + '/countDS?id=' + id);
//  }

//  getListCat(): Observable<CategoriaDTO[]> {
//        return this.http.get<any>('http://localhost:8080/categoria/getall');
//  }

//  getListUnit(): Observable<UnitaMisuraDTO[]> {
//    return this.http.get<any>('http://localhost:8080/unitamisura/getall');
//  }

//  getListUser(): Observable<UserDTO[]> {
//    return this.http.get<any>('http://localhost:8080/user/getall');
//  }

//  createDS(dto: DataSetDTO): Observable<number>{
//    return this.http.post<any>('http://localhost:8080/' + this.type + '/createdataset', dto);
//  }

//  deleteDS(ut: number,cat:number): Observable<boolean>{
//    return this.http.get<any>('http://localhost:8080/' + this.type + '/deletedataset?ut='+ut+'&cat='+cat);
//  }

//  getDataSet(ut: number,cat:number){
//    return this.http.get<any>('http://localhost:8080/' + this.type + '/getDataSet?id='+ut+'&cat='+cat);
//  }

//  updateDS(dtoList: DataSetDTO[]) : Observable<boolean>{
//    return this.http.post<any>('http://localhost:8080/' + this.type + '/updateDS',dtoList);
//  }

  findAll(daCercare: string): Observable<DataSetDTO[]> {
    return this.http.get<any>('http://localhost:8080/dataset/findAll?cerca=' + daCercare);
  }

}
