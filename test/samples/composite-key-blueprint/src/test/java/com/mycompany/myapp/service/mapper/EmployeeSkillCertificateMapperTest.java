package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.web.rest.EmployeeSkillResourceIT;
import com.mycompany.myapp.web.rest.EmployeeResourceIT;
import com.mycompany.myapp.domain.EmployeeSkillCertificateId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EmployeeSkillCertificateMapperTest {

    private EmployeeSkillCertificateMapper employeeSkillCertificateMapper;

    @BeforeEach
    public void setUp() {
        employeeSkillCertificateMapper = new EmployeeSkillCertificateMapperImpl();
    }

    @Test
        public void testEntityFromId() {
        assertThat(employeeSkillCertificateMapper.fromId(new EmployeeSkillCertificateId(2L, EmployeeSkillResourceIT.UPDATED_NAME, EmployeeResourceIT.UPDATED_USERNAME)).getId()).isEqualTo(new EmployeeSkillCertificateId(2L, EmployeeSkillResourceIT.UPDATED_NAME, EmployeeResourceIT.UPDATED_USERNAME));
        assertThat(employeeSkillCertificateMapper.fromId(null)).isNull();
    }
}
