package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.PriceFormulaService;
import com.mycompany.myapp.domain.PriceFormula;
import com.mycompany.myapp.repository.PriceFormulaRepository;
import com.mycompany.myapp.service.dto.PriceFormulaDTO;
import com.mycompany.myapp.service.mapper.PriceFormulaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link PriceFormula}.
 */
@Service
@Transactional
public class PriceFormulaServiceImpl implements PriceFormulaService {

    private final Logger log = LoggerFactory.getLogger(PriceFormulaServiceImpl.class);

    private final PriceFormulaRepository priceFormulaRepository;

    private final PriceFormulaMapper priceFormulaMapper;

    public PriceFormulaServiceImpl(PriceFormulaRepository priceFormulaRepository, PriceFormulaMapper priceFormulaMapper) {
        this.priceFormulaRepository = priceFormulaRepository;
        this.priceFormulaMapper = priceFormulaMapper;
    }

    /**
     * Save a priceFormula.
     *
     * @param priceFormulaDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public PriceFormulaDTO save(PriceFormulaDTO priceFormulaDTO) {
        log.debug("Request to save PriceFormula : {}", priceFormulaDTO);
        PriceFormula priceFormula = priceFormulaMapper.toEntity(priceFormulaDTO);
        priceFormula = priceFormulaRepository.save(priceFormula);
        return priceFormulaMapper.toDto(priceFormula);
    }

    /**
     * Get all the priceFormulas.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<PriceFormulaDTO> findAll() {
        log.debug("Request to get all PriceFormulas");
        return priceFormulaRepository.findAll().stream()
            .map(priceFormulaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one priceFormula by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PriceFormulaDTO> findOne(Integer id) {
        log.debug("Request to get PriceFormula : {}", id);
        return priceFormulaRepository.findById(id)
            .map(priceFormulaMapper::toDto);
    }

    /**
     * Delete the priceFormula by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Integer id) {
        log.debug("Request to delete PriceFormula : {}", id);
        priceFormulaRepository.deleteById(id);
    }
}
