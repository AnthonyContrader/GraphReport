package it.contrader.graphreport.repository;

import it.contrader.graphreport.domain.Graph;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Graph entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GraphRepository extends JpaRepository<Graph, Long> {

}
