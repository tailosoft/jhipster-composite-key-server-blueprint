package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.web.rest.EmployeeSkillResourceIT;
import com.mycompany.myapp.web.rest.EmployeeResourceIT;
import com.mycompany.myapp.domain.EmployeeSkillId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class EmployeeSkillMapperTest {

    private EmployeeSkillMapper employeeSkillMapper;

    @BeforeEach
    public void setUp() {
        employeeSkillMapper = new EmployeeSkillMapperImpl();
    }

    @Test
        public void testEntityFromId() {
        assertThat(employeeSkillMapper.fromId(new EmployeeSkillId(EmployeeSkillResourceIT.UPDATED_NAME, EmployeeResourceIT.UPDATED_USERNAME)).getId()).isEqualTo(new EmployeeSkillId(EmployeeSkillResourceIT.UPDATED_NAME, EmployeeResourceIT.UPDATED_USERNAME));
        assertThat(employeeSkillMapper.fromId(null)).isNull();
    }
}
