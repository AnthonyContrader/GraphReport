package it.contrader.graphreport.web.rest;

import it.contrader.graphreport.UnitaApp;

import it.contrader.graphreport.domain.Unitamisura;
import it.contrader.graphreport.domain.Categoria;
import it.contrader.graphreport.repository.UnitamisuraRepository;
import it.contrader.graphreport.service.UnitamisuraService;
import it.contrader.graphreport.service.dto.UnitamisuraDTO;
import it.contrader.graphreport.service.mapper.UnitamisuraMapper;
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

import javax.persistence.EntityManager;
import java.util.List;


import static it.contrader.graphreport.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the UnitamisuraResource REST controller.
 *
 * @see UnitamisuraResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UnitaApp.class)
public class UnitamisuraResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    @Autowired
    private UnitamisuraRepository unitamisuraRepository;


    @Autowired
    private UnitamisuraMapper unitamisuraMapper;
    

    @Autowired
    private UnitamisuraService unitamisuraService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUnitamisuraMockMvc;

    private Unitamisura unitamisura;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UnitamisuraResource unitamisuraResource = new UnitamisuraResource(unitamisuraService);
        this.restUnitamisuraMockMvc = MockMvcBuilders.standaloneSetup(unitamisuraResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Unitamisura createEntity(EntityManager em) {
        Unitamisura unitamisura = new Unitamisura()
            .nome(DEFAULT_NOME);
        // Add required entity
        Categoria categoria = CategoriaResourceIntTest.createEntity(em);
        em.persist(categoria);
        em.flush();
        unitamisura.setUnicat(categoria);
        return unitamisura;
    }

    @Before
    public void initTest() {
        unitamisura = createEntity(em);
    }

    @Test
    @Transactional
    public void createUnitamisura() throws Exception {
        int databaseSizeBeforeCreate = unitamisuraRepository.findAll().size();

        // Create the Unitamisura
        UnitamisuraDTO unitamisuraDTO = unitamisuraMapper.toDto(unitamisura);
        restUnitamisuraMockMvc.perform(post("/api/unitamisuras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(unitamisuraDTO)))
            .andExpect(status().isCreated());

        // Validate the Unitamisura in the database
        List<Unitamisura> unitamisuraList = unitamisuraRepository.findAll();
        assertThat(unitamisuraList).hasSize(databaseSizeBeforeCreate + 1);
        Unitamisura testUnitamisura = unitamisuraList.get(unitamisuraList.size() - 1);
        assertThat(testUnitamisura.getNome()).isEqualTo(DEFAULT_NOME);
    }

    @Test
    @Transactional
    public void createUnitamisuraWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = unitamisuraRepository.findAll().size();

        // Create the Unitamisura with an existing ID
        unitamisura.setId(1L);
        UnitamisuraDTO unitamisuraDTO = unitamisuraMapper.toDto(unitamisura);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUnitamisuraMockMvc.perform(post("/api/unitamisuras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(unitamisuraDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Unitamisura in the database
        List<Unitamisura> unitamisuraList = unitamisuraRepository.findAll();
        assertThat(unitamisuraList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = unitamisuraRepository.findAll().size();
        // set the field null
        unitamisura.setNome(null);

        // Create the Unitamisura, which fails.
        UnitamisuraDTO unitamisuraDTO = unitamisuraMapper.toDto(unitamisura);

        restUnitamisuraMockMvc.perform(post("/api/unitamisuras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(unitamisuraDTO)))
            .andExpect(status().isBadRequest());

        List<Unitamisura> unitamisuraList = unitamisuraRepository.findAll();
        assertThat(unitamisuraList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUnitamisuras() throws Exception {
        // Initialize the database
        unitamisuraRepository.saveAndFlush(unitamisura);

        // Get all the unitamisuraList
        restUnitamisuraMockMvc.perform(get("/api/unitamisuras?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(unitamisura.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())));
    }
    

    @Test
    @Transactional
    public void getUnitamisura() throws Exception {
        // Initialize the database
        unitamisuraRepository.saveAndFlush(unitamisura);

        // Get the unitamisura
        restUnitamisuraMockMvc.perform(get("/api/unitamisuras/{id}", unitamisura.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(unitamisura.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingUnitamisura() throws Exception {
        // Get the unitamisura
        restUnitamisuraMockMvc.perform(get("/api/unitamisuras/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUnitamisura() throws Exception {
        // Initialize the database
        unitamisuraRepository.saveAndFlush(unitamisura);

        int databaseSizeBeforeUpdate = unitamisuraRepository.findAll().size();

        // Update the unitamisura
        Unitamisura updatedUnitamisura = unitamisuraRepository.findById(unitamisura.getId()).get();
        // Disconnect from session so that the updates on updatedUnitamisura are not directly saved in db
        em.detach(updatedUnitamisura);
        updatedUnitamisura
            .nome(UPDATED_NOME);
        UnitamisuraDTO unitamisuraDTO = unitamisuraMapper.toDto(updatedUnitamisura);

        restUnitamisuraMockMvc.perform(put("/api/unitamisuras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(unitamisuraDTO)))
            .andExpect(status().isOk());

        // Validate the Unitamisura in the database
        List<Unitamisura> unitamisuraList = unitamisuraRepository.findAll();
        assertThat(unitamisuraList).hasSize(databaseSizeBeforeUpdate);
        Unitamisura testUnitamisura = unitamisuraList.get(unitamisuraList.size() - 1);
        assertThat(testUnitamisura.getNome()).isEqualTo(UPDATED_NOME);
    }

    @Test
    @Transactional
    public void updateNonExistingUnitamisura() throws Exception {
        int databaseSizeBeforeUpdate = unitamisuraRepository.findAll().size();

        // Create the Unitamisura
        UnitamisuraDTO unitamisuraDTO = unitamisuraMapper.toDto(unitamisura);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restUnitamisuraMockMvc.perform(put("/api/unitamisuras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(unitamisuraDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Unitamisura in the database
        List<Unitamisura> unitamisuraList = unitamisuraRepository.findAll();
        assertThat(unitamisuraList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUnitamisura() throws Exception {
        // Initialize the database
        unitamisuraRepository.saveAndFlush(unitamisura);

        int databaseSizeBeforeDelete = unitamisuraRepository.findAll().size();

        // Get the unitamisura
        restUnitamisuraMockMvc.perform(delete("/api/unitamisuras/{id}", unitamisura.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Unitamisura> unitamisuraList = unitamisuraRepository.findAll();
        assertThat(unitamisuraList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Unitamisura.class);
        Unitamisura unitamisura1 = new Unitamisura();
        unitamisura1.setId(1L);
        Unitamisura unitamisura2 = new Unitamisura();
        unitamisura2.setId(unitamisura1.getId());
        assertThat(unitamisura1).isEqualTo(unitamisura2);
        unitamisura2.setId(2L);
        assertThat(unitamisura1).isNotEqualTo(unitamisura2);
        unitamisura1.setId(null);
        assertThat(unitamisura1).isNotEqualTo(unitamisura2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UnitamisuraDTO.class);
        UnitamisuraDTO unitamisuraDTO1 = new UnitamisuraDTO();
        unitamisuraDTO1.setId(1L);
        UnitamisuraDTO unitamisuraDTO2 = new UnitamisuraDTO();
        assertThat(unitamisuraDTO1).isNotEqualTo(unitamisuraDTO2);
        unitamisuraDTO2.setId(unitamisuraDTO1.getId());
        assertThat(unitamisuraDTO1).isEqualTo(unitamisuraDTO2);
        unitamisuraDTO2.setId(2L);
        assertThat(unitamisuraDTO1).isNotEqualTo(unitamisuraDTO2);
        unitamisuraDTO1.setId(null);
        assertThat(unitamisuraDTO1).isNotEqualTo(unitamisuraDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(unitamisuraMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(unitamisuraMapper.fromId(null)).isNull();
    }
}
