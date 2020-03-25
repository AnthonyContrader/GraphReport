package it.contrader.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.contrader.dao.GraphRepository;
import it.contrader.dto.GraphDTO;
import it.contrader.model.Graph;


@Service
public class GraphService extends AbstractService<Graph,GraphDTO>{
		
	@Autowired
	private DataGraphService serviceDG;
	
	public void insertMtM(Long ds, Long graph, char asse) {
		serviceDG.insertNew(ds,graph,asse);
	}
	
	public List<GraphDTO> getAllByUser(Long id){
		return converter.toDTOList(((GraphRepository)repository).findGraphByUser(id));
	}
	
}
