package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.EmployeeSkillCertificateDTO;

import java.util.List;
import java.util.Optional;
import com.mycompany.myapp.domain.EmployeeSkillCertificateId;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.EmployeeSkillCertificate}.
 */
public interface EmployeeSkillCertificateService {

    /**
     * Save a employeeSkillCertificate.
     *
     * @param employeeSkillCertificateDTO the entity to save.
     * @return the persisted entity.
     */
    EmployeeSkillCertificateDTO save(EmployeeSkillCertificateDTO employeeSkillCertificateDTO);

    /**
     * Get all the employeeSkillCertificates.
     *
     * @return the list of entities.
     */
    List<EmployeeSkillCertificateDTO> findAll();


    /**
     * Get the "id" employeeSkillCertificate.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EmployeeSkillCertificateDTO> findOne(EmployeeSkillCertificateId id);

    /**
     * Delete the "id" employeeSkillCertificate.
     *
     * @param id the id of the entity.
     */
    void delete(EmployeeSkillCertificateId id);
}
