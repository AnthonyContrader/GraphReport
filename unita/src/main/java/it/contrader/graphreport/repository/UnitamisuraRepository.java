package it.contrader.graphreport.repository;

import it.contrader.graphreport.domain.Categoria;
import it.contrader.graphreport.domain.Unitamisura;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Unitamisura entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UnitamisuraRepository extends JpaRepository<Unitamisura, Long> {
	
	public Page<Unitamisura> findAllByCategoria_id(Long categoria, Pageable pageable);

}
