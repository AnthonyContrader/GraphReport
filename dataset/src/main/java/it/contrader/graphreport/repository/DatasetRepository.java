package it.contrader.graphreport.repository;

import it.contrader.graphreport.domain.Dataset;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Dataset entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DatasetRepository extends JpaRepository<Dataset, Long> {
}
