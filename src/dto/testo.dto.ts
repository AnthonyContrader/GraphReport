import { fontStyleText } from './fontStyleText.enum';
import { Colore } from './colore.obj';
import { DiapositivaDTO } from './diapositiva.dto';


export class TestoDTO{
  id: number;
  text: string;
  dimensione: string;
  posizione: string;
  fontSize: number;
  colore: Colore;
  fontStyle: number;
  diapositiva: DiapositivaDTO;

  constructor(diapositiva: DiapositivaDTO,id?: number, text?: string, dimensione?: string, posizione?: string,
             fontSize?: number, colore?: Colore, fontStyle?: fontStyleText){
        this.id = id ? id : 0;
        this.text = text ? text : "Nuovo Testo";
        this.dimensione = dimensione ? dimensione: "50";
        this.posizione = posizione? posizione : "1_1";
        this.fontSize = fontSize ? fontSize: 20;
        this.colore = colore ? colore: new Colore(255,255,255,100);
        this.fontStyle = fontStyle ? fontStyle:0;
        this.diapositiva = diapositiva;

  }
}
