

export class PresentazioneDTO{
  id: number;
  nome: string;
  dataCreazione: Date;
  ultimaModifica: Date;

  constructor(id: number, nome: string, dataCreazione: Date, ultimaModifica: Date){
    this.id = id;
    this.nome = nome;
    this.dataCreazione = dataCreazione;
    this.ultimaModifica = ultimaModifica;
  }
}
