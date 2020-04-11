import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from '../app.constants';
import { UserDTO } from 'src/dto/user.dto';

@Injectable({ providedIn: 'root' })
export class UserService {
    public resourceUrl = SERVER_API_URL + 'api/users';

    constructor(private http: HttpClient) {}

    create(user: UserDTO): Observable<HttpResponse<UserDTO>> {
        return this.http.post<UserDTO>(this.resourceUrl, user, { observe: 'response' });
    }

    update(user: UserDTO): Observable<HttpResponse<UserDTO>> {
        return this.http.put<UserDTO>(this.resourceUrl, user, { observe: 'response' });
    }

    find(login: string): Observable<HttpResponse<UserDTO>> {
        return this.http.get<UserDTO>(`${this.resourceUrl}/${login}`, { observe: 'response' });
    }

    delete(login: string): Observable<HttpResponse<any>> {
        return this.http.delete(`${this.resourceUrl}/${login}`, { observe: 'response' });
    }

    authorities(): Observable<string[]> {
        return this.http.get<string[]>(SERVER_API_URL + 'api/users/authorities');
    }
}
