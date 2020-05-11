

export class PresentazioneDTO{
  id: number;
  nome: string;
  dataCreazione: Date;
  ultimaModifica: Date;
  utente: number;

  constructor(id: number, utente: number, nome?: string, dataCreazione?: Date, ultimaModifica?: Date){
    this.id = id ;
    this.utente = utente;
    this.nome = nome ? nome : "";
    this.dataCreazione = dataCreazione ? dataCreazione : new Date();
    this.ultimaModifica = ultimaModifica ? ultimaModifica : new Date();
  }
}
