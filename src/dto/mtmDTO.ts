import { DataSetDTO } from './DataSetDTO';

export class mtmDTO{
    public id : number;

    public dataSetId : number;

    public dataSet : DataSetDTO;

    public graphId : number;

    public asse : string;

    constructor(i : number,d : number,g : number ,a : string){
        this.id=i;
        this.dataSetId=d;
        this.dataSet = new DataSetDTO(null,null,null,null,null,null,null,null);
        this.graphId=g;
        this.asse=a;
    }
}