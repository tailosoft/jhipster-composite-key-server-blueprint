package com.mycompany.myapp.service.dto;

import com.mycompany.myapp.web.rest.EmployeeResourceIT;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class EmployeeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EmployeeDTO.class);
        EmployeeDTO employeeDTO1 = new EmployeeDTO();
        employeeDTO1.setUsername(EmployeeResourceIT.DEFAULT_USERNAME);
        EmployeeDTO employeeDTO2 = new EmployeeDTO();
        assertThat(employeeDTO1).isNotEqualTo(employeeDTO2);
        employeeDTO2.setUsername(employeeDTO1.getUsername());
        assertThat(employeeDTO1).isEqualTo(employeeDTO2);
        employeeDTO2.setUsername(EmployeeResourceIT.UPDATED_USERNAME);
        assertThat(employeeDTO1).isNotEqualTo(employeeDTO2);
        employeeDTO1.setUsername(null);
        assertThat(employeeDTO1).isNotEqualTo(employeeDTO2);
    }
}
