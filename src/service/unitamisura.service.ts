import { Injectable } from '@angular/core';
import { AbstractService} from './abstractservice';
import { HttpClient } from '@angular/common/http';
import { UnitaMisuraDTO } from 'src/dto/unitamisuradto';

@Injectable({
  providedIn: 'root'
})
export class UnitamisuraService extends AbstractService<UnitaMisuraDTO> {

  constructor(http: HttpClient) {

    super(http);
    this.type = 'unitamisura';
   }
}
