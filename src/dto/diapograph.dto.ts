

export class  DiapoGraphDTO{
  id: number;
  dimensione: string;
  posizione: string;
  idDiapositiva: number;
  idGraph: number;

  constructor(id: number, dimensione: string, posizione: string, idDiapositiva: number, idGraph: number){
    this.id = id;
    this.dimensione = dimensione;
    this.posizione = posizione;
    this.idDiapositiva = idDiapositiva;
    this.idGraph = idGraph;
  }
}
