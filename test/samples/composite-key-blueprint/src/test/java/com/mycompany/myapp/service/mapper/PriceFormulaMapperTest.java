package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.web.rest.PriceFormulaResourceIT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class PriceFormulaMapperTest {

    private PriceFormulaMapper priceFormulaMapper;

    @BeforeEach
    public void setUp() {
        priceFormulaMapper = new PriceFormulaMapperImpl();
    }

    @Test
        public void testEntityFromId() {
        assertThat(priceFormulaMapper.fromMax(PriceFormulaResourceIT.UPDATED_MAX).getMax()).isEqualTo(PriceFormulaResourceIT.UPDATED_MAX);
        assertThat(priceFormulaMapper.fromMax(null)).isNull();
    }
}
