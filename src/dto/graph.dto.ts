export class GraphDTO{

	id : number;

	utente : number;
	
	titoloBool : boolean;
	
    titolo : string;
    
	fontStyle : string;
	
	fontSize : number;
	
	tipografico : string;
	
	posTitolo : string;
	
	legenda : boolean;

	posLegenda : string;
	
	pareto : boolean;

	created : string;

	modify : string;

	constructor(id : number, utente : number, titolobool : boolean, titolo : string,font : string, size: number, tipografico : string,posTitolo : string,legenda : boolean,posLegenda : string,pareto : boolean, created : string, modify : string){
		this.id=id;
		this.utente=utente;
		this.titoloBool=titolobool;
		this.titolo=titolo;
		this.fontStyle=font;
		this.fontSize=size;
		this.tipografico=tipografico;
		this.posTitolo=posTitolo;
		this.legenda=legenda;
		this.posLegenda=posLegenda;
		this.pareto=pareto;
		this.created = created;
		this.modify = modify;
	}
}