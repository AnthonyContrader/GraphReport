package it.contrader.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.contrader.converter.DataGraphConverter;
import it.contrader.converter.GraphConverter;
import it.contrader.dao.DataGraphRepository;
import it.contrader.dto.DataGraphDTO;
import it.contrader.dto.GraphDTO;
import it.contrader.model.DataGraph;
import it.contrader.model.DataSet;


@Service
public class DataGraphService extends AbstractService<DataGraph,DataGraphDTO>{
	
	@Autowired
	private DataGraphRepository repository;
	
	@Autowired
	private DataGraphConverter converter;
	
	public void insertNew(Long ds, Long graph, char asse) {
		DataGraphDTO dto = new DataGraphDTO();
		dto.setDataSetId(ds);
		dto.setGraphId(graph);
		dto.setAsse(asse);
		repository.save(converter.toEntity(dto));
	}
	
	public void deleteByGraph(Long id) {
		repository.deleteByGraphId(id);
	}
	
	public List<DataGraphDTO> getListValue(Long id){
		return converter.toDTOList(repository.findAllByGraph_Id(id));
	}
}
