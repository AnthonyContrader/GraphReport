package it.contrader.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import it.contrader.converter.DataSetConverter;
import it.contrader.dao.DataSetRepository;
import it.contrader.dto.DataSetDTO;
import it.contrader.model.DataSet;
import it.contrader.model.User;

@Service
public class DataSetService extends AbstractService<DataSet,DataSetDTO>{
	
	@Autowired
	private DataSetRepository repository;
	@Autowired
	private DataSetConverter converter;
	
	public List<DataSetDTO> findAllByUtente(Long utente){
		return converter.toDTOList(repository.findAllByUtente_Id(utente));
	}
	
	public List<DataSetDTO> findAllByUtenteAndCategoria(Long utente, Long categoria){
		return converter.toDTOList(repository.findAllByUtente_IdAndCategoria_Id(utente,categoria));
	}
	
	public void deleteDataSet(Long utente, Long categoria) {
		repository.deleteByUtente_IdAndCategoria_Id(utente, categoria);
	}
		
	public int updateDS(String valore, String commento, Long id) {
		return repository.updateValoreById(valore, commento, id);
	}

	public boolean exist(Long ut, Long cat) {
		return repository.existsByUtente_IdAndCategoria_Id(ut,cat);
	}

	public boolean exist(Long ut, Long cat, Long um) {
		return repository.existsByUtente_IdAndCategoria_IdAndUnitaMisura_Id(ut,cat,um);
	} 
}
