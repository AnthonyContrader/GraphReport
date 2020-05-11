import { Colore } from './colore.obj';
import { PresentazioneDTO } from './presentazione.dto';

export class DiapositivaDTO{
  id: number;
  sfondo: Colore;
  ordine: number;
  preset: boolean;
  ratio: string;
  isTitolo: boolean;
  titolo: string;
  posizioneT: string;
  dimensioneT: number;
  coloreT: Colore;
  presentazione: PresentazioneDTO;
  fontFamily: number;
  fontStyle: string;
  fontRotation: number;
  borderSize: number;
  borderColor: Colore;

  constructor(id: number, sfondo: Colore, ordine: number, preset: boolean, ratio: string,
              isTitolo: boolean, titolo: string, posizioneT: string,
              dimensioneT: number, coloreT: Colore, presentazione: PresentazioneDTO,
              fontFamily?: number, fontStyle?: string, fontRotation?: number, borderSize?: number, borderColor?: Colore ){
    this.id = id;
    this.sfondo = sfondo;
    this.ordine = ordine;
    this.preset = preset;
    this.ratio = ratio;
    this.isTitolo = isTitolo;
    this.titolo = titolo;
    this.posizioneT = posizioneT;
    this.dimensioneT = dimensioneT;
    this.coloreT = coloreT;
    this.presentazione = presentazione;
    this.fontFamily = fontFamily ? fontFamily : 0;
    this.fontStyle = fontStyle ? fontStyle : 'normal';
    this.fontRotation = fontRotation ? fontRotation : 0;
    this.borderSize = borderSize ? borderSize : 0;
    this.borderColor = borderColor ? borderColor : new Colore(255,255,255,100);

  }
}
