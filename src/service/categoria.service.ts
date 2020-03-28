import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { HttpClient } from '@angular/common/http';
import { CategoriaDTO } from 'src/dto/categoriadto';

@Injectable({
  providedIn: 'root'
})
export class CategoriaService extends AbstractService<CategoriaDTO> {

  constructor(http: HttpClient) {

    super(http);
    this.type = 'categoria';
  }
}
