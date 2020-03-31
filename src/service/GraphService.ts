import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { HttpClient } from '@angular/common/http';
import { GraphDTO } from 'src/dto/GraphDTO';

@Injectable({
    providedIn: 'root'
  })
  export class GraphService extends AbstractService<GraphDTO>{
    
    constructor(http: HttpClient) {
        super(http);
        this.type = 'graph';
      }

  }