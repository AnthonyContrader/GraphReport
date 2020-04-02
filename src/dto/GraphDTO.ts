import { TipoGrafico } from './TipoGrafico';
import { FontStyle } from './FontStyle';

export class GraphDTO{

    id : number;
	
    titolo : string;
    
    fontStyle : FontStyle;
	
	tipografico : TipoGrafico;
	
	posTitolo : string;
	
	legenda : boolean;
	
	pareto : boolean;

	constructor(id : number,titolo : string,font : FontStyle,tipografico : TipoGrafico,posTitolo : string,legenda : boolean,pareto : boolean){
		this.id=id;
		this.titolo=titolo;
		this.fontStyle=font;
		this.tipografico=tipografico;
		this.posTitolo=posTitolo;
		this.legenda=legenda;
		this.pareto=pareto;
	}
}