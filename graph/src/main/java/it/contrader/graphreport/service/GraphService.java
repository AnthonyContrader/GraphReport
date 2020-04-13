package it.contrader.graphreport.service;

import it.contrader.graphreport.service.dto.GraphDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Graph.
 */
public interface GraphService {

    /**
     * Save a graph.
     *
     * @param graphDTO the entity to save
     * @return the persisted entity
     */
    GraphDTO save(GraphDTO graphDTO);

    /**
     * Get all the graphs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<GraphDTO> findAll(Pageable pageable);


    /**
     * Get the "id" graph.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<GraphDTO> findOne(Long id);

    /**
     * Delete the "id" graph.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
