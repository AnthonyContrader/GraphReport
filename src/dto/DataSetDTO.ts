export class DataSetDTO{

    private id : number;

	private utente : number;
	
	private categoria : number;
	private categoriaN : string;
	
	private unitaMisura : number;
	private unitaMisuraN : string;
	
	private valore: string;
	
    private commento : string;
    
    constructor(id : number, utente : number, categoria : number, categoriaN : string, unitaMisura : number, unitaMisuraN : string, valore: string, commento : string){
        this.id = id;
        this.utente = utente;
        this.categoria = categoria;
        this.categoriaN = categoriaN;
        this.unitaMisura = unitaMisura;
        this.unitaMisuraN = unitaMisuraN;
        this.valore = valore;
        this.commento = commento;
    }
}