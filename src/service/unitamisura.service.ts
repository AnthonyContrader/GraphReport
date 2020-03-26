import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { UnitaMisuraDTO } from 'src/dto/unitamisuradto';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class UnitaMisuraService extends AbstractService<UnitaMisuraDTO> {

  constructor(http: HttpClient) {
    super(http);
    this.type = 'unitamisura';
  }

}
