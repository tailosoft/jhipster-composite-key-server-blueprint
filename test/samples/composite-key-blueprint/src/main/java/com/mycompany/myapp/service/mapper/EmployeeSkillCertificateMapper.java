package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.EmployeeSkillCertificateDTO;

import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Mapper for the entity {@link EmployeeSkillCertificate} and its DTO {@link EmployeeSkillCertificateDTO}.
 */
@Mapper(componentModel = "spring", uses = {CertificateTypeMapper.class, EmployeeSkillMapper.class})
public abstract class EmployeeSkillCertificateMapper implements EntityMapper<EmployeeSkillCertificateDTO, EmployeeSkillCertificate> {

    @Autowired
    EmployeeSkillMapper employeeSkillMapper;

    @Mapping(source = "id.typeId", target = "typeId")
    @Mapping(source = "type.name", target = "typeName")
    @Mapping(source = "id.skillName", target = "skillName")
    @Mapping(source = "id.skillEmployeeUsername", target = "skillEmployeeUsername")
    @Mapping(source = "skill.employee.fullname", target = "skillEmployeeFullname")
    public abstract EmployeeSkillCertificateDTO toDto(EmployeeSkillCertificate employeeSkillCertificate);

    @Mapping(source = "typeId", target = "id.typeId")
    @Mapping(source = "typeId", target = "type")
    @Mapping(source = "skillName", target = "id.skillName")
    @Mapping(source = "skillEmployeeUsername", target = "id.skillEmployeeUsername")
    @Mapping(expression = "java(this.employeeSkillMapper.fromId(new com.mycompany.myapp.domain.EmployeeSkillId(employeeSkillCertificateDTO.getSkillName(), employeeSkillCertificateDTO.getSkillEmployeeUsername())))", target = "skill")
    public abstract EmployeeSkillCertificate toEntity(EmployeeSkillCertificateDTO employeeSkillCertificateDTO);

    public EmployeeSkillCertificate fromId(EmployeeSkillCertificateId id) {
        if (id == null) {
            return null;
        }
        EmployeeSkillCertificate employeeSkillCertificate = new EmployeeSkillCertificate();
        employeeSkillCertificate.setId(id);
        return employeeSkillCertificate;
    }
}
