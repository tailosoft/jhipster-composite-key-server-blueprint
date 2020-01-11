package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.web.rest.EmployeeResourceIT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class EmployeeMapperTest {

    private EmployeeMapper employeeMapper;

    @BeforeEach
    public void setUp() {
        employeeMapper = new EmployeeMapperImpl();
    }

    @Test
        public void testEntityFromId() {
        assertThat(employeeMapper.fromUsername(EmployeeResourceIT.UPDATED_USERNAME).getUsername()).isEqualTo(EmployeeResourceIT.UPDATED_USERNAME);
        assertThat(employeeMapper.fromUsername(null)).isNull();
    }
}
