package it.contrader.dao;

import javax.transaction.Transactional;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.contrader.model.DataSet;

@Repository
@Transactional
public interface DataSetRepository extends CrudRepository<DataSet,Long>{

	List<DataSet> findAllByUtente(Long utente);
	
	List<DataSet> findAllByUtenteAndCategoria(Long utente, Long categoria);
	
	void deleteByUtenteAndCategoria(Long utente, Long categoria);

}
