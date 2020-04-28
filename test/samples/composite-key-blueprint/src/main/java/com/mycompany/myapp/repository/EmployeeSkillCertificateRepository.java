package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.EmployeeSkillCertificate;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import com.mycompany.myapp.domain.EmployeeSkillCertificateId;

/**
 * Spring Data  repository for the EmployeeSkillCertificate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmployeeSkillCertificateRepository extends JpaRepository<EmployeeSkillCertificate, EmployeeSkillCertificateId>, JpaSpecificationExecutor<EmployeeSkillCertificate> {
}
