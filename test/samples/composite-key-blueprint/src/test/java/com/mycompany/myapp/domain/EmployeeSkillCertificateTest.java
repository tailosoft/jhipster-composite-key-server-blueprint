package com.mycompany.myapp.domain;

import com.mycompany.myapp.web.rest.EmployeeSkillResourceIT;
import com.mycompany.myapp.web.rest.EmployeeResourceIT;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class EmployeeSkillCertificateTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EmployeeSkillCertificate.class);
        EmployeeSkillCertificate employeeSkillCertificate1 = new EmployeeSkillCertificate();
        employeeSkillCertificate1.setId(new EmployeeSkillCertificateId(1L, EmployeeSkillResourceIT.DEFAULT_NAME, EmployeeResourceIT.DEFAULT_USERNAME));
        EmployeeSkillCertificate employeeSkillCertificate2 = new EmployeeSkillCertificate();
        employeeSkillCertificate2.setId(new EmployeeSkillCertificateId(1L, EmployeeSkillResourceIT.DEFAULT_NAME, EmployeeResourceIT.DEFAULT_USERNAME));
        assertThat(employeeSkillCertificate1).isEqualTo(employeeSkillCertificate2);
        employeeSkillCertificate2.setId(new EmployeeSkillCertificateId(2L, EmployeeSkillResourceIT.UPDATED_NAME, EmployeeResourceIT.UPDATED_USERNAME));
        assertThat(employeeSkillCertificate1).isNotEqualTo(employeeSkillCertificate2);
        employeeSkillCertificate1.setId(null);
        assertThat(employeeSkillCertificate1).isNotEqualTo(employeeSkillCertificate2);
    }
}
