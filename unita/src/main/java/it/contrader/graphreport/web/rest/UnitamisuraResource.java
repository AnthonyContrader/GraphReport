package it.contrader.graphreport.web.rest;

import com.codahale.metrics.annotation.Timed;
import it.contrader.graphreport.service.UnitamisuraService;
import it.contrader.graphreport.web.rest.errors.BadRequestAlertException;
import it.contrader.graphreport.web.rest.util.HeaderUtil;
import it.contrader.graphreport.web.rest.util.PaginationUtil;
import it.contrader.graphreport.service.dto.UnitamisuraDTO;
import it.contrader.graphreport.service.impl.UnitamisuraServiceImpl;
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

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Unitamisura.
 */
@RestController
@RequestMapping("/api")
public class UnitamisuraResource {

    private final Logger log = LoggerFactory.getLogger(UnitamisuraResource.class);

    private static final String ENTITY_NAME = "unitamisura";

    private final UnitamisuraServiceImpl unitamisuraService;

    public UnitamisuraResource(UnitamisuraServiceImpl unitamisuraService) {
        this.unitamisuraService = unitamisuraService;
    }

    /**
     * POST  /unitamisuras : Create a new unitamisura.
     *
     * @param unitamisuraDTO the unitamisuraDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new unitamisuraDTO, or with status 400 (Bad Request) if the unitamisura has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/unitamisuras")
    @Timed
    public ResponseEntity<UnitamisuraDTO> createUnitamisura(@RequestBody UnitamisuraDTO unitamisuraDTO) throws URISyntaxException {
        log.debug("REST request to save Unitamisura : {}", unitamisuraDTO);
        if (unitamisuraDTO.getId() != null) {
            throw new BadRequestAlertException("A new unitamisura cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UnitamisuraDTO result = unitamisuraService.save(unitamisuraDTO);
        return ResponseEntity.created(new URI("/api/unitamisuras/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /unitamisuras : Updates an existing unitamisura.
     *
     * @param unitamisuraDTO the unitamisuraDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated unitamisuraDTO,
     * or with status 400 (Bad Request) if the unitamisuraDTO is not valid,
     * or with status 500 (Internal Server Error) if the unitamisuraDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/unitamisuras")
    @Timed
    public ResponseEntity<UnitamisuraDTO> updateUnitamisura(@RequestBody UnitamisuraDTO unitamisuraDTO) throws URISyntaxException {
        log.debug("REST request to update Unitamisura : {}", unitamisuraDTO);
        if (unitamisuraDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UnitamisuraDTO result = unitamisuraService.save(unitamisuraDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, unitamisuraDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /unitamisuras : get all the unitamisuras.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of unitamisuras in body
     */
    @GetMapping("/unitamisuras")
    @Timed
    public ResponseEntity<List<UnitamisuraDTO>> getAllUnitamisuras(Pageable pageable) {
        log.debug("REST request to get a page of Unitamisuras");
        Page<UnitamisuraDTO> page = unitamisuraService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/unitamisuras");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    @GetMapping("/unitabycategoria/{categoriaId}")
    public ResponseEntity<List<UnitamisuraDTO>> getAllByCategoria(@PathVariable Long categoriaId) {
        log.debug("REST request to get a page of Unitamisuras by Categoria: {}", categoriaId);
        Pageable p = PageRequest.of(0, Integer.MAX_VALUE, Sort.by("nome"));
        Page<UnitamisuraDTO> page = unitamisuraService.findAllByCategoria(categoriaId,p);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/unitabycategoria");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /unitamisuras/:id : get the "id" unitamisura.
     *
     * @param id the id of the unitamisuraDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the unitamisuraDTO, or with status 404 (Not Found)
     */
    @GetMapping("/unitamisuras/{id}")
    @Timed
    public ResponseEntity<UnitamisuraDTO> getUnitamisura(@PathVariable Long id) {
        log.debug("REST request to get Unitamisura : {}", id);
        Optional<UnitamisuraDTO> unitamisuraDTO = unitamisuraService.findOne(id);
        return ResponseUtil.wrapOrNotFound(unitamisuraDTO);
    }

    /**
     * DELETE  /unitamisuras/:id : delete the "id" unitamisura.
     *
     * @param id the id of the unitamisuraDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/unitamisuras/{id}")
    @Timed
    public ResponseEntity<Void> deleteUnitamisura(@PathVariable Long id) {
        log.debug("REST request to delete Unitamisura : {}", id);
        unitamisuraService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
