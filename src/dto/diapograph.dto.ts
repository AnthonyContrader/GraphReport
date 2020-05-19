import { DiapositivaDTO } from './diapositiva.dto';


export class  DiapoGraphDTO{
  id: number;
  dimensione: string;
  posizione: string;
  diapositiva: DiapositivaDTO;
  idGraph: number;

  constructor(diapositiva: DiapositivaDTO,id?: number, dimensione?: string, posizione?: string,  idGraph?: number){
    this.id = id ? id:0;
    this.dimensione = dimensione ? dimensione : "25_25";
    this.posizione = posizione ? posizione : "1_1";
    this.diapositiva = diapositiva;
    this.idGraph = idGraph ? idGraph : 1;
  }
}
