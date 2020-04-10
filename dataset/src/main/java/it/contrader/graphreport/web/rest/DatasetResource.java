package it.contrader.graphreport.web.rest;

import it.contrader.graphreport.service.DatasetService;
import it.contrader.graphreport.web.rest.errors.BadRequestAlertException;
import it.contrader.graphreport.service.dto.DatasetDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link it.contrader.graphreport.domain.Dataset}.
 */
@RestController
@RequestMapping("/api")
public class DatasetResource {

    private final Logger log = LoggerFactory.getLogger(DatasetResource.class);

    private static final String ENTITY_NAME = "datasetDataset";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DatasetService datasetService;

    public DatasetResource(DatasetService datasetService) {
        this.datasetService = datasetService;
    }

    /**
     * {@code POST  /datasets} : Create a new dataset.
     *
     * @param datasetDTO the datasetDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new datasetDTO, or with status {@code 400 (Bad Request)} if the dataset has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/datasets")
    public ResponseEntity<DatasetDTO> createDataset(@Valid @RequestBody DatasetDTO datasetDTO) throws URISyntaxException {
        log.debug("REST request to save Dataset : {}", datasetDTO);
        if (datasetDTO.getId() != null) {
            throw new BadRequestAlertException("A new dataset cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DatasetDTO result = datasetService.save(datasetDTO);
        return ResponseEntity.created(new URI("/api/datasets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /datasets} : Updates an existing dataset.
     *
     * @param datasetDTO the datasetDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated datasetDTO,
     * or with status {@code 400 (Bad Request)} if the datasetDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the datasetDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/datasets")
    public ResponseEntity<DatasetDTO> updateDataset(@Valid @RequestBody DatasetDTO datasetDTO) throws URISyntaxException {
        log.debug("REST request to update Dataset : {}", datasetDTO);
        if (datasetDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DatasetDTO result = datasetService.save(datasetDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, datasetDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /datasets} : get all the datasets.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of datasets in body.
     */
    @GetMapping("/datasets")
    public ResponseEntity<List<DatasetDTO>> getAllDatasets(Pageable pageable) {
        log.debug("REST request to get a page of Datasets");
        Page<DatasetDTO> page = datasetService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /datasets/:id} : get the "id" dataset.
     *
     * @param id the id of the datasetDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the datasetDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/datasets/{id}")
    public ResponseEntity<DatasetDTO> getDataset(@PathVariable Long id) {
        log.debug("REST request to get Dataset : {}", id);
        Optional<DatasetDTO> datasetDTO = datasetService.findOne(id);
        return ResponseUtil.wrapOrNotFound(datasetDTO);
    }

    /**
     * {@code DELETE  /datasets/:id} : delete the "id" dataset.
     *
     * @param id the id of the datasetDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/datasets/{id}")
    public ResponseEntity<Void> deleteDataset(@PathVariable Long id) {
        log.debug("REST request to delete Dataset : {}", id);
        datasetService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
