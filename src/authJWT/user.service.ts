import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from '../app.constants';
import { AbstractService } from './abstractservice';
import { UserDTO } from 'src/dto/userdto';

@Injectable({ providedIn: 'root' })
export class UserService extends AbstractService<UserDTO> {
    public resourceUrl = SERVER_API_URL + 'api/users';

    constructor(http: HttpClient) {
      super(http);
      this.type = '';
    }

    create(user: UserDTO): Observable<HttpResponse<UserDTO>> {
        return this.http.post<UserDTO>(this.resourceUrl, user, { observe: 'response' });
    }

    find(login: string): Observable<HttpResponse<UserDTO>> {
        return this.http.get<UserDTO>(`${this.resourceUrl}/${login}`, { observe: 'response' });
    }

    authorities(): Observable<string[]> {
        return this.http.get<string[]>(SERVER_API_URL + 'api/users/authorities');
    }

    findAll(daCercare: string): Observable<UserDTO[]> {
      return this.http.get<any>('http://localhost:8080/user/findAll?cerca=' + daCercare);
    }
}
