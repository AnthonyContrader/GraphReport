import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { DataSetDTO } from 'src/dto/DataSetDTO';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CategoriaDTO } from 'src/dto/categoriadto';
import { UnitaMisuraDTO } from 'src/dto/unitamisuradto';
import { UserDTO } from 'src/dto/userdto';

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
    this.type = 'dataset';
  }

  getAllByUser(): Observable<DataSetDTO[]> {
    return this.http.get<any>('http://localhost:8080/' + this.type + '/getAllByUser?id=' + localStorage.getItem('idUser').toString());
  }

  countDS(): Observable<DataSetDTO[]> {
    return this.http.get<any>('http://localhost:8080/' + this.type + '/countDS?id=' + localStorage.getItem('idUser').toString());
  }

  getListCat(): Observable<CategoriaDTO[]> {
        return this.http.get<any>('http://localhost:8080/categoria/getall');
  }

  getListUnit(): Observable<UnitaMisuraDTO[]> {
    return this.http.get<any>('http://localhost:8080/unitamisura/getall');
  }

  getListUser(): Observable<UserDTO[]> {
    return this.http.get<any>('http://localhost:8080/user/getall');
  }

}