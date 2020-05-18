package it.contrader.graphreport.repository;

import it.contrader.graphreport.domain.Graph;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Graph entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GraphRepository extends JpaRepository<Graph, Long> {

	public Page<Graph> findAllByUtente(Long utente,Pageable page);
	
	@Modifying
	public void deleteByutente(Long id);
	
}
