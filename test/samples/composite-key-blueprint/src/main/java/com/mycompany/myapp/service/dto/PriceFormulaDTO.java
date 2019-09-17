package com.mycompany.myapp.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.PriceFormula} entity.
 */
public class PriceFormulaDTO implements Serializable {

    @NotNull
    private Integer max;

    @NotNull
    private String formula;

    public Integer getMax(){
        return this.max;
    }

    public void setMax(Integer max){
        this.max = max;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PriceFormulaDTO priceFormulaDTO = (PriceFormulaDTO) o;
        if (priceFormulaDTO.getMax() == null && getMax() == null){
            return false;
        }
        return Objects.equals(getMax(), priceFormulaDTO.getMax());
    }

    @Override
    public int hashCode() {
        return Objects.hash(max);
    }

    @Override
    public String toString() {
        return "PriceFormulaDTO{" +
            ", max=" + getMax() +
            ", formula='" + getFormula() + "'" +
            "}";
    }
}
