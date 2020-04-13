package it.contrader.graphreport.repository;

import it.contrader.graphreport.domain.MtM;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MtM entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MtMRepository extends JpaRepository<MtM, Long> {

}
