import { TipoGrafico } from './TipoGrafico';
import { FontStyle } from './FontStyle';

export class GraphDTO{
    id : number;
	
    titolo : string;
    
    font : FontStyle;
	
	tipografico : TipoGrafico;
	
	posTitolo : string;
	
	legenda : boolean;
	
	pareto : boolean;
}