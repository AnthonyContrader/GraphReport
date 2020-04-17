package it.contrader.graphreport.web.rest;
import it.contrader.graphreport.service.MtMService;
import it.contrader.graphreport.web.rest.errors.BadRequestAlertException;
import it.contrader.graphreport.web.rest.util.HeaderUtil;
import it.contrader.graphreport.web.rest.util.PaginationUtil;
import it.contrader.graphreport.service.dto.MtMDTO;
import it.contrader.graphreport.service.impl.MtMServiceImpl;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing MtM.
 */
@RestController
@RequestMapping("/api")
public class MtMResource {

    private final Logger log = LoggerFactory.getLogger(MtMResource.class);

    private static final String ENTITY_NAME = "graphMtM";

    private final MtMServiceImpl mtMService;

    public MtMResource(MtMServiceImpl mtMService) {
        this.mtMService = mtMService;
    }

    /**
     * POST  /mt-ms : Create a new mtM.
     *
     * @param mtMDTO the mtMDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new mtMDTO, or with status 400 (Bad Request) if the mtM has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/mt-ms")
    public ResponseEntity<MtMDTO> createMtM(@RequestBody MtMDTO mtMDTO) throws URISyntaxException {
        log.debug("REST request to save MtM : {}", mtMDTO);
        if (mtMDTO.getId() != null) {
            throw new BadRequestAlertException("A new mtM cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MtMDTO result = mtMService.save(mtMDTO);
        return ResponseEntity.created(new URI("/api/mt-ms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /mt-ms : Updates an existing mtM.
     *
     * @param mtMDTO the mtMDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated mtMDTO,
     * or with status 400 (Bad Request) if the mtMDTO is not valid,
     * or with status 500 (Internal Server Error) if the mtMDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/mt-ms")
    public ResponseEntity<MtMDTO> updateMtM(@RequestBody MtMDTO mtMDTO) throws URISyntaxException {
        log.debug("REST request to update MtM : {}", mtMDTO);
        if (mtMDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MtMDTO result = mtMService.save(mtMDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, mtMDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /mt-ms : get all the mtMS.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of mtMS in body
     */
    @GetMapping("/mt-ms")
    public ResponseEntity<List<MtMDTO>> getAllMtMS(Pageable pageable) {
        log.debug("REST request to get a page of MtMS");
        Page<MtMDTO> page = mtMService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/mt-ms");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /mt-ms/:id : get the "id" mtM.
     *
     * @param id the id of the mtMDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the mtMDTO, or with status 404 (Not Found)
     */
    @GetMapping("/mt-ms/{id}")
    public ResponseEntity<MtMDTO> getMtM(@PathVariable Long id) {
        log.debug("REST request to get MtM : {}", id);
        Optional<MtMDTO> mtMDTO = mtMService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mtMDTO);
    }

    /**
     * DELETE  /mt-ms/:id : delete the "id" mtM.
     *
     * @param id the id of the mtMDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/mt-ms/{id}")
    public ResponseEntity<Void> deleteMtM(@PathVariable Long id) {
        log.debug("REST request to delete MtM : {}", id);
        mtMService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    @GetMapping("/getMtMByGraph/{id}")
    public ResponseEntity<List<MtMDTO>> getAllByGraph(@PathVariable Long id) {
        log.debug("REST request to get all By Graph");
        return ResponseEntity.ok().body(mtMService.getAllByGraph(id));
    }
    
}
