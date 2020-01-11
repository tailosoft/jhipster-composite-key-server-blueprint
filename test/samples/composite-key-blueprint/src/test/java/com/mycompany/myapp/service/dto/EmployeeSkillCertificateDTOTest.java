package com.mycompany.myapp.service.dto;

import com.mycompany.myapp.web.rest.EmployeeSkillResourceIT;
import com.mycompany.myapp.web.rest.EmployeeResourceIT;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class EmployeeSkillCertificateDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EmployeeSkillCertificateDTO.class);
        EmployeeSkillCertificateDTO employeeSkillCertificateDTO1 = new EmployeeSkillCertificateDTO();
        employeeSkillCertificateDTO1.setTypeId(1L);
        employeeSkillCertificateDTO1.setSkillName(EmployeeSkillResourceIT.DEFAULT_NAME);
        employeeSkillCertificateDTO1.setSkillEmployeeUsername(EmployeeResourceIT.DEFAULT_USERNAME);
        EmployeeSkillCertificateDTO employeeSkillCertificateDTO2 = new EmployeeSkillCertificateDTO();
        assertThat(employeeSkillCertificateDTO1).isNotEqualTo(employeeSkillCertificateDTO2);
        employeeSkillCertificateDTO2.setTypeId(employeeSkillCertificateDTO1.getTypeId());
        employeeSkillCertificateDTO2.setSkillName(employeeSkillCertificateDTO1.getSkillName());
        employeeSkillCertificateDTO2.setSkillEmployeeUsername(employeeSkillCertificateDTO1.getSkillEmployeeUsername());
        assertThat(employeeSkillCertificateDTO1).isEqualTo(employeeSkillCertificateDTO2);
        employeeSkillCertificateDTO2.setTypeId(2L);
        employeeSkillCertificateDTO2.setSkillName(EmployeeSkillResourceIT.UPDATED_NAME);
        employeeSkillCertificateDTO2.setSkillEmployeeUsername(EmployeeResourceIT.UPDATED_USERNAME);
        assertThat(employeeSkillCertificateDTO1).isNotEqualTo(employeeSkillCertificateDTO2);
        employeeSkillCertificateDTO1.setSkillEmployeeUsername(null);
        assertThat(employeeSkillCertificateDTO1).isNotEqualTo(employeeSkillCertificateDTO2);
    }
}
