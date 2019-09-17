package com.mycompany.myapp.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.mycompany.myapp.domain.PriceFormula;
import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.repository.PriceFormulaRepository;
import com.mycompany.myapp.service.dto.PriceFormulaCriteria;
import com.mycompany.myapp.service.dto.PriceFormulaDTO;
import com.mycompany.myapp.service.mapper.PriceFormulaMapper;

/**
 * Service for executing complex queries for {@link PriceFormula} entities in the database.
 * The main input is a {@link PriceFormulaCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PriceFormulaDTO} or a {@link Page} of {@link PriceFormulaDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PriceFormulaQueryService extends QueryService<PriceFormula> {

    private final Logger log = LoggerFactory.getLogger(PriceFormulaQueryService.class);

    private final PriceFormulaRepository priceFormulaRepository;

    private final PriceFormulaMapper priceFormulaMapper;

    public PriceFormulaQueryService(PriceFormulaRepository priceFormulaRepository, PriceFormulaMapper priceFormulaMapper) {
        this.priceFormulaRepository = priceFormulaRepository;
        this.priceFormulaMapper = priceFormulaMapper;
    }

    /**
     * Return a {@link List} of {@link PriceFormulaDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PriceFormulaDTO> findByCriteria(PriceFormulaCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<PriceFormula> specification = createSpecification(criteria);
        return priceFormulaMapper.toDto(priceFormulaRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PriceFormulaDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PriceFormulaDTO> findByCriteria(PriceFormulaCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<PriceFormula> specification = createSpecification(criteria);
        return priceFormulaRepository.findAll(specification, page)
            .map(priceFormulaMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PriceFormulaCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<PriceFormula> specification = createSpecification(criteria);
        return priceFormulaRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    private Specification<PriceFormula> createSpecification(PriceFormulaCriteria criteria) {
        Specification<PriceFormula> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getMax() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMax(), PriceFormula_.max));
            }
            if (criteria.getFormula() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFormula(), PriceFormula_.formula));
            }
        }
        return specification;
    }
}
