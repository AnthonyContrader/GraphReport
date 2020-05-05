import { fontStyleText } from './fontStyleText.enum';


export class TestoDTO{
  id: number;
  text: string;
  dimensione: string;
  posizione: string;
  fontSize: number;
  colore: string;
  fontStyle: fontStyleText;
  idDiapositiva: number;

  constructo(id: number, text: string, dimensione: string, posizione: string,
             fontSize: number, colore: string, fontStyle: fontStyleText, idDiapositiva: number){
        this.id = id;
        this.text = text;
        this.dimensione = dimensione;
        this.posizione = posizione;
        this.fontSize = fontSize;
        this.colore = colore;
        this.fontStyle = fontStyle;
        this.idDiapositiva = idDiapositiva;

  }
}
