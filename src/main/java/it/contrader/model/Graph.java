package it.contrader.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Graph {
	
	public enum TipoGrafico{
		doughnut,column,line,pie,bar,rangeColumn,rangeBar,pyramid,bubble,spline
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
	
	@OneToMany(mappedBy = "graph", cascade = CascadeType.ALL)
	private List<DataGraph> graphs;
	
	
}
