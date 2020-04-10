package it.contrader.graphreport.web.rest;

import it.contrader.graphreport.DatasetApp;
import it.contrader.graphreport.domain.Dataset;
import it.contrader.graphreport.repository.DatasetRepository;
import it.contrader.graphreport.service.DatasetService;
import it.contrader.graphreport.service.dto.DatasetDTO;
import it.contrader.graphreport.service.mapper.DatasetMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link DatasetResource} REST controller.
 */
@SpringBootTest(classes = DatasetApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class DatasetResourceIT {

    private static final String DEFAULT_TITOLO = "AAAAAAAAAA";
    private static final String UPDATED_TITOLO = "BBBBBBBBBB";

    private static final String DEFAULT_VALORI = "AAAAAAAAAA";
    private static final String UPDATED_VALORI = "BBBBBBBBBB";

    private static final String DEFAULT_COMMENTO = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTO = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_USER = 1L;
    private static final Long UPDATED_ID_USER = 2L;

    private static final Long DEFAULT_ID_UNITA = 1L;
    private static final Long UPDATED_ID_UNITA = 2L;

    @Autowired
    private DatasetRepository datasetRepository;

    @Autowired
    private DatasetMapper datasetMapper;

    @Autowired
    private DatasetService datasetService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDatasetMockMvc;

    private Dataset dataset;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Dataset createEntity(EntityManager em) {
        Dataset dataset = new Dataset()
            .titolo(DEFAULT_TITOLO)
            .valori(DEFAULT_VALORI)
            .commento(DEFAULT_COMMENTO)
            .idUser(DEFAULT_ID_USER)
            .idUnita(DEFAULT_ID_UNITA);
        return dataset;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Dataset createUpdatedEntity(EntityManager em) {
        Dataset dataset = new Dataset()
            .titolo(UPDATED_TITOLO)
            .valori(UPDATED_VALORI)
            .commento(UPDATED_COMMENTO)
            .idUser(UPDATED_ID_USER)
            .idUnita(UPDATED_ID_UNITA);
        return dataset;
    }

    @BeforeEach
    public void initTest() {
        dataset = createEntity(em);
    }

    @Test
    @Transactional
    public void createDataset() throws Exception {
        int databaseSizeBeforeCreate = datasetRepository.findAll().size();

        // Create the Dataset
        DatasetDTO datasetDTO = datasetMapper.toDto(dataset);
        restDatasetMockMvc.perform(post("/api/datasets")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(datasetDTO)))
            .andExpect(status().isCreated());

        // Validate the Dataset in the database
        List<Dataset> datasetList = datasetRepository.findAll();
        assertThat(datasetList).hasSize(databaseSizeBeforeCreate + 1);
        Dataset testDataset = datasetList.get(datasetList.size() - 1);
        assertThat(testDataset.getTitolo()).isEqualTo(DEFAULT_TITOLO);
        assertThat(testDataset.getValori()).isEqualTo(DEFAULT_VALORI);
        assertThat(testDataset.getCommento()).isEqualTo(DEFAULT_COMMENTO);
        assertThat(testDataset.getIdUser()).isEqualTo(DEFAULT_ID_USER);
        assertThat(testDataset.getIdUnita()).isEqualTo(DEFAULT_ID_UNITA);
    }

    @Test
    @Transactional
    public void createDatasetWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = datasetRepository.findAll().size();

        // Create the Dataset with an existing ID
        dataset.setId(1L);
        DatasetDTO datasetDTO = datasetMapper.toDto(dataset);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDatasetMockMvc.perform(post("/api/datasets")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(datasetDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Dataset in the database
        List<Dataset> datasetList = datasetRepository.findAll();
        assertThat(datasetList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTitoloIsRequired() throws Exception {
        int databaseSizeBeforeTest = datasetRepository.findAll().size();
        // set the field null
        dataset.setTitolo(null);

        // Create the Dataset, which fails.
        DatasetDTO datasetDTO = datasetMapper.toDto(dataset);

        restDatasetMockMvc.perform(post("/api/datasets")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(datasetDTO)))
            .andExpect(status().isBadRequest());

        List<Dataset> datasetList = datasetRepository.findAll();
        assertThat(datasetList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkValoriIsRequired() throws Exception {
        int databaseSizeBeforeTest = datasetRepository.findAll().size();
        // set the field null
        dataset.setValori(null);

        // Create the Dataset, which fails.
        DatasetDTO datasetDTO = datasetMapper.toDto(dataset);

        restDatasetMockMvc.perform(post("/api/datasets")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(datasetDTO)))
            .andExpect(status().isBadRequest());

        List<Dataset> datasetList = datasetRepository.findAll();
        assertThat(datasetList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = datasetRepository.findAll().size();
        // set the field null
        dataset.setIdUser(null);

        // Create the Dataset, which fails.
        DatasetDTO datasetDTO = datasetMapper.toDto(dataset);

        restDatasetMockMvc.perform(post("/api/datasets")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(datasetDTO)))
            .andExpect(status().isBadRequest());

        List<Dataset> datasetList = datasetRepository.findAll();
        assertThat(datasetList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdUnitaIsRequired() throws Exception {
        int databaseSizeBeforeTest = datasetRepository.findAll().size();
        // set the field null
        dataset.setIdUnita(null);

        // Create the Dataset, which fails.
        DatasetDTO datasetDTO = datasetMapper.toDto(dataset);

        restDatasetMockMvc.perform(post("/api/datasets")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(datasetDTO)))
            .andExpect(status().isBadRequest());

        List<Dataset> datasetList = datasetRepository.findAll();
        assertThat(datasetList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDatasets() throws Exception {
        // Initialize the database
        datasetRepository.saveAndFlush(dataset);

        // Get all the datasetList
        restDatasetMockMvc.perform(get("/api/datasets?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dataset.getId().intValue())))
            .andExpect(jsonPath("$.[*].titolo").value(hasItem(DEFAULT_TITOLO)))
            .andExpect(jsonPath("$.[*].valori").value(hasItem(DEFAULT_VALORI)))
            .andExpect(jsonPath("$.[*].commento").value(hasItem(DEFAULT_COMMENTO)))
            .andExpect(jsonPath("$.[*].idUser").value(hasItem(DEFAULT_ID_USER.intValue())))
            .andExpect(jsonPath("$.[*].idUnita").value(hasItem(DEFAULT_ID_UNITA.intValue())));
    }
    
    @Test
    @Transactional
    public void getDataset() throws Exception {
        // Initialize the database
        datasetRepository.saveAndFlush(dataset);

        // Get the dataset
        restDatasetMockMvc.perform(get("/api/datasets/{id}", dataset.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dataset.getId().intValue()))
            .andExpect(jsonPath("$.titolo").value(DEFAULT_TITOLO))
            .andExpect(jsonPath("$.valori").value(DEFAULT_VALORI))
            .andExpect(jsonPath("$.commento").value(DEFAULT_COMMENTO))
            .andExpect(jsonPath("$.idUser").value(DEFAULT_ID_USER.intValue()))
            .andExpect(jsonPath("$.idUnita").value(DEFAULT_ID_UNITA.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingDataset() throws Exception {
        // Get the dataset
        restDatasetMockMvc.perform(get("/api/datasets/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDataset() throws Exception {
        // Initialize the database
        datasetRepository.saveAndFlush(dataset);

        int databaseSizeBeforeUpdate = datasetRepository.findAll().size();

        // Update the dataset
        Dataset updatedDataset = datasetRepository.findById(dataset.getId()).get();
        // Disconnect from session so that the updates on updatedDataset are not directly saved in db
        em.detach(updatedDataset);
        updatedDataset
            .titolo(UPDATED_TITOLO)
            .valori(UPDATED_VALORI)
            .commento(UPDATED_COMMENTO)
            .idUser(UPDATED_ID_USER)
            .idUnita(UPDATED_ID_UNITA);
        DatasetDTO datasetDTO = datasetMapper.toDto(updatedDataset);

        restDatasetMockMvc.perform(put("/api/datasets")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(datasetDTO)))
            .andExpect(status().isOk());

        // Validate the Dataset in the database
        List<Dataset> datasetList = datasetRepository.findAll();
        assertThat(datasetList).hasSize(databaseSizeBeforeUpdate);
        Dataset testDataset = datasetList.get(datasetList.size() - 1);
        assertThat(testDataset.getTitolo()).isEqualTo(UPDATED_TITOLO);
        assertThat(testDataset.getValori()).isEqualTo(UPDATED_VALORI);
        assertThat(testDataset.getCommento()).isEqualTo(UPDATED_COMMENTO);
        assertThat(testDataset.getIdUser()).isEqualTo(UPDATED_ID_USER);
        assertThat(testDataset.getIdUnita()).isEqualTo(UPDATED_ID_UNITA);
    }

    @Test
    @Transactional
    public void updateNonExistingDataset() throws Exception {
        int databaseSizeBeforeUpdate = datasetRepository.findAll().size();

        // Create the Dataset
        DatasetDTO datasetDTO = datasetMapper.toDto(dataset);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDatasetMockMvc.perform(put("/api/datasets")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(datasetDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Dataset in the database
        List<Dataset> datasetList = datasetRepository.findAll();
        assertThat(datasetList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDataset() throws Exception {
        // Initialize the database
        datasetRepository.saveAndFlush(dataset);

        int databaseSizeBeforeDelete = datasetRepository.findAll().size();

        // Delete the dataset
        restDatasetMockMvc.perform(delete("/api/datasets/{id}", dataset.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Dataset> datasetList = datasetRepository.findAll();
        assertThat(datasetList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
