package it.contrader.graphreport.repository;

import it.contrader.graphreport.domain.MtM;
import it.contrader.graphreport.service.dto.MtMDTO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MtM entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MtMRepository extends JpaRepository<MtM, Long> {

	List<MtM> findAllByGraph_id(Long id);

}
