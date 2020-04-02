import { Injectable } from '@angular/core';
import { AbstractService} from './abstractservice';
import { HttpClient } from '@angular/common/http';
import { UnitaMisuraDTO } from 'src/dto/unitamisuradto';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UnitamisuraService extends AbstractService<UnitaMisuraDTO> {

  constructor(http: HttpClient) {

    super(http);
    this.type = 'unitamisura';
  }

  findAll(daCercare: string): Observable<UnitaMisuraDTO[]> {
    return this.http.get<any>('http://localhost:8080/unitamisura/findAll?cerca=' + daCercare);
  }

}
