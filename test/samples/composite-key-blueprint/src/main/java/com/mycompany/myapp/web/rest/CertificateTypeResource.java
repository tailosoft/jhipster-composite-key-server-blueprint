package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.CertificateTypeService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.CertificateTypeDTO;
import com.mycompany.myapp.service.dto.CertificateTypeCriteria;
import com.mycompany.myapp.service.CertificateTypeQueryService;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.CertificateType}.
 */
@RestController
@RequestMapping("/api")
public class CertificateTypeResource {

    private final Logger log = LoggerFactory.getLogger(CertificateTypeResource.class);

    private static final String ENTITY_NAME = "certificateType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CertificateTypeService certificateTypeService;

    private final CertificateTypeQueryService certificateTypeQueryService;

    public CertificateTypeResource(CertificateTypeService certificateTypeService, CertificateTypeQueryService certificateTypeQueryService) {
        this.certificateTypeService = certificateTypeService;
        this.certificateTypeQueryService = certificateTypeQueryService;
    }

    /**
     * {@code POST  /certificate-types} : Create a new certificateType.
     *
     * @param certificateTypeDTO the certificateTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new certificateTypeDTO, or with status {@code 400 (Bad Request)} if the certificateType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/certificate-types")
    public ResponseEntity<CertificateTypeDTO> createCertificateType(@Valid @RequestBody CertificateTypeDTO certificateTypeDTO) throws URISyntaxException {
        log.debug("REST request to save CertificateType : {}", certificateTypeDTO);
        if (certificateTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new certificateType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CertificateTypeDTO result = certificateTypeService.save(certificateTypeDTO);
        return ResponseEntity.created(new URI("/api/certificate-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /certificate-types} : Updates an existing certificateType.
     *
     * @param certificateTypeDTO the certificateTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated certificateTypeDTO,
     * or with status {@code 400 (Bad Request)} if the certificateTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the certificateTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/certificate-types")
    public ResponseEntity<CertificateTypeDTO> updateCertificateType(@Valid @RequestBody CertificateTypeDTO certificateTypeDTO) throws URISyntaxException {
        log.debug("REST request to update CertificateType : {}", certificateTypeDTO);
        if (certificateTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CertificateTypeDTO result = certificateTypeService.save(certificateTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /certificate-types} : get all the certificateTypes.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of certificateTypes in body.
     */
    @GetMapping("/certificate-types")
    public ResponseEntity<List<CertificateTypeDTO>> getAllCertificateTypes(CertificateTypeCriteria criteria) {
        log.debug("REST request to get CertificateTypes by criteria: {}", criteria);
        List<CertificateTypeDTO> entityList = certificateTypeQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /certificate-types/count} : count all the certificateTypes.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/certificate-types/count")
    public ResponseEntity<Long> countCertificateTypes(CertificateTypeCriteria criteria) {
        log.debug("REST request to count CertificateTypes by criteria: {}", criteria);
        return ResponseEntity.ok().body(certificateTypeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /certificate-types/:id} : get the "id" certificateType.
     *
     * @param id the id of the certificateTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the certificateTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/certificate-types/{id}")
    public ResponseEntity<CertificateTypeDTO> getCertificateType(@PathVariable Long id) {
        log.debug("REST request to get CertificateType : {}", id);
        Optional<CertificateTypeDTO> certificateTypeDTO = certificateTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(certificateTypeDTO);
    }

    /**
     * {@code DELETE  /certificate-types/:id} : delete the "id" certificateType.
     *
     * @param id the id of the certificateTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/certificate-types/{id}")
    public ResponseEntity<Void> deleteCertificateType(@PathVariable Long id) {
        log.debug("REST request to delete CertificateType : {}", id);
        certificateTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
