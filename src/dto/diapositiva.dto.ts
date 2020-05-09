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
  dimensioneT: string;
  coloreT: Colore;
  presentazione: PresentazioneDTO;

  constructor(id: number, sfondo: Colore, ordine: number, preset: boolean, ratio: string,
              isTitolo: boolean, titolo: string, posizioneT: string,
              dimensioneT: string, coloreT: Colore, presentazione: PresentazioneDTO ){
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

  }
}
