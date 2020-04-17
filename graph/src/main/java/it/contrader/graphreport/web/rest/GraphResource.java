package it.contrader.graphreport.web.rest;
import it.contrader.graphreport.service.GraphService;
import it.contrader.graphreport.web.rest.errors.BadRequestAlertException;
import it.contrader.graphreport.web.rest.util.HeaderUtil;
import it.contrader.graphreport.web.rest.util.PaginationUtil;
import it.contrader.graphreport.service.dto.DataSetDTO;
import it.contrader.graphreport.service.dto.GraphDTO;
import it.contrader.graphreport.service.dto.MtMDTO;
import it.contrader.graphreport.service.dto.ToDrawDTO;
import it.contrader.graphreport.service.impl.GraphServiceImpl;
import it.contrader.graphreport.service.impl.MtMServiceImpl;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing Graph.
 */
@RestController
@RequestMapping("/api")
public class GraphResource {

    private final Logger log = LoggerFactory.getLogger(GraphResource.class);

    private static final String ENTITY_NAME = "graphGraph";

    private final GraphServiceImpl graphService;
    
    private final MtMServiceImpl mtmService;   
    

    public GraphResource(GraphServiceImpl graphService, MtMServiceImpl mtmService) {
        this.graphService = graphService;
        this.mtmService = mtmService;
    }

    /**
     * POST  /graphs : Create a new graph.
     *
     * @param graphDTO the graphDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new graphDTO, or with status 400 (Bad Request) if the graph has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/graphs")
    public ResponseEntity<GraphDTO> createGraph(@RequestBody GraphDTO graphDTO) throws URISyntaxException {
        log.debug("REST request to save Graph : {}", graphDTO);
        if (graphDTO.getId() != null) {
            throw new BadRequestAlertException("A new graph cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GraphDTO result = graphService.save(graphDTO);
        return ResponseEntity.created(new URI("/api/graphs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /graphs : Updates an existing graph.
     *
     * @param graphDTO the graphDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated graphDTO,
     * or with status 400 (Bad Request) if the graphDTO is not valid,
     * or with status 500 (Internal Server Error) if the graphDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/graphs")
    public ResponseEntity<GraphDTO> updateGraph(@RequestBody GraphDTO graphDTO) throws URISyntaxException {
        log.debug("REST request to update Graph : {}", graphDTO);
        if (graphDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GraphDTO result = graphService.save(graphDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, graphDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /graphs : get all the graphs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of graphs in body
     */
    @GetMapping("/graphs")
    public ResponseEntity<List<GraphDTO>> getAllGraphs(Pageable pageable) {
        log.debug("REST request to get a page of Graphs");
        Page<GraphDTO> page = graphService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/graphs");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    
    @GetMapping("/getByUt")
    public ResponseEntity<List<GraphDTO>> getGraphsByUser(@RequestParam("id") Long id,@RequestParam("page") int npage,@RequestParam("size") int size,@RequestParam("sort") String sort) {
        log.debug("REST request to get a page of Graphs by user");
        Pageable p = PageRequest.of(npage, size, Sort.by(sort));
        Page<GraphDTO> page = graphService.findAllByUser(id,p);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/graphs");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    
    /**
     * GET  /graphs/:id : get the "id" graph.
     *
     * @param id the id of the graphDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the graphDTO, or with status 404 (Not Found)
     */
    @GetMapping("/graphs/{id}")
    public ResponseEntity<GraphDTO> getGraph(@PathVariable Long id) {
        log.debug("REST request to get Graph : {}", id);
        Optional<GraphDTO> graphDTO = graphService.findOne(id);
        return ResponseUtil.wrapOrNotFound(graphDTO);
    }

    /**
     * DELETE  /graphs/:id : delete the "id" graph.
     *
     * @param id the id of the graphDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/graphs/{id}")
    public ResponseEntity<Void> deleteGraph(@PathVariable Long id) {
        log.debug("REST request to delete Graph : {}", id);
        graphService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    @GetMapping("/lastModify")
    public ResponseEntity<GraphDTO> getGraphByLastModify() {
        log.debug("REST request to get Last Modify Graph");
        Optional<GraphDTO> graphDTO = graphService.findLastModify();
        return ResponseUtil.wrapOrNotFound(graphDTO);
    }
    
}
