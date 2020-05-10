import { Service } from './service';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { SERVER_API_URL } from 'src/authJWT/app.constants';

/**
 * Service astratto, implementa tutti i metodi CRUD inviando request al server di SpringBoot.
 * @param port il port del backend
 * @param type la mappatura del controller di ciascuna entità.
 *
 * @see Service
 *
 * @author Vittorio Valent
 */
export abstract class AbstractService<DTO> implements Service<DTO> {

    microservicesPath: string;
    generic: string;

    constructor(protected http: HttpClient) {
    }

    getAll(): Observable<DTO[]> {
        return this.http.get<DTO[]>(SERVER_API_URL + this.microservicesPath + this.generic);
    }

    read(id: number): Observable<DTO> {
        return this.http.get<DTO>(SERVER_API_URL + this.microservicesPath + this.generic + "/" + id);
    }

    delete(id: number): Observable<any> {
        return this.http.delete(SERVER_API_URL + this.microservicesPath + this.generic + "/" + id);
    }

    insert(dto: DTO): Observable<any> {
        return this.http.post(SERVER_API_URL + this.microservicesPath + this.generic, dto);
    }

    update(dto: DTO): Observable<DTO> {
        console.log(dto);
        return this.http.put<DTO>(SERVER_API_URL + this.microservicesPath + this.generic, dto);

    }
}
