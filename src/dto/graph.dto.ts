import { FontStyle } from './fontStyle.enum';
import { TipoGrafico } from './tipoGrafico.enum';

export class GraphDTO{

	id : number;

	utente : number;

	tipoGrafico : TipoGrafico;

	titoloBool : boolean;
	
    titolo : string;
    
	fontStyle : FontStyle;
	
	fontSize : number;
	
	posTitolo : string;
	
	legenda : boolean;

	posLegenda : string;
	
	pareto : boolean;

	created : Date;

	modify : Date;

	constructor(id : number, utente : number, titolobool : boolean, titolo : string,font : FontStyle, size: number, tipografico : TipoGrafico,posTitolo : string,
													legenda : boolean,posLegenda : string,pareto : boolean, created : Date, modify : Date){

		this.id=id;
		this.utente=utente;
		this.titoloBool=titolobool;
		this.titolo=titolo;
		this.fontStyle=font;
		this.fontSize=size;
		this.tipoGrafico=tipografico;
		this.posTitolo=posTitolo;
		this.legenda=legenda;
		this.posLegenda=posLegenda;
		this.pareto=pareto;
		this.created = created;
		this.modify = modify;
	}
}