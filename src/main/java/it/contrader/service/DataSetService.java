package it.contrader.service;

import org.springframework.stereotype.Service;

import java.util.List;
import it.contrader.dao.DataSetRepository;
import it.contrader.dto.DataSetDTO;
import it.contrader.model.DataSet;

@Service
public class DataSetService extends AbstractService<DataSet,DataSetDTO>{
	
	public List<DataSetDTO> findAllByUtente(Long utente){
		return converter.toDTOList(((DataSetRepository)repository).findAllByUtente_Id(utente));
	}
	
	public List<DataSetDTO> findAllByUtenteAndCategoria(Long utente, Long categoria){
		return converter.toDTOList(((DataSetRepository)repository).findAllByUtente_IdAndCategoria_Id(utente,categoria));
	}
	
	public void deleteDataSet(Long utente, Long categoria) {
		((DataSetRepository)repository).deleteByUtente_IdAndCategoria_Id(utente, categoria);
	}
		
	public int updateDS(String valore, String commento, Long id) {
		return ((DataSetRepository)repository).updateValoreById(valore, commento, id);
	}

	public boolean exist(Long ut, Long cat) {
		return ((DataSetRepository)repository).existsByUtente_IdAndCategoria_Id(ut,cat);
	}

	public boolean exist(Long ut, Long cat, Long um) {
		return ((DataSetRepository)repository).existsByUtente_IdAndCategoria_IdAndUnitaMisura_Id(ut,cat,um);
	}
	
	public List<DataSetDTO> countDS(Long id) {
		return converter.toDTOList(((DataSetRepository)repository).countDS(id));
	}
	
	public List<DataSetDTO> getUMList(Long id, Long cat) {
		return converter.toDTOList(((DataSetRepository)repository).getUMList(id,cat));
	}
	
	public boolean createDS(DataSetDTO dtop,DataSetDTO dtos) {
		if(((DataSetRepository)repository).createDB(dtop.getUtente(),dtop.getCategoria(),dtop.getUnitaMisura(),dtop.getValore(),dtop.getCommento()))
			if(((DataSetRepository)repository).createDB(dtop.getUtente(),dtop.getCategoria(),dtop.getUnitaMisura(),dtop.getValore(),dtop.getCommento()))
				return true;
		return false;
	}
	
}
