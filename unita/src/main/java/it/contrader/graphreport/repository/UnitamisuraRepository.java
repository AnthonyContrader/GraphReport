package it.contrader.graphreport.repository;

import it.contrader.graphreport.domain.Unitamisura;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Unitamisura entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UnitamisuraRepository extends JpaRepository<Unitamisura, Long> {

}
