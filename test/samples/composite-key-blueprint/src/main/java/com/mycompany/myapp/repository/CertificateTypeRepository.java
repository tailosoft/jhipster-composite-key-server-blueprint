package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.CertificateType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CertificateType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CertificateTypeRepository extends JpaRepository<CertificateType, Long>, JpaSpecificationExecutor<CertificateType> {
}
