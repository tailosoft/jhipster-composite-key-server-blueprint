package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.EmployeeSkillService;
import com.mycompany.myapp.domain.EmployeeSkill;
import com.mycompany.myapp.repository.EmployeeSkillRepository;
import com.mycompany.myapp.service.dto.EmployeeSkillDTO;
import com.mycompany.myapp.service.mapper.EmployeeSkillMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import com.mycompany.myapp.domain.EmployeeSkillId;

/**
 * Service Implementation for managing {@link EmployeeSkill}.
 */
@Service
@Transactional
public class EmployeeSkillServiceImpl implements EmployeeSkillService {

    private final Logger log = LoggerFactory.getLogger(EmployeeSkillServiceImpl.class);

    private final EmployeeSkillRepository employeeSkillRepository;

    private final EmployeeSkillMapper employeeSkillMapper;

    public EmployeeSkillServiceImpl(EmployeeSkillRepository employeeSkillRepository, EmployeeSkillMapper employeeSkillMapper) {
        this.employeeSkillRepository = employeeSkillRepository;
        this.employeeSkillMapper = employeeSkillMapper;
    }

    /**
     * Save a employeeSkill.
     *
     * @param employeeSkillDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EmployeeSkillDTO save(EmployeeSkillDTO employeeSkillDTO) {
        log.debug("Request to save EmployeeSkill : {}", employeeSkillDTO);
        EmployeeSkill employeeSkill = employeeSkillMapper.toEntity(employeeSkillDTO);
        employeeSkill = employeeSkillRepository.save(employeeSkill);
        return employeeSkillMapper.toDto(employeeSkill);
    }

    /**
     * Get all the employeeSkills.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EmployeeSkillDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EmployeeSkills");
        return employeeSkillRepository.findAll(pageable)
            .map(employeeSkillMapper::toDto);
    }

    /**
     * Get all the employeeSkills with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<EmployeeSkillDTO> findAllWithEagerRelationships(Pageable pageable) {
        return employeeSkillRepository.findAllWithEagerRelationships(pageable).map(employeeSkillMapper::toDto);
    }

    /**
     * Get one employeeSkill by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EmployeeSkillDTO> findOne(EmployeeSkillId id) {
        log.debug("Request to get EmployeeSkill : {}", id);
        return employeeSkillRepository.findOneWithEagerRelationships(id)
            .map(employeeSkillMapper::toDto);
    }

    /**
     * Delete the employeeSkill by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(EmployeeSkillId id) {
        log.debug("Request to delete EmployeeSkill : {}", id);
        employeeSkillRepository.deleteById(id);
    }
}
