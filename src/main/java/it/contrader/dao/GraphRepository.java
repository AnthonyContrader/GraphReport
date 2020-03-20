package it.contrader.dao;

import javax.transaction.Transactional;
import java.util.*;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.contrader.model.Graph;


@Repository
@Transactional
public interface GraphRepository  extends CrudRepository<Graph,Long>{
	
	

}
