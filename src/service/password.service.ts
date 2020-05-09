import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AbstractService } from './abstractservice';
import { UserDTO } from 'src/dto/user.dto';
import { SERVER_API_URL } from 'src/authJWT/app.constants';

@Injectable({ providedIn: 'root' })
export class PasswordService extends AbstractService<UserDTO>{
    constructor(http: HttpClient) {
      super(http);
      this.microservicesPath = '/api';
    }

    save(newPassword: string, currentPassword: string): Observable<any> {
        return this.http.post(SERVER_API_URL + this.microservicesPath + '/account/change-password', { currentPassword, newPassword });
    }
}
