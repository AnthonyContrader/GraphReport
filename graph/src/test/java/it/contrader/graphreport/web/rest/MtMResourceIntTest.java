package it.contrader.graphreport.web.rest;

import it.contrader.graphreport.GraphApp;

import it.contrader.graphreport.domain.MtM;
import it.contrader.graphreport.repository.MtMRepository;
import it.contrader.graphreport.service.MtMService;
import it.contrader.graphreport.service.dto.MtMDTO;
import it.contrader.graphreport.service.mapper.MtMMapper;
import it.contrader.graphreport.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;


import static it.contrader.graphreport.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import it.contrader.graphreport.domain.enumeration.TipoGrafico;
/**
 * Test class for the MtMResource REST controller.
 *
 * @see MtMResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GraphApp.class)
public class MtMResourceIntTest {

    private static final Long DEFAULT_UTENTE = 1L;
    private static final Long UPDATED_UTENTE = 2L;

    private static final String DEFAULT_ASSE = "AAAAAAAAAA";
    private static final String UPDATED_ASSE = "BBBBBBBBBB";

    private static final TipoGrafico DEFAULT_TIPO_SET = TipoGrafico.LINE;
    private static final TipoGrafico UPDATED_TIPO_SET = TipoGrafico.BAR;

    private static final String DEFAULT_COLORE = "AAAAAAAAAA";
    private static final String UPDATED_COLORE = "BBBBBBBBBB";

    @Autowired
    private MtMRepository mtMRepository;

    @Autowired
    private MtMMapper mtMMapper;

    @Autowired
    private MtMService mtMService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restMtMMockMvc;

    private MtM mtM;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MtMResource mtMResource = new MtMResource(mtMService);
        this.restMtMMockMvc = MockMvcBuilders.standaloneSetup(mtMResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MtM createEntity(EntityManager em) {
        MtM mtM = new MtM()
            .utente(DEFAULT_UTENTE)
            .asse(DEFAULT_ASSE)
            .tipoSet(DEFAULT_TIPO_SET)
            .colore(DEFAULT_COLORE);
        return mtM;
    }

    @Before
    public void initTest() {
        mtM = createEntity(em);
    }

    @Test
    @Transactional
    public void createMtM() throws Exception {
        int databaseSizeBeforeCreate = mtMRepository.findAll().size();

        // Create the MtM
        MtMDTO mtMDTO = mtMMapper.toDto(mtM);
        restMtMMockMvc.perform(post("/api/mt-ms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mtMDTO)))
            .andExpect(status().isCreated());

        // Validate the MtM in the database
        List<MtM> mtMList = mtMRepository.findAll();
        assertThat(mtMList).hasSize(databaseSizeBeforeCreate + 1);
        MtM testMtM = mtMList.get(mtMList.size() - 1);
        assertThat(testMtM.getUtente()).isEqualTo(DEFAULT_UTENTE);
        assertThat(testMtM.getAsse()).isEqualTo(DEFAULT_ASSE);
        assertThat(testMtM.getTipoSet()).isEqualTo(DEFAULT_TIPO_SET);
        assertThat(testMtM.getColore()).isEqualTo(DEFAULT_COLORE);
    }

    @Test
    @Transactional
    public void createMtMWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mtMRepository.findAll().size();

        // Create the MtM with an existing ID
        mtM.setId(1L);
        MtMDTO mtMDTO = mtMMapper.toDto(mtM);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMtMMockMvc.perform(post("/api/mt-ms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mtMDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MtM in the database
        List<MtM> mtMList = mtMRepository.findAll();
        assertThat(mtMList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMtMS() throws Exception {
        // Initialize the database
        mtMRepository.saveAndFlush(mtM);

        // Get all the mtMList
        restMtMMockMvc.perform(get("/api/mt-ms?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mtM.getId().intValue())))
            .andExpect(jsonPath("$.[*].utente").value(hasItem(DEFAULT_UTENTE.intValue())))
            .andExpect(jsonPath("$.[*].asse").value(hasItem(DEFAULT_ASSE.toString())))
            .andExpect(jsonPath("$.[*].tipoSet").value(hasItem(DEFAULT_TIPO_SET.toString())))
            .andExpect(jsonPath("$.[*].colore").value(hasItem(DEFAULT_COLORE.toString())));
    }
    
    @Test
    @Transactional
    public void getMtM() throws Exception {
        // Initialize the database
        mtMRepository.saveAndFlush(mtM);

        // Get the mtM
        restMtMMockMvc.perform(get("/api/mt-ms/{id}", mtM.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mtM.getId().intValue()))
            .andExpect(jsonPath("$.utente").value(DEFAULT_UTENTE.intValue()))
            .andExpect(jsonPath("$.asse").value(DEFAULT_ASSE.toString()))
            .andExpect(jsonPath("$.tipoSet").value(DEFAULT_TIPO_SET.toString()))
            .andExpect(jsonPath("$.colore").value(DEFAULT_COLORE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMtM() throws Exception {
        // Get the mtM
        restMtMMockMvc.perform(get("/api/mt-ms/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMtM() throws Exception {
        // Initialize the database
        mtMRepository.saveAndFlush(mtM);

        int databaseSizeBeforeUpdate = mtMRepository.findAll().size();

        // Update the mtM
        MtM updatedMtM = mtMRepository.findById(mtM.getId()).get();
        // Disconnect from session so that the updates on updatedMtM are not directly saved in db
        em.detach(updatedMtM);
        updatedMtM
            .utente(UPDATED_UTENTE)
            .asse(UPDATED_ASSE)
            .tipoSet(UPDATED_TIPO_SET)
            .colore(UPDATED_COLORE);
        MtMDTO mtMDTO = mtMMapper.toDto(updatedMtM);

        restMtMMockMvc.perform(put("/api/mt-ms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mtMDTO)))
            .andExpect(status().isOk());

        // Validate the MtM in the database
        List<MtM> mtMList = mtMRepository.findAll();
        assertThat(mtMList).hasSize(databaseSizeBeforeUpdate);
        MtM testMtM = mtMList.get(mtMList.size() - 1);
        assertThat(testMtM.getUtente()).isEqualTo(UPDATED_UTENTE);
        assertThat(testMtM.getAsse()).isEqualTo(UPDATED_ASSE);
        assertThat(testMtM.getTipoSet()).isEqualTo(UPDATED_TIPO_SET);
        assertThat(testMtM.getColore()).isEqualTo(UPDATED_COLORE);
    }

    @Test
    @Transactional
    public void updateNonExistingMtM() throws Exception {
        int databaseSizeBeforeUpdate = mtMRepository.findAll().size();

        // Create the MtM
        MtMDTO mtMDTO = mtMMapper.toDto(mtM);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMtMMockMvc.perform(put("/api/mt-ms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mtMDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MtM in the database
        List<MtM> mtMList = mtMRepository.findAll();
        assertThat(mtMList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMtM() throws Exception {
        // Initialize the database
        mtMRepository.saveAndFlush(mtM);

        int databaseSizeBeforeDelete = mtMRepository.findAll().size();

        // Delete the mtM
        restMtMMockMvc.perform(delete("/api/mt-ms/{id}", mtM.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MtM> mtMList = mtMRepository.findAll();
        assertThat(mtMList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MtM.class);
        MtM mtM1 = new MtM();
        mtM1.setId(1L);
        MtM mtM2 = new MtM();
        mtM2.setId(mtM1.getId());
        assertThat(mtM1).isEqualTo(mtM2);
        mtM2.setId(2L);
        assertThat(mtM1).isNotEqualTo(mtM2);
        mtM1.setId(null);
        assertThat(mtM1).isNotEqualTo(mtM2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MtMDTO.class);
        MtMDTO mtMDTO1 = new MtMDTO();
        mtMDTO1.setId(1L);
        MtMDTO mtMDTO2 = new MtMDTO();
        assertThat(mtMDTO1).isNotEqualTo(mtMDTO2);
        mtMDTO2.setId(mtMDTO1.getId());
        assertThat(mtMDTO1).isEqualTo(mtMDTO2);
        mtMDTO2.setId(2L);
        assertThat(mtMDTO1).isNotEqualTo(mtMDTO2);
        mtMDTO1.setId(null);
        assertThat(mtMDTO1).isNotEqualTo(mtMDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mtMMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mtMMapper.fromId(null)).isNull();
    }
}
