package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.CertificateTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CertificateType} and its DTO {@link CertificateTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CertificateTypeMapper extends EntityMapper<CertificateTypeDTO, CertificateType> {

    @Mapping(target = "employeeSkillCertificates", ignore = true)
    @Mapping(target = "removeEmployeeSkillCertificate", ignore = true)
    CertificateType toEntity(CertificateTypeDTO certificateTypeDTO);

    default CertificateType fromId(Long id) {
        if (id == null) {
            return null;
        }
        CertificateType certificateType = new CertificateType();
        certificateType.setId(id);
        return certificateType;
    }
}
