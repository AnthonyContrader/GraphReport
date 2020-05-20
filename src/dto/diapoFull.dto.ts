import { DiapositivaDTO } from './diapositiva.dto';
import { TestoDTO } from './testo.dto';
import { DiapoGraphDTO } from './diapograph.dto';

export class DiapoFullDTO{

    diapositiva : DiapositivaDTO;

    testi : TestoDTO[];
    testiDel : number[];

    grafici : DiapoGraphDTO[];
    graficiDel : number[];

    constructor(diapositiva : DiapositivaDTO, testi? : TestoDTO[], grafici? : DiapoGraphDTO[]){
        this.diapositiva = diapositiva;
        this.testi = testi ? testi : [];
        this.grafici = grafici ? grafici : [];
        this.testiDel = [];
        this.graficiDel = [];

    }

}