import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { CategoriaDTO } from 'src/dto/categoriadto';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Usertype } from 'src/dto/usertype';


@Injectable({
  providedIn: 'root'
})
export class CategoriaService extends AbstractService<CategoriaDTO> {

  constructor(http: HttpClient) {
    super(http);
    this.type = 'categoria';
  }

}
