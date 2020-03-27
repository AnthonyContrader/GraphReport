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

	@Query("SELECT ds FROM DataSet ds WHERE ds.utente.id = ?1 ORDER BY ds.categoria.id")
	List<DataSet> findAllByUtente_Id(Long utente);
	
	List<DataSet> findAllByUtente_IdAndCategoria_Id(Long utente, Long categoria);
	
	void deleteByUtente_IdAndCategoria_Id(Long utente, Long categoria);
	
	@Modifying
	@Query("update DataSet set valore = ?1, commento= ?2 where id = ?3")
	int updateValoreById(String valore, String commento, Long id);
		
	boolean existsByUtente_IdAndCategoria_Id(Long ut, Long cat);

	boolean existsByUtente_IdAndCategoria_IdAndUnitaMisura_Id(Long ut, Long cat, Long um);
	
	@Query("select d from DataSet d where d.utente.id = ?1 group by d.categoria.id ORDER BY d.categoria.id")
	List<DataSet> countDS(Long id);
}
