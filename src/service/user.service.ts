import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from '../authJWT/app.constants';
import { AbstractService } from './abstractservice';
import { UserDTO } from 'src/dto/user.dto';

@Injectable({ providedIn: 'root' })
export class UserService extends AbstractService<UserDTO> {
    public resourceUrl = SERVER_API_URL + 'api/users';


    constructor(http: HttpClient) {
      super(http);
      this.microservicesPath = '/api';
      this.generic = '/users';
    }

    create(user: UserDTO): Observable<HttpResponse<UserDTO>> {
        return this.http.post<UserDTO>(this.resourceUrl, user, { observe: 'response' });
    }

    deleteByLogin(login: string): Observable<UserDTO> {
      return this.http.delete<any>(SERVER_API_URL + this.microservicesPath + '/users/' + login);
    }

    find(login: string): Observable<HttpResponse<UserDTO>> {
        return this.http.get<UserDTO>(SERVER_API_URL + this.microservicesPath + '/users/' + login, { observe: 'response' });
    }

    authorities(): Observable<string[]> {
        return this.http.get<string[]>(SERVER_API_URL + '/api/users/authorities');
    }

    registration(user: UserDTO): Observable<UserDTO[]>{
      return this.http.post<any>(SERVER_API_URL +  this.microservicesPath + '/register', user);
    }

    activation(key: string): Observable <UserDTO>{
      return this.http.get<any>(SERVER_API_URL + this.microservicesPath + '/activate?key=' + key);
    }

    authentication(user: UserDTO): Observable<UserDTO[]>{
      return this.http.post<any>(SERVER_API_URL + this.microservicesPath + '/authenticate', user);
    }

}
