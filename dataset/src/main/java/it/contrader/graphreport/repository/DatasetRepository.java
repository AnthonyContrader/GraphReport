package it.contrader.graphreport.repository;

import it.contrader.graphreport.domain.Dataset;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Dataset entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DatasetRepository extends JpaRepository<Dataset, Long> {
	
	//Spring Data
	public Page<Dataset> findAllByidUser(Long id, Pageable pageable);
	
	public Page<Dataset> findAllByidUserAndTitolo(Long id, String titolo, Pageable pageable);
	
	@Modifying
	public void deleteByidUserAndTitolo(Long id, String titolo);
	
	@Modifying
	public void deleteByidUser(Long id);
	
	//HQL --- aggiungere @Modifying in caso di una query update --- d e' il tipo di ritorno, in questo caso un dataset
	@Query("Select d from Dataset d where d.idUser=:id")
	public Page<Dataset> cicciocercamiquesto(Long id, Pageable pageable);
	
	
}
