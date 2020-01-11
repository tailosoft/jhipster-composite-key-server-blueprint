package com.mycompany.myapp.domain;

import com.mycompany.myapp.web.rest.PriceFormulaResourceIT;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class PriceFormulaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PriceFormula.class);
        PriceFormula priceFormula1 = new PriceFormula();
        priceFormula1.setMax(PriceFormulaResourceIT.DEFAULT_MAX);
        PriceFormula priceFormula2 = new PriceFormula();
        priceFormula2.setMax(PriceFormulaResourceIT.DEFAULT_MAX);
        assertThat(priceFormula1).isEqualTo(priceFormula2);
        priceFormula2.setMax(PriceFormulaResourceIT.UPDATED_MAX);
        assertThat(priceFormula1).isNotEqualTo(priceFormula2);
        priceFormula1.setMax(null);
        assertThat(priceFormula1).isNotEqualTo(priceFormula2);
    }
}
