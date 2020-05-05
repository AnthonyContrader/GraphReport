export class DiapositivaDTO{
  id: number;
  sfondo: string;
  ordine: number;
  preset: boolean;
  ratio: string;
  isTitolo: boolean;
  titolo: string;
  posizioneT: string;
  dimensioneT: string;
  coloreT: string;
  idPresentazione: number;

  constructor(id: number, sfondo: string, preset: boolean, ratio: string,
              isTitolo: boolean, titolo: string, posizioneT: string,
              dimensioneT: string, coloreT: string, idPresentazione: number){
    this.id = id;
    this.sfondo = sfondo;
    this.preset = preset;
    this.ratio = ratio;
    this.isTitolo = isTitolo;
    this.titolo = titolo;
    this.posizioneT = posizioneT;
    this.dimensioneT = dimensioneT;
    this.coloreT = coloreT;
    this.idPresentazione = idPresentazione;

  }
}
