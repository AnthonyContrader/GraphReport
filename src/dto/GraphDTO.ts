export class GraphDTO{

	id : number;
	
	titoloBool : boolean;
	
    titolo : string;
    
	fontStyle : string;
	
	fontSize : number;
	
	tipografico : string;
	
	posTitolo : string;
	
	legenda : boolean;

	posLegenda : string;
	
	pareto : boolean;

	constructor(id : number,titolobool : boolean, titolo : string,font : string, size: number, tipografico : string,posTitolo : string,legenda : boolean,posLegenda : string,pareto : boolean){
		this.id=id;
		this.titoloBool=titolobool;
		this.titolo=titolo;
		this.fontStyle=font;
		this.fontSize=size;
		this.tipografico=tipografico;
		this.posTitolo=posTitolo;
		this.legenda=legenda;
		this.posLegenda=posLegenda;
		this.pareto=pareto;
	}
}