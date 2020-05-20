import { DiapositivaDTO } from './diapositiva.dto';
import { GraphDTO } from './graph.dto';


export class  DiapoGraphDTO{
  id: number;
  dimensione: string;
  posizione: string;
  diapositiva: DiapositivaDTO;
  graph: GraphDTO;

  constructor(diapositiva: DiapositivaDTO,id?: number, dimensione?: string, posizione?: string,  graph?: GraphDTO){
    this.id = id ? id:0;
    this.dimensione = dimensione ? dimensione : "25_25";
    this.posizione = posizione ? posizione : "1_1";
    this.diapositiva = diapositiva;
    this.graph = graph ? graph : new GraphDTO(null);
  }
}
