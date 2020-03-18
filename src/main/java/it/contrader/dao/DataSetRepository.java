package it.contrader.dao;

import javax.transaction.Transactional;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.contrader.model.DataSet;

@Repository
@Transactional
public interface DataSetRepository extends CrudRepository<DataSet,Long>{

	List<DataSet> findAllByUtente(Long utente);
	
	List<DataSet> findAllByUtenteAndCategoria(Long utente, Long categoria);
	
	void deleteByUtenteAndCategoria(Long utente, Long categoria);
	
	@Modifying
	@Query("update DataSet set valore = ?1 where id = ?2")
	int updateValoreById(String valore, Long id);

}
