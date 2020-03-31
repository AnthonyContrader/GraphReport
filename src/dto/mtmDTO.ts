export class mtmDTO{
    public id : number;

    public dataSetId : number;

    public graphId : number;

    public asse : string;

    constructor(i : number,d : number,g : number ,a : string){
        this.id=i;
        this.dataSetId=d;
        this.graphId=g;
        this.asse=a;
    }
}