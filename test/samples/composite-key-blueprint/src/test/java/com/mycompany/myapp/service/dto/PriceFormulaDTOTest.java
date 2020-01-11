package com.mycompany.myapp.service.dto;

import com.mycompany.myapp.web.rest.PriceFormulaResourceIT;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class PriceFormulaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PriceFormulaDTO.class);
        PriceFormulaDTO priceFormulaDTO1 = new PriceFormulaDTO();
        priceFormulaDTO1.setMax(PriceFormulaResourceIT.DEFAULT_MAX);
        PriceFormulaDTO priceFormulaDTO2 = new PriceFormulaDTO();
        assertThat(priceFormulaDTO1).isNotEqualTo(priceFormulaDTO2);
        priceFormulaDTO2.setMax(priceFormulaDTO1.getMax());
        assertThat(priceFormulaDTO1).isEqualTo(priceFormulaDTO2);
        priceFormulaDTO2.setMax(PriceFormulaResourceIT.UPDATED_MAX);
        assertThat(priceFormulaDTO1).isNotEqualTo(priceFormulaDTO2);
        priceFormulaDTO1.setMax(null);
        assertThat(priceFormulaDTO1).isNotEqualTo(priceFormulaDTO2);
    }
}
