package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.EmployeeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.Employee}.
 */
public interface EmployeeService {

    /**
     * Save a employee.
     *
     * @param employeeDTO the entity to save.
     * @return the persisted entity.
     */
    EmployeeDTO save(EmployeeDTO employeeDTO);

    /**
     * Get all the employees.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EmployeeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" employee.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EmployeeDTO> findOne(String id);

    /**
     * Delete the "id" employee.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
