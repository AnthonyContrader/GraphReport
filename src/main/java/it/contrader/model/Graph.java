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
		line,bar,radar,pie,doughnut,polarArea,bubble,scatter
	}
	
	public enum FontStyle{
		courier, helveticaneue, helvetica, arial,
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	@OneToMany(mappedBy = "graph", cascade = CascadeType.ALL)
	private List<DataGraph> graphs;
	
	
}
