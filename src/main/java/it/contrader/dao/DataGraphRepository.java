package it.contrader.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.contrader.model.DataGraph;

@Repository
@Transactional
public interface DataGraphRepository  extends CrudRepository<DataGraph,Long>{
	
	@Modifying
	@Query("DELETE FROM DataGraph dg WHERE dg.graph.id = ?1")
	public void deleteByGraphId(Long id);
}
