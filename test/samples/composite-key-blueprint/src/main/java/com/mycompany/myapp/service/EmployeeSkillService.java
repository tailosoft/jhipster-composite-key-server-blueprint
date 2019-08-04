package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.EmployeeSkillDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import com.mycompany.myapp.domain.EmployeeSkillId;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.EmployeeSkill}.
 */
public interface EmployeeSkillService {

    /**
     * Save a employeeSkill.
     *
     * @param employeeSkillDTO the entity to save.
     * @return the persisted entity.
     */
    EmployeeSkillDTO save(EmployeeSkillDTO employeeSkillDTO);

    /**
     * Get all the employeeSkills.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EmployeeSkillDTO> findAll(Pageable pageable);

    /**
     * Get all the employeeSkills with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<EmployeeSkillDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" employeeSkill.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EmployeeSkillDTO> findOne(EmployeeSkillId id);

    /**
     * Delete the "id" employeeSkill.
     *
     * @param id the id of the entity.
     */
    void delete(EmployeeSkillId id);
}
