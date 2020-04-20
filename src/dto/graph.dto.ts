import { FontStyle } from './fontStyle.enum';
import { TipoGrafico } from './tipoGrafico.enum';

export class GraphDTO{

	id : number;

	utente : number;

	tipoGrafico : TipoGrafico;

	titoloBool : boolean;
	
    titolo : string;
    
	fontStyle : FontStyle;

	fontColor : string;
	
	fontSize : number;
	
	posTitolo : string;
	
	legenda : boolean;

	posLegenda : string;
	
	pareto : boolean;

	mixed : boolean;

	created : Date;

	modify : Date;

	constructor(id : number, utente? : number, titolobool? : boolean, titolo? : string,font? : number, fontColor? : string, size? : number, tipografico? : number,posTitolo? : string,
													legenda? : boolean,posLegenda? : string,pareto? : boolean, mixed? : boolean, created? : Date, modify? : Date){

		this.id= id;
		this.utente=utente ? utente : null;
		this.titoloBool=titolobool ? titolobool : false;
		this.titolo=titolo ? titolo : null;
		this.fontStyle=font ? font : 0;
		this.fontColor=fontColor ? fontColor : null;
		this.fontSize=size ? size : 0;
		this.tipoGrafico=tipografico ? tipografico : 0;
		this.posTitolo=posTitolo ? posTitolo : null;
		this.legenda=legenda ? legenda : false;
		this.posLegenda=posLegenda ? posLegenda : null;
		this.pareto=pareto ? pareto : false;
		this.mixed= mixed ? mixed : false;
		this.created = created ? created : new Date();
		this.modify = modify ? modify : new Date();
	}
}