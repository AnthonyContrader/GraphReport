import { DataSetDTO } from './dataSet.dto';
import { TipoGrafico } from './tipoGrafico.enum';
import { NgIf } from '@angular/common';

export class Colore{

    red: number;

    green : number;

    blue : number;

    alpha : number;

    constructor(red: number, green : number, blue : number, alpha : number){
        this.red=red;
        this.green=green;
        this.blue=blue;
        this.alpha=alpha;
    }

}