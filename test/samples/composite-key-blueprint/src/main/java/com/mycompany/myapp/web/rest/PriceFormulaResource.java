package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.PriceFormulaService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.PriceFormulaDTO;
import com.mycompany.myapp.service.dto.PriceFormulaCriteria;
import com.mycompany.myapp.service.PriceFormulaQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.PriceFormula}.
 */
@RestController
@RequestMapping("/api")
public class PriceFormulaResource {

    private final Logger log = LoggerFactory.getLogger(PriceFormulaResource.class);

    private static final String ENTITY_NAME = "priceFormula";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PriceFormulaService priceFormulaService;

    private final PriceFormulaQueryService priceFormulaQueryService;

    public PriceFormulaResource(PriceFormulaService priceFormulaService, PriceFormulaQueryService priceFormulaQueryService) {
        this.priceFormulaService = priceFormulaService;
        this.priceFormulaQueryService = priceFormulaQueryService;
    }

    /**
     * {@code POST  /price-formulas} : Create a new priceFormula.
     *
     * @param priceFormulaDTO the priceFormulaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new priceFormulaDTO, or with status {@code 400 (Bad Request)} if the priceFormula has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/price-formulas")
    public ResponseEntity<PriceFormulaDTO> createPriceFormula(@Valid @RequestBody PriceFormulaDTO priceFormulaDTO) throws URISyntaxException {
        log.debug("REST request to save PriceFormula : {}", priceFormulaDTO);
        if (priceFormulaService.findOne(priceFormulaDTO.getMax()).isPresent()) {
            throw new BadRequestAlertException("This priceFormula already exists", ENTITY_NAME, "idexists");
        }
        PriceFormulaDTO result = priceFormulaService.save(priceFormulaDTO);
        return ResponseEntity.created(new URI("/api/price-formulas/" + result.getMax()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getMax().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /price-formulas} : Updates an existing priceFormula.
     *
     * @param priceFormulaDTO the priceFormulaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated priceFormulaDTO,
     * or with status {@code 400 (Bad Request)} if the priceFormulaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the priceFormulaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/price-formulas")
    public ResponseEntity<PriceFormulaDTO> updatePriceFormula(@Valid @RequestBody PriceFormulaDTO priceFormulaDTO) throws URISyntaxException {
        log.debug("REST request to update PriceFormula : {}", priceFormulaDTO);
        if (!priceFormulaService.findOne(priceFormulaDTO.getMax()).isPresent()) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PriceFormulaDTO result = priceFormulaService.save(priceFormulaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getMax().toString()))
            .body(result);
    }

    /**
     * {@code GET  /price-formulas} : get all the priceFormulas.
     *

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of priceFormulas in body.
     */
    @GetMapping("/price-formulas")
    public ResponseEntity<List<PriceFormulaDTO>> getAllPriceFormulas(PriceFormulaCriteria criteria) {
        log.debug("REST request to get PriceFormulas by criteria: {}", criteria);
        List<PriceFormulaDTO> entityList = priceFormulaQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /price-formulas/count} : count all the priceFormulas.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/price-formulas/count")
    public ResponseEntity<Long> countPriceFormulas(PriceFormulaCriteria criteria) {
        log.debug("REST request to count PriceFormulas by criteria: {}", criteria);
        return ResponseEntity.ok().body(priceFormulaQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /price-formulas/:id} : get the "id" priceFormula.
     *
     * @param id the id of the priceFormulaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the priceFormulaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/price-formulas/{id}")
    public ResponseEntity<PriceFormulaDTO> getPriceFormula(@PathVariable Integer id) {
        log.debug("REST request to get PriceFormula : {}", id);
        Optional<PriceFormulaDTO> priceFormulaDTO = priceFormulaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(priceFormulaDTO);
    }

    /**
     * {@code DELETE  /price-formulas/:id} : delete the "id" priceFormula.
     *
     * @param id the id of the priceFormulaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/price-formulas/{id}")
    public ResponseEntity<Void> deletePriceFormula(@PathVariable Integer id) {
        log.debug("REST request to delete PriceFormula : {}", id);
        priceFormulaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
