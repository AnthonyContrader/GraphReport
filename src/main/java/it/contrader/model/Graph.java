package it.contrader.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Graph {
	
	public enum TipoGrafico{
		daughnut,column,line,pie,bar,rangeColumn,rangeBar,pyramid,buble,spline
	}
	
	public enum Tema{
		ligth1,ligth2,dark1,dark2
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String titolo;
	
	private TipoGrafico tipografico;
	
	private Tema tema;
	
	private String posTitolo;
	
	private Boolean legenda;
	
	private Boolean zoom;
	
	private Boolean pareto;
	
	
}
