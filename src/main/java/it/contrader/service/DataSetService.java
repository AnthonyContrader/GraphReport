package it.contrader.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import it.contrader.converter.DataSetConverter;
import it.contrader.dao.DataSetRepository;
import it.contrader.dto.DataSetDTO;
import it.contrader.model.DataSet;

@Service
public class DataSetService extends AbstractService<DataSet,DataSetDTO>{
	
	@Autowired
	private DataSetRepository repository;
	@Autowired
	private DataSetConverter converter;
	
	public List<DataSetDTO> findAllByUtente(DataSet dato){
		return converter.toDTOList(repository.findAllByUtente(dato.getUtente()));
	}
}
