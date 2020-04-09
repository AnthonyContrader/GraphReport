package it.contrader.graphreport.service;

import it.contrader.graphreport.service.dto.UnitamisuraDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Unitamisura.
 */
public interface UnitamisuraService {

    /**
     * Save a unitamisura.
     *
     * @param unitamisuraDTO the entity to save
     * @return the persisted entity
     */
    UnitamisuraDTO save(UnitamisuraDTO unitamisuraDTO);

    /**
     * Get all the unitamisuras.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<UnitamisuraDTO> findAll(Pageable pageable);


    /**
     * Get the "id" unitamisura.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<UnitamisuraDTO> findOne(Long id);

    /**
     * Delete the "id" unitamisura.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
