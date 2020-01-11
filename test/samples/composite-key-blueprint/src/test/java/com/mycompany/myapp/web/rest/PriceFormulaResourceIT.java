package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.CompositekeyApp;
import com.mycompany.myapp.domain.PriceFormula;
import com.mycompany.myapp.repository.PriceFormulaRepository;
import com.mycompany.myapp.service.PriceFormulaService;
import com.mycompany.myapp.service.dto.PriceFormulaDTO;
import com.mycompany.myapp.service.mapper.PriceFormulaMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;
import com.mycompany.myapp.service.dto.PriceFormulaCriteria;
import com.mycompany.myapp.service.PriceFormulaQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link PriceFormulaResource} REST controller.
 */
@SpringBootTest(classes = CompositekeyApp.class)
public class PriceFormulaResourceIT {

    public static final Integer DEFAULT_MAX = 1;
    public static final Integer UPDATED_MAX = 2;
    public static final Integer SMALLER_MAX = 1 - 1;

    public static final String DEFAULT_FORMULA = "AAAAAAAAAA";
    public static final String UPDATED_FORMULA = "BBBBBBBBBB";

    @Autowired
    private PriceFormulaRepository priceFormulaRepository;

    @Autowired
    private PriceFormulaMapper priceFormulaMapper;

    @Autowired
    private PriceFormulaService priceFormulaService;

    @Autowired
    private PriceFormulaQueryService priceFormulaQueryService;

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

    private MockMvc restPriceFormulaMockMvc;

    private PriceFormula priceFormula;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PriceFormulaResource priceFormulaResource = new PriceFormulaResource(priceFormulaService, priceFormulaQueryService);
        this.restPriceFormulaMockMvc = MockMvcBuilders.standaloneSetup(priceFormulaResource)
            .setRemoveSemicolonContent(false)
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
    public static PriceFormula createEntity(EntityManager em) {
        PriceFormula priceFormula = new PriceFormula()
            .max(DEFAULT_MAX)
            .formula(DEFAULT_FORMULA);
        return priceFormula;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PriceFormula createUpdatedEntity(EntityManager em) {
        PriceFormula priceFormula = new PriceFormula()
            .max(UPDATED_MAX)
            .formula(UPDATED_FORMULA);
        return priceFormula;
    }

    @BeforeEach
    public void initTest() {
        priceFormula = createEntity(em);
    }

    @Test
    @Transactional
    public void createPriceFormula() throws Exception {
        int databaseSizeBeforeCreate = priceFormulaRepository.findAll().size();

        // Create the PriceFormula
        PriceFormulaDTO priceFormulaDTO = priceFormulaMapper.toDto(priceFormula);
        restPriceFormulaMockMvc.perform(post("/api/price-formulas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(priceFormulaDTO)))
            .andExpect(status().isCreated());

        // Validate the PriceFormula in the database
        List<PriceFormula> priceFormulaList = priceFormulaRepository.findAll();
        assertThat(priceFormulaList).hasSize(databaseSizeBeforeCreate + 1);
        PriceFormula testPriceFormula = priceFormulaList.get(priceFormulaList.size() - 1);
        assertThat(testPriceFormula.getMax()).isEqualTo(DEFAULT_MAX);
        assertThat(testPriceFormula.getFormula()).isEqualTo(DEFAULT_FORMULA);
    }

    @Test
    @Transactional
    public void createPriceFormulaWithExistingId() throws Exception {
        priceFormulaRepository.save(priceFormula);
        int databaseSizeBeforeCreate = priceFormulaRepository.findAll().size();

        // Create the PriceFormula with an existing ID
        priceFormula.setMax(priceFormula.getMax());
        PriceFormulaDTO priceFormulaDTO = priceFormulaMapper.toDto(priceFormula);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPriceFormulaMockMvc.perform(post("/api/price-formulas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(priceFormulaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PriceFormula in the database
        List<PriceFormula> priceFormulaList = priceFormulaRepository.findAll();
        assertThat(priceFormulaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkMaxIsRequired() throws Exception {
        int databaseSizeBeforeTest = priceFormulaRepository.findAll().size();
        // set the field null
        priceFormula.setMax(null);

        // Create the PriceFormula, which fails.
        PriceFormulaDTO priceFormulaDTO = priceFormulaMapper.toDto(priceFormula);

        restPriceFormulaMockMvc.perform(post("/api/price-formulas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(priceFormulaDTO)))
            .andExpect(status().isBadRequest());

        List<PriceFormula> priceFormulaList = priceFormulaRepository.findAll();
        assertThat(priceFormulaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFormulaIsRequired() throws Exception {
        int databaseSizeBeforeTest = priceFormulaRepository.findAll().size();
        // set the field null
        priceFormula.setFormula(null);

        // Create the PriceFormula, which fails.
        PriceFormulaDTO priceFormulaDTO = priceFormulaMapper.toDto(priceFormula);

        restPriceFormulaMockMvc.perform(post("/api/price-formulas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(priceFormulaDTO)))
            .andExpect(status().isBadRequest());

        List<PriceFormula> priceFormulaList = priceFormulaRepository.findAll();
        assertThat(priceFormulaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPriceFormulas() throws Exception {
        // Initialize the database
        priceFormulaRepository.saveAndFlush(priceFormula);

        // Get all the priceFormulaList
        restPriceFormulaMockMvc.perform(get("/api/price-formulas"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].max").value(hasItem(DEFAULT_MAX)))
            .andExpect(jsonPath("$.[*].formula").value(hasItem(DEFAULT_FORMULA)));
    }

    @Test
    @Transactional
    public void getPriceFormula() throws Exception {
        // Initialize the database
        priceFormulaRepository.saveAndFlush(priceFormula);

        // Get the priceFormula
        restPriceFormulaMockMvc.perform(get("/api/price-formulas/{max}", priceFormula.getMax()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.max").value(DEFAULT_MAX))
            .andExpect(jsonPath("$.formula").value(DEFAULT_FORMULA));
    }

    @Test
    @Transactional
    public void getAllPriceFormulasByMaxIsEqualToSomething() throws Exception {
        // Initialize the database
        priceFormulaRepository.saveAndFlush(priceFormula);

        // Get all the priceFormulaList where max equals to DEFAULT_MAX
        defaultPriceFormulaShouldBeFound("max.equals=" + DEFAULT_MAX);

        // Get all the priceFormulaList where max equals to UPDATED_MAX
        defaultPriceFormulaShouldNotBeFound("max.equals=" + UPDATED_MAX);
    }

    @Test
    @Transactional
    public void getAllPriceFormulasByMaxIsNotEqualToSomething() throws Exception {
        // Initialize the database
        priceFormulaRepository.saveAndFlush(priceFormula);

        // Get all the priceFormulaList where max not equals to DEFAULT_MAX
        defaultPriceFormulaShouldNotBeFound("max.notEquals=" + DEFAULT_MAX);

        // Get all the priceFormulaList where max not equals to UPDATED_MAX
        defaultPriceFormulaShouldBeFound("max.notEquals=" + UPDATED_MAX);
    }

    @Test
    @Transactional
    public void getAllPriceFormulasByMaxIsInShouldWork() throws Exception {
        // Initialize the database
        priceFormulaRepository.saveAndFlush(priceFormula);

        // Get all the priceFormulaList where max in DEFAULT_MAX or UPDATED_MAX
        defaultPriceFormulaShouldBeFound("max.in=" + DEFAULT_MAX + "," + UPDATED_MAX);

        // Get all the priceFormulaList where max equals to UPDATED_MAX
        defaultPriceFormulaShouldNotBeFound("max.in=" + UPDATED_MAX);
    }

    @Test
    @Transactional
    public void getAllPriceFormulasByMaxIsNullOrNotNull() throws Exception {
        // Initialize the database
        priceFormulaRepository.saveAndFlush(priceFormula);

        // Get all the priceFormulaList where max is not null
        defaultPriceFormulaShouldBeFound("max.specified=true");

        // Get all the priceFormulaList where max is null
        defaultPriceFormulaShouldNotBeFound("max.specified=false");
    }

    @Test
    @Transactional
    public void getAllPriceFormulasByMaxIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        priceFormulaRepository.saveAndFlush(priceFormula);

        // Get all the priceFormulaList where max is greater than or equal to DEFAULT_MAX
        defaultPriceFormulaShouldBeFound("max.greaterThanOrEqual=" + DEFAULT_MAX);

        // Get all the priceFormulaList where max is greater than or equal to UPDATED_MAX
        defaultPriceFormulaShouldNotBeFound("max.greaterThanOrEqual=" + UPDATED_MAX);
    }

    @Test
    @Transactional
    public void getAllPriceFormulasByMaxIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        priceFormulaRepository.saveAndFlush(priceFormula);

        // Get all the priceFormulaList where max is less than or equal to DEFAULT_MAX
        defaultPriceFormulaShouldBeFound("max.lessThanOrEqual=" + DEFAULT_MAX);

        // Get all the priceFormulaList where max is less than or equal to SMALLER_MAX
        defaultPriceFormulaShouldNotBeFound("max.lessThanOrEqual=" + SMALLER_MAX);
    }

    @Test
    @Transactional
    public void getAllPriceFormulasByMaxIsLessThanSomething() throws Exception {
        // Initialize the database
        priceFormulaRepository.saveAndFlush(priceFormula);

        // Get all the priceFormulaList where max is less than DEFAULT_MAX
        defaultPriceFormulaShouldNotBeFound("max.lessThan=" + DEFAULT_MAX);

        // Get all the priceFormulaList where max is less than UPDATED_MAX
        defaultPriceFormulaShouldBeFound("max.lessThan=" + UPDATED_MAX);
    }

    @Test
    @Transactional
    public void getAllPriceFormulasByMaxIsGreaterThanSomething() throws Exception {
        // Initialize the database
        priceFormulaRepository.saveAndFlush(priceFormula);

        // Get all the priceFormulaList where max is greater than DEFAULT_MAX
        defaultPriceFormulaShouldNotBeFound("max.greaterThan=" + DEFAULT_MAX);

        // Get all the priceFormulaList where max is greater than SMALLER_MAX
        defaultPriceFormulaShouldBeFound("max.greaterThan=" + SMALLER_MAX);
    }

    @Test
    @Transactional
    public void getAllPriceFormulasByFormulaIsEqualToSomething() throws Exception {
        // Initialize the database
        priceFormulaRepository.saveAndFlush(priceFormula);

        // Get all the priceFormulaList where formula equals to DEFAULT_FORMULA
        defaultPriceFormulaShouldBeFound("formula.equals=" + DEFAULT_FORMULA);

        // Get all the priceFormulaList where formula equals to UPDATED_FORMULA
        defaultPriceFormulaShouldNotBeFound("formula.equals=" + UPDATED_FORMULA);
    }

    @Test
    @Transactional
    public void getAllPriceFormulasByFormulaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        priceFormulaRepository.saveAndFlush(priceFormula);

        // Get all the priceFormulaList where formula not equals to DEFAULT_FORMULA
        defaultPriceFormulaShouldNotBeFound("formula.notEquals=" + DEFAULT_FORMULA);

        // Get all the priceFormulaList where formula not equals to UPDATED_FORMULA
        defaultPriceFormulaShouldBeFound("formula.notEquals=" + UPDATED_FORMULA);
    }

    @Test
    @Transactional
    public void getAllPriceFormulasByFormulaIsInShouldWork() throws Exception {
        // Initialize the database
        priceFormulaRepository.saveAndFlush(priceFormula);

        // Get all the priceFormulaList where formula in DEFAULT_FORMULA or UPDATED_FORMULA
        defaultPriceFormulaShouldBeFound("formula.in=" + DEFAULT_FORMULA + "," + UPDATED_FORMULA);

        // Get all the priceFormulaList where formula equals to UPDATED_FORMULA
        defaultPriceFormulaShouldNotBeFound("formula.in=" + UPDATED_FORMULA);
    }

    @Test
    @Transactional
    public void getAllPriceFormulasByFormulaIsNullOrNotNull() throws Exception {
        // Initialize the database
        priceFormulaRepository.saveAndFlush(priceFormula);

        // Get all the priceFormulaList where formula is not null
        defaultPriceFormulaShouldBeFound("formula.specified=true");

        // Get all the priceFormulaList where formula is null
        defaultPriceFormulaShouldNotBeFound("formula.specified=false");
    }

    @Test
    @Transactional
    public void getAllPriceFormulasByFormulaContainsSomething() throws Exception {
        // Initialize the database
        priceFormulaRepository.saveAndFlush(priceFormula);

        // Get all the priceFormulaList where formula contains DEFAULT_FORMULA
        defaultPriceFormulaShouldBeFound("formula.contains=" + DEFAULT_FORMULA);

        // Get all the priceFormulaList where formula contains UPDATED_FORMULA
        defaultPriceFormulaShouldNotBeFound("formula.contains=" + UPDATED_FORMULA);
    }

    @Test
    @Transactional
    public void getAllPriceFormulasByFormulaNotContainsSomething() throws Exception {
        // Initialize the database
        priceFormulaRepository.saveAndFlush(priceFormula);

        // Get all the priceFormulaList where formula does not contain DEFAULT_FORMULA
        defaultPriceFormulaShouldNotBeFound("formula.doesNotContain=" + DEFAULT_FORMULA);

        // Get all the priceFormulaList where formula does not contain UPDATED_FORMULA
        defaultPriceFormulaShouldBeFound("formula.doesNotContain=" + UPDATED_FORMULA);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultPriceFormulaShouldBeFound(String filter) throws Exception {
        restPriceFormulaMockMvc.perform(get("/api/price-formulas?" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].max").value(hasItem(DEFAULT_MAX)))
            .andExpect(jsonPath("$.[*].formula").value(hasItem(DEFAULT_FORMULA)));

        // Check, that the count call also returns 1
        restPriceFormulaMockMvc.perform(get("/api/price-formulas/count?" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultPriceFormulaShouldNotBeFound(String filter) throws Exception {
        restPriceFormulaMockMvc.perform(get("/api/price-formulas?" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restPriceFormulaMockMvc.perform(get("/api/price-formulas/count?" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingPriceFormula() throws Exception {
        // Get the priceFormula
        PriceFormula priceFormula = createUpdatedEntity(em);
        restPriceFormulaMockMvc.perform(get("/api/price-formulas/{id}", priceFormula.getMax()))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePriceFormula() throws Exception {
        // Initialize the database
        priceFormulaRepository.saveAndFlush(priceFormula);

        int databaseSizeBeforeUpdate = priceFormulaRepository.findAll().size();

        // Update the priceFormula
        PriceFormula updatedPriceFormula = priceFormulaRepository.findById(priceFormula.getMax()).get();
        // Disconnect from session so that the updates on updatedPriceFormula are not directly saved in db
        em.detach(updatedPriceFormula);
        updatedPriceFormula
            .formula(UPDATED_FORMULA);
        PriceFormulaDTO priceFormulaDTO = priceFormulaMapper.toDto(updatedPriceFormula);

        restPriceFormulaMockMvc.perform(put("/api/price-formulas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(priceFormulaDTO)))
            .andExpect(status().isOk());

        // Validate the PriceFormula in the database
        List<PriceFormula> priceFormulaList = priceFormulaRepository.findAll();
        assertThat(priceFormulaList).hasSize(databaseSizeBeforeUpdate);
        PriceFormula testPriceFormula = priceFormulaList.get(priceFormulaList.size() - 1);
        assertThat(testPriceFormula.getFormula()).isEqualTo(UPDATED_FORMULA);
    }

    @Test
    @Transactional
    public void updateNonExistingPriceFormula() throws Exception {
        int databaseSizeBeforeUpdate = priceFormulaRepository.findAll().size();

        // Create the PriceFormula
        PriceFormulaDTO priceFormulaDTO = priceFormulaMapper.toDto(priceFormula);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPriceFormulaMockMvc.perform(put("/api/price-formulas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(priceFormulaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PriceFormula in the database
        List<PriceFormula> priceFormulaList = priceFormulaRepository.findAll();
        assertThat(priceFormulaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePriceFormula() throws Exception {
        // Initialize the database
        priceFormulaRepository.saveAndFlush(priceFormula);

        int databaseSizeBeforeDelete = priceFormulaRepository.findAll().size();

        // Delete the priceFormula
        restPriceFormulaMockMvc.perform(delete("/api/price-formulas/{max}", priceFormula.getMax())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PriceFormula> priceFormulaList = priceFormulaRepository.findAll();
        assertThat(priceFormulaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
