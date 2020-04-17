package it.contrader.graphreport.service.impl;

import it.contrader.graphreport.service.GraphService;
import it.contrader.graphreport.domain.Graph;
import it.contrader.graphreport.repository.GraphRepository;
import it.contrader.graphreport.service.dto.GraphDTO;
import it.contrader.graphreport.service.mapper.GraphMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Graph.
 */
@Service
@Transactional
public class GraphServiceImpl implements GraphService {

    private final Logger log = LoggerFactory.getLogger(GraphServiceImpl.class);

    private final GraphRepository graphRepository;

    private final GraphMapper graphMapper;

    public GraphServiceImpl(GraphRepository graphRepository, GraphMapper graphMapper) {
        this.graphRepository = graphRepository;
        this.graphMapper = graphMapper;
    }

    /**
     * Save a graph.
     *
     * @param graphDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public GraphDTO save(GraphDTO graphDTO) {
        log.debug("Request to save Graph : {}", graphDTO);
        Graph graph = graphMapper.toEntity(graphDTO);
        graph = graphRepository.save(graph);
        return graphMapper.toDto(graph);
    }

    /**
     * Get all the graphs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GraphDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Graphs");
        return graphRepository.findAll(pageable)
            .map(graphMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<GraphDTO> findAllByUser(Long user,Pageable pageable) {
        log.debug("Request to get all Graphs");
        return graphRepository.findAllByUtente(user,pageable)
            .map(graphMapper::toDto);
    }
    
    
    /**
     * Get one graph by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<GraphDTO> findOne(Long id) {
        log.debug("Request to get Graph : {}", id);
        return graphRepository.findById(id)
            .map(graphMapper::toDto);
    }

    /**
     * Delete the graph by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Graph : {}", id);
        graphRepository.deleteById(id);
    }

	public Optional<GraphDTO> findLastModify() {
		log.debug("Request to get Graph by LastModify");
		Pageable page = PageRequest.of(0, 1, Sort.by("modify").descending());
		return graphRepository.findAll(page).stream().findFirst().map(graphMapper::toDto);
	}
}
