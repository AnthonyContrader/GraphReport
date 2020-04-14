export class mtmDTO{

    utente : Number;

    graphId : number;

    asse : string;

    tipoSet : string;

    colore : string;

    constructor(utente: number, graph: number, asse: string, tipoSet: string, colore: string){
        this.utente=utente;
        this.asse=asse;
        this.colore=colore;
        this.graphId=graph;
        this.tipoSet=tipoSet;
    }

}