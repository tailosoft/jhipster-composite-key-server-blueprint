package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.mycompany.myapp.domain.PriceFormula} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.PriceFormulaResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /price-formulas?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PriceFormulaCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private IntegerFilter max;

    private StringFilter formula;

    public PriceFormulaCriteria() {
    }

    public PriceFormulaCriteria(PriceFormulaCriteria other) {
        this.max = other.max == null ? null : other.max.copy();
        this.formula = other.formula == null ? null : other.formula.copy();
    }

    @Override
    public PriceFormulaCriteria copy() {
        return new PriceFormulaCriteria(this);
    }

    public IntegerFilter getMax() {
        return max;
    }

    public void setMax(IntegerFilter max) {
        this.max = max;
    }

    public StringFilter getFormula() {
        return formula;
    }

    public void setFormula(StringFilter formula) {
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
        final PriceFormulaCriteria that = (PriceFormulaCriteria) o;
        return Objects.equals(max, that.max) &&
            Objects.equals(formula, that.formula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            max,
            formula
        );
    }

    @Override
    public String toString() {
        return "PriceFormulaCriteria{" +
            (max != null ? "max=" + max + ", " : "") +
            (formula != null ? "formula=" + formula + ", " : "") +
            "}";
    }

}
