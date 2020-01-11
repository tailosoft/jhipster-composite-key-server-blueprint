package com.mycompany.myapp.service.dto;

import com.mycompany.myapp.web.rest.EmployeeSkillResourceIT;
import com.mycompany.myapp.web.rest.EmployeeResourceIT;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class EmployeeSkillDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EmployeeSkillDTO.class);
        EmployeeSkillDTO employeeSkillDTO1 = new EmployeeSkillDTO();
        employeeSkillDTO1.setName(EmployeeSkillResourceIT.DEFAULT_NAME);
        employeeSkillDTO1.setEmployeeUsername(EmployeeResourceIT.DEFAULT_USERNAME);
        EmployeeSkillDTO employeeSkillDTO2 = new EmployeeSkillDTO();
        assertThat(employeeSkillDTO1).isNotEqualTo(employeeSkillDTO2);
        employeeSkillDTO2.setName(employeeSkillDTO1.getName());
        employeeSkillDTO2.setEmployeeUsername(employeeSkillDTO1.getEmployeeUsername());
        assertThat(employeeSkillDTO1).isEqualTo(employeeSkillDTO2);
        employeeSkillDTO2.setName(EmployeeSkillResourceIT.UPDATED_NAME);
        employeeSkillDTO2.setEmployeeUsername(EmployeeResourceIT.UPDATED_USERNAME);
        assertThat(employeeSkillDTO1).isNotEqualTo(employeeSkillDTO2);
        employeeSkillDTO1.setEmployeeUsername(null);
        assertThat(employeeSkillDTO1).isNotEqualTo(employeeSkillDTO2);
    }
}
