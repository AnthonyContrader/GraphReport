package it.contrader.graphreport.service;

import it.contrader.graphreport.service.dto.MtMDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing MtM.
 */
public interface MtMService {

    /**
     * Save a mtM.
     *
     * @param mtMDTO the entity to save
     * @return the persisted entity
     */
    MtMDTO save(MtMDTO mtMDTO);

    /**
     * Get all the mtMS.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<MtMDTO> findAll(Pageable pageable);


    /**
     * Get the "id" mtM.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<MtMDTO> findOne(Long id);

    /**
     * Delete the "id" mtM.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
