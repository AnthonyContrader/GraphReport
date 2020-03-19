package it.contrader.dao;

import javax.transaction.Transactional;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.contrader.model.DataSet;
import it.contrader.model.User;

@Repository
@Transactional
public interface DataSetRepository extends CrudRepository<DataSet,Long>{

	List<DataSet> findAllByUtente_Id(Long utente);
	
	List<DataSet> findAllByUtente_IdAndCategoria_Id(Long utente, Long categoria);
	
	void deleteByUtente_IdAndCategoria_Id(Long utente, Long categoria);
	
	@Modifying
	@Query("update DataSet set valore = ?1 where id = ?2")
	int updateValoreById(String valore, Long id);

}