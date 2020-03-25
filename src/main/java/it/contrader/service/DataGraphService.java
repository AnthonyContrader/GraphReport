package it.contrader.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.contrader.converter.DataGraphConverter;
import it.contrader.converter.GraphConverter;
import it.contrader.dao.DataGraphRepository;
import it.contrader.dao.UserRepository;
import it.contrader.dto.DataGraphDTO;
import it.contrader.dto.GraphDTO;
import it.contrader.model.DataGraph;
import it.contrader.model.DataSet;


@Service
public class DataGraphService extends AbstractService<DataGraph,DataGraphDTO>{
	
	public void insertNew(DataGraphDTO dto) {
		repository.save(converter.toEntity(dto));
	}
	
	public void deleteByGraph(Long id) {
		((DataGraphRepository)repository).deleteByGraphId(id);
	}
	
	public List<DataGraphDTO> getListValue(Long id){
		return converter.toDTOList(((DataGraphRepository)repository).findAllByGraph_Id(id));
	}
}
