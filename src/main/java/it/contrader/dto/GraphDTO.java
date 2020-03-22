package it.contrader.dto;

import it.contrader.model.Graph.Tema;
import it.contrader.model.Graph.TipoGrafico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GraphDTO {
	
private Long id;
	
	private String titolo;
	
	private TipoGrafico tipografico;
	
	private Tema tema;
	
	private String posTitolo;
	
	private Boolean legenda;
	
	private Boolean zoom;
	
	private Boolean pareto;
	
}
