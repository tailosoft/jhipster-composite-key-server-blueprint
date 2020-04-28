package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CertificateTypeMapperTest {

    private CertificateTypeMapper certificateTypeMapper;

    @BeforeEach
    public void setUp() {
        certificateTypeMapper = new CertificateTypeMapperImpl();
    }

    @Test
        public void testEntityFromId() {
        assertThat(certificateTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(certificateTypeMapper.fromId(null)).isNull();
    }
}
