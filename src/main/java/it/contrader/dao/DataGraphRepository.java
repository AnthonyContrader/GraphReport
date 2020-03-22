package it.contrader.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.contrader.model.DataGraph;
import it.contrader.model.Graph;


@Repository
@Transactional
public interface DataGraphRepository  extends CrudRepository<DataGraph,Long>{
	
	
}
