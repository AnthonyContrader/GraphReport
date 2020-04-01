package it.contrader.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.contrader.dao.GraphRepository;
import it.contrader.dto.DataGraphDTO;
import it.contrader.dto.GraphDTO;
import it.contrader.model.Graph;


@Service
public class GraphService extends AbstractService<Graph,GraphDTO>{
		
	@Autowired
	private DataGraphService serviceDG;
	
	public void insertMtM(DataGraphDTO dto) {
		serviceDG.insertNew(dto);
	}
	
	public List<GraphDTO> getAllByUser(Long id){
		return converter.toDTOList(((GraphRepository)repository).findGraphByUser(id));
	}

	public List<GraphDTO> findAll(String daCercare){
		return converter.toDTOList(((GraphRepository)repository).findAll(daCercare));
	}
	
}
