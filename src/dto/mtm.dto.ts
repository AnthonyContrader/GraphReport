import { DataSetDTO } from './dataSet.dto';
import { TipoGrafico } from './tipoGrafico.enum';

export class mtmDTO{

    id: number;

    dataSet : DataSetDTO;

    asse : string;
    
    tipoSet : TipoGrafico;

    colore : string;

    graphId : number;

    constructor(id:number,dataset: DataSetDTO, graph: number, asse: string, tipoSet: TipoGrafico, colore: string){
        this.id=id;
        this.dataSet=dataset;
        this.asse=asse;
        this.colore=colore;
        this.graphId=graph;
        this.tipoSet=tipoSet;
    }

}