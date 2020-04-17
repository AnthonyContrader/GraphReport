package it.contrader.graphreport.service.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ToDrawDTO implements Serializable{

	private GraphDTO graph;
	
	private List<MtMDTO> assi;
	
	public ToDrawDTO() {
		this.graph = new GraphDTO();
		this.assi = new ArrayList<MtMDTO>();
	}
	
	public ToDrawDTO(GraphDTO graph,List<MtMDTO> assi) {
		this.graph = graph;
		this.assi = assi;
	}
	
	public GraphDTO getGraph() {
		return this.graph;
	}
	
	public List<MtMDTO> getAssi(){
		return this.assi;
	}
}
