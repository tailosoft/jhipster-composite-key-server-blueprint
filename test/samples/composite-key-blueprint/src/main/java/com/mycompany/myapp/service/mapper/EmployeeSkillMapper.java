package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.EmployeeSkillDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EmployeeSkill} and its DTO {@link EmployeeSkillDTO}.
 */
@Mapper(componentModel = "spring", uses = {TaskMapper.class, EmployeeMapper.class})
public interface EmployeeSkillMapper extends EntityMapper<EmployeeSkillDTO, EmployeeSkill> {

    @Mapping(source = "id.name", target = "name")
    @Mapping(source = "id.employeeUsername", target = "employeeUsername")
    @Mapping(source = "employee.fullname", target = "employeeFullname")
    EmployeeSkillDTO toDto(EmployeeSkill employeeSkill);

    @Mapping(source = "name", target = "id.name")
    @Mapping(target = "employeeSkillCertificates", ignore = true)
    @Mapping(target = "removeEmployeeSkillCertificate", ignore = true)
    @Mapping(target = "removeTask", ignore = true)
    @Mapping(source = "employeeUsername", target = "id.employeeUsername")
    @Mapping(source = "employeeUsername", target = "employee")
    EmployeeSkill toEntity(EmployeeSkillDTO employeeSkillDTO);

    default EmployeeSkill fromId(EmployeeSkillId id) {
        if (id == null) {
            return null;
        }
        EmployeeSkill employeeSkill = new EmployeeSkill();
        employeeSkill.setId(id);
        return employeeSkill;
    }
}
