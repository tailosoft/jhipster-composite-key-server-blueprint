package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.EmployeeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Employee} and its DTO {@link EmployeeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EmployeeMapper extends EntityMapper<EmployeeDTO, Employee> {

    @Mapping(target = "skills", ignore = true)
    @Mapping(target = "removeSkill", ignore = true)
    @Mapping(target = "taughtSkills", ignore = true)
    @Mapping(target = "removeTaughtSkill", ignore = true)
    Employee toEntity(EmployeeDTO employeeDTO);

    default Employee fromUsername(String username) {
        if (username == null) {
            return null;
        }
        Employee employee = new Employee();
        employee.setUsername(username);
        return employee;
    }
}
