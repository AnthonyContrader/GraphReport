import { DataSetDTO } from './dataSet.dto';

export class mtmDTO{

    id: number;

    dataSet : DataSetDTO;

    asse : string;
    
    tipoSet : string;

    colore : string;

    graphId : number;

    constructor(id:number,dataset: DataSetDTO, graph: number, asse: string, tipoSet: string, colore: string){
        this.id=id;
        this.dataSet=dataset;
        this.asse=asse;
        this.colore=colore;
        this.graphId=graph;
        this.tipoSet=tipoSet;
    }

}