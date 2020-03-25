package it.contrader.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.contrader.model.Graph;


@Repository
@Transactional
public interface GraphRepository  extends CrudRepository<Graph,Long>{
	
	@Query("SELECT DISTINCT dg.graph FROM DataGraph dg WHERE dg.dataSet.utente.id= ?1")
	public List<Graph> findGraphByUser(Long id);
	
}
