package it.contrader.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

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
	
	
	@ManyToMany
	@JoinTable(name = "datagraph",
			joinColumns = @JoinColumn(name = "grap_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "dataset_id", referencedColumnName = "id"))
	private Set<DataSet> dataset; 
	
}
