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

	created : Date;

	modify : Date;

	constructor(id : number, utente? : number, titolobool? : boolean, titolo? : string,font? : FontStyle, fontColor? : string, size? : number, tipografico? : TipoGrafico,posTitolo? : string,
													legenda? : boolean,posLegenda? : string,pareto? : boolean, created? : Date, modify? : Date){

		this.id= id;
		this.utente=utente ? utente : null;
		this.titoloBool=titolobool ? titolobool : null;
		this.titolo=titolo ? titolo : null;
		this.fontStyle=font ? font : null;
		this.fontColor=fontColor ? fontColor : null;
		this.fontSize=size ? size : null;
		this.tipoGrafico=tipografico ? tipografico : null;
		this.posTitolo=posTitolo ? posTitolo : null;
		this.legenda=legenda ? legenda : null;
		this.posLegenda=posLegenda ? posLegenda : null;
		this.pareto=pareto ? pareto : null;
		this.created = created ? created : null;
		this.modify = modify ? modify : null;
	}
}