import { DiapositivaDTO } from './diapositiva.dto';
import { TestoDTO } from './testo.dto';
import { DiapoGraphDTO } from './diapograph.dto';

export class DiapoFullDTO{

    diapositiva : DiapositivaDTO;

    testi : TestoDTO[];

    grafici : DiapoGraphDTO[];

    constructor(diapositiva : DiapositivaDTO, testi? : TestoDTO[], grafici? : DiapoGraphDTO[]){
        this.diapositiva = diapositiva;
        this.testi = testi ? testi : [];
        this.grafici = grafici ? grafici : [];
    }

}