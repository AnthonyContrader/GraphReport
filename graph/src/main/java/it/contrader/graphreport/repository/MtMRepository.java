package it.contrader.graphreport.repository;

import it.contrader.graphreport.domain.MtM;
import it.contrader.graphreport.service.dto.MtMDTO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Spring Data  repository for the MtM entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MtMRepository extends JpaRepository<MtM, Long> {

	List<MtM> findAllByGraph_id(Long id);
	
	@Transactional
	@Modifying
	void deleteByGraph_Id(Long id);

}
