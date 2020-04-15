package it.contrader.graphreport.web.rest;

import it.contrader.graphreport.GraphApp;

import it.contrader.graphreport.domain.Graph;
import it.contrader.graphreport.repository.GraphRepository;
import it.contrader.graphreport.service.GraphService;
import it.contrader.graphreport.service.dto.GraphDTO;
import it.contrader.graphreport.service.mapper.GraphMapper;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


import static it.contrader.graphreport.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import it.contrader.graphreport.domain.enumeration.TipoGrafico;
import it.contrader.graphreport.domain.enumeration.FontStyle;
/**
 * Test class for the GraphResource REST controller.
 *
 * @see GraphResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GraphApp.class)
public class GraphResourceIntTest {

    private static final Long DEFAULT_UTENTE = 1L;
    private static final Long UPDATED_UTENTE = 2L;

    private static final TipoGrafico DEFAULT_TIPO_GRAFICO = TipoGrafico.LINE;
    private static final TipoGrafico UPDATED_TIPO_GRAFICO = TipoGrafico.BAR;

    private static final Boolean DEFAULT_TITOLO_BOOL = false;
    private static final Boolean UPDATED_TITOLO_BOOL = true;

    private static final String DEFAULT_TITOLO = "AAAAAAAAAA";
    private static final String UPDATED_TITOLO = "BBBBBBBBBB";

    private static final FontStyle DEFAULT_FONT_STYLE = FontStyle.COURIER;
    private static final FontStyle UPDATED_FONT_STYLE = FontStyle.HELVETICANEUE;

    private static final Integer DEFAULT_FONT_SIZE = 1;
    private static final Integer UPDATED_FONT_SIZE = 2;

    private static final String DEFAULT_POS_TITOLO = "AAAAAAAAAA";
    private static final String UPDATED_POS_TITOLO = "BBBBBBBBBB";

    private static final Boolean DEFAULT_LEGENDA = false;
    private static final Boolean UPDATED_LEGENDA = true;

    private static final String DEFAULT_POS_LEGENDA = "AAAAAAAAAA";
    private static final String UPDATED_POS_LEGENDA = "BBBBBBBBBB";

    private static final Boolean DEFAULT_PARETO = false;
    private static final Boolean UPDATED_PARETO = true;

    private static final LocalDate DEFAULT_CREATED = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_MODIFY = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_MODIFY = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private GraphRepository graphRepository;

    @Autowired
    private GraphMapper graphMapper;

    @Autowired
    private GraphService graphService;

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

    private MockMvc restGraphMockMvc;

    private Graph graph;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GraphResource graphResource = new GraphResource(graphService);
        this.restGraphMockMvc = MockMvcBuilders.standaloneSetup(graphResource)
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
    public static Graph createEntity(EntityManager em) {
        Graph graph = new Graph()
            .utente(DEFAULT_UTENTE)
            .tipoGrafico(DEFAULT_TIPO_GRAFICO)
            .titoloBool(DEFAULT_TITOLO_BOOL)
            .titolo(DEFAULT_TITOLO)
            .fontStyle(DEFAULT_FONT_STYLE)
            .fontSize(DEFAULT_FONT_SIZE)
            .posTitolo(DEFAULT_POS_TITOLO)
            .legenda(DEFAULT_LEGENDA)
            .posLegenda(DEFAULT_POS_LEGENDA)
            .pareto(DEFAULT_PARETO)
            .created(DEFAULT_CREATED)
            .modify(DEFAULT_MODIFY);
        return graph;
    }

    @Before
    public void initTest() {
        graph = createEntity(em);
    }

    @Test
    @Transactional
    public void createGraph() throws Exception {
        int databaseSizeBeforeCreate = graphRepository.findAll().size();

        // Create the Graph
        GraphDTO graphDTO = graphMapper.toDto(graph);
        restGraphMockMvc.perform(post("/api/graphs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(graphDTO)))
            .andExpect(status().isCreated());

        // Validate the Graph in the database
        List<Graph> graphList = graphRepository.findAll();
        assertThat(graphList).hasSize(databaseSizeBeforeCreate + 1);
        Graph testGraph = graphList.get(graphList.size() - 1);
        assertThat(testGraph.getUtente()).isEqualTo(DEFAULT_UTENTE);
        assertThat(testGraph.getTipoGrafico()).isEqualTo(DEFAULT_TIPO_GRAFICO);
        assertThat(testGraph.isTitoloBool()).isEqualTo(DEFAULT_TITOLO_BOOL);
        assertThat(testGraph.getTitolo()).isEqualTo(DEFAULT_TITOLO);
        assertThat(testGraph.getFontStyle()).isEqualTo(DEFAULT_FONT_STYLE);
        assertThat(testGraph.getFontSize()).isEqualTo(DEFAULT_FONT_SIZE);
        assertThat(testGraph.getPosTitolo()).isEqualTo(DEFAULT_POS_TITOLO);
        assertThat(testGraph.isLegenda()).isEqualTo(DEFAULT_LEGENDA);
        assertThat(testGraph.getPosLegenda()).isEqualTo(DEFAULT_POS_LEGENDA);
        assertThat(testGraph.isPareto()).isEqualTo(DEFAULT_PARETO);
        assertThat(testGraph.getCreated()).isEqualTo(DEFAULT_CREATED);
        assertThat(testGraph.getModify()).isEqualTo(DEFAULT_MODIFY);
    }

    @Test
    @Transactional
    public void createGraphWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = graphRepository.findAll().size();

        // Create the Graph with an existing ID
        graph.setId(1L);
        GraphDTO graphDTO = graphMapper.toDto(graph);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGraphMockMvc.perform(post("/api/graphs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(graphDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Graph in the database
        List<Graph> graphList = graphRepository.findAll();
        assertThat(graphList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllGraphs() throws Exception {
        // Initialize the database
        graphRepository.saveAndFlush(graph);

        // Get all the graphList
        restGraphMockMvc.perform(get("/api/graphs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(graph.getId().intValue())))
            .andExpect(jsonPath("$.[*].utente").value(hasItem(DEFAULT_UTENTE.intValue())))
            .andExpect(jsonPath("$.[*].tipoGrafico").value(hasItem(DEFAULT_TIPO_GRAFICO.toString())))
            .andExpect(jsonPath("$.[*].titoloBool").value(hasItem(DEFAULT_TITOLO_BOOL.booleanValue())))
            .andExpect(jsonPath("$.[*].titolo").value(hasItem(DEFAULT_TITOLO.toString())))
            .andExpect(jsonPath("$.[*].fontStyle").value(hasItem(DEFAULT_FONT_STYLE.toString())))
            .andExpect(jsonPath("$.[*].fontSize").value(hasItem(DEFAULT_FONT_SIZE)))
            .andExpect(jsonPath("$.[*].posTitolo").value(hasItem(DEFAULT_POS_TITOLO.toString())))
            .andExpect(jsonPath("$.[*].legenda").value(hasItem(DEFAULT_LEGENDA.booleanValue())))
            .andExpect(jsonPath("$.[*].posLegenda").value(hasItem(DEFAULT_POS_LEGENDA.toString())))
            .andExpect(jsonPath("$.[*].pareto").value(hasItem(DEFAULT_PARETO.booleanValue())))
            .andExpect(jsonPath("$.[*].created").value(hasItem(DEFAULT_CREATED.toString())))
            .andExpect(jsonPath("$.[*].modify").value(hasItem(DEFAULT_MODIFY.toString())));
    }
    
    @Test
    @Transactional
    public void getGraph() throws Exception {
        // Initialize the database
        graphRepository.saveAndFlush(graph);

        // Get the graph
        restGraphMockMvc.perform(get("/api/graphs/{id}", graph.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(graph.getId().intValue()))
            .andExpect(jsonPath("$.utente").value(DEFAULT_UTENTE.intValue()))
            .andExpect(jsonPath("$.tipoGrafico").value(DEFAULT_TIPO_GRAFICO.toString()))
            .andExpect(jsonPath("$.titoloBool").value(DEFAULT_TITOLO_BOOL.booleanValue()))
            .andExpect(jsonPath("$.titolo").value(DEFAULT_TITOLO.toString()))
            .andExpect(jsonPath("$.fontStyle").value(DEFAULT_FONT_STYLE.toString()))
            .andExpect(jsonPath("$.fontSize").value(DEFAULT_FONT_SIZE))
            .andExpect(jsonPath("$.posTitolo").value(DEFAULT_POS_TITOLO.toString()))
            .andExpect(jsonPath("$.legenda").value(DEFAULT_LEGENDA.booleanValue()))
            .andExpect(jsonPath("$.posLegenda").value(DEFAULT_POS_LEGENDA.toString()))
            .andExpect(jsonPath("$.pareto").value(DEFAULT_PARETO.booleanValue()))
            .andExpect(jsonPath("$.created").value(DEFAULT_CREATED.toString()))
            .andExpect(jsonPath("$.modify").value(DEFAULT_MODIFY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingGraph() throws Exception {
        // Get the graph
        restGraphMockMvc.perform(get("/api/graphs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGraph() throws Exception {
        // Initialize the database
        graphRepository.saveAndFlush(graph);

        int databaseSizeBeforeUpdate = graphRepository.findAll().size();

        // Update the graph
        Graph updatedGraph = graphRepository.findById(graph.getId()).get();
        // Disconnect from session so that the updates on updatedGraph are not directly saved in db
        em.detach(updatedGraph);
        updatedGraph
            .utente(UPDATED_UTENTE)
            .tipoGrafico(UPDATED_TIPO_GRAFICO)
            .titoloBool(UPDATED_TITOLO_BOOL)
            .titolo(UPDATED_TITOLO)
            .fontStyle(UPDATED_FONT_STYLE)
            .fontSize(UPDATED_FONT_SIZE)
            .posTitolo(UPDATED_POS_TITOLO)
            .legenda(UPDATED_LEGENDA)
            .posLegenda(UPDATED_POS_LEGENDA)
            .pareto(UPDATED_PARETO)
            .created(UPDATED_CREATED)
            .modify(UPDATED_MODIFY);
        GraphDTO graphDTO = graphMapper.toDto(updatedGraph);

        restGraphMockMvc.perform(put("/api/graphs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(graphDTO)))
            .andExpect(status().isOk());

        // Validate the Graph in the database
        List<Graph> graphList = graphRepository.findAll();
        assertThat(graphList).hasSize(databaseSizeBeforeUpdate);
        Graph testGraph = graphList.get(graphList.size() - 1);
        assertThat(testGraph.getUtente()).isEqualTo(UPDATED_UTENTE);
        assertThat(testGraph.getTipoGrafico()).isEqualTo(UPDATED_TIPO_GRAFICO);
        assertThat(testGraph.isTitoloBool()).isEqualTo(UPDATED_TITOLO_BOOL);
        assertThat(testGraph.getTitolo()).isEqualTo(UPDATED_TITOLO);
        assertThat(testGraph.getFontStyle()).isEqualTo(UPDATED_FONT_STYLE);
        assertThat(testGraph.getFontSize()).isEqualTo(UPDATED_FONT_SIZE);
        assertThat(testGraph.getPosTitolo()).isEqualTo(UPDATED_POS_TITOLO);
        assertThat(testGraph.isLegenda()).isEqualTo(UPDATED_LEGENDA);
        assertThat(testGraph.getPosLegenda()).isEqualTo(UPDATED_POS_LEGENDA);
        assertThat(testGraph.isPareto()).isEqualTo(UPDATED_PARETO);
        assertThat(testGraph.getCreated()).isEqualTo(UPDATED_CREATED);
        assertThat(testGraph.getModify()).isEqualTo(UPDATED_MODIFY);
    }

    @Test
    @Transactional
    public void updateNonExistingGraph() throws Exception {
        int databaseSizeBeforeUpdate = graphRepository.findAll().size();

        // Create the Graph
        GraphDTO graphDTO = graphMapper.toDto(graph);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGraphMockMvc.perform(put("/api/graphs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(graphDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Graph in the database
        List<Graph> graphList = graphRepository.findAll();
        assertThat(graphList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGraph() throws Exception {
        // Initialize the database
        graphRepository.saveAndFlush(graph);

        int databaseSizeBeforeDelete = graphRepository.findAll().size();

        // Delete the graph
        restGraphMockMvc.perform(delete("/api/graphs/{id}", graph.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Graph> graphList = graphRepository.findAll();
        assertThat(graphList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Graph.class);
        Graph graph1 = new Graph();
        graph1.setId(1L);
        Graph graph2 = new Graph();
        graph2.setId(graph1.getId());
        assertThat(graph1).isEqualTo(graph2);
        graph2.setId(2L);
        assertThat(graph1).isNotEqualTo(graph2);
        graph1.setId(null);
        assertThat(graph1).isNotEqualTo(graph2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GraphDTO.class);
        GraphDTO graphDTO1 = new GraphDTO();
        graphDTO1.setId(1L);
        GraphDTO graphDTO2 = new GraphDTO();
        assertThat(graphDTO1).isNotEqualTo(graphDTO2);
        graphDTO2.setId(graphDTO1.getId());
        assertThat(graphDTO1).isEqualTo(graphDTO2);
        graphDTO2.setId(2L);
        assertThat(graphDTO1).isNotEqualTo(graphDTO2);
        graphDTO1.setId(null);
        assertThat(graphDTO1).isNotEqualTo(graphDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(graphMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(graphMapper.fromId(null)).isNull();
    }
}
