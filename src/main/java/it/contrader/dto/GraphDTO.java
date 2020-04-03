package it.contrader.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import it.contrader.model.Graph.FontStyle;
import it.contrader.model.Graph.TipoGrafico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class GraphDTO {
	
	private Long id;
	
	private Boolean titoloBool;
	
	private String titolo;
	
	private FontStyle fontStyle;
	
	private Integer fontSize;
	
	private TipoGrafico tipografico;
	
	private String posTitolo;
	
	private Boolean legenda;
	
	private String posLegenda;
	
	private Boolean pareto;
	
}
