package it.contrader.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.contrader.converter.GraphConverter;
import it.contrader.dao.GraphRepository;
import it.contrader.dto.GraphDTO;
import it.contrader.model.Graph;


@Service
public class GraphService extends AbstractService<Graph,GraphDTO>{
	
	@Autowired
	private GraphRepository repository;
	
	@Autowired
	private GraphConverter converter;
	
	public List<GraphDTO> getAllByUser(){
		return converter.toDTOList(repository.getAllByUser());
	} 

}
