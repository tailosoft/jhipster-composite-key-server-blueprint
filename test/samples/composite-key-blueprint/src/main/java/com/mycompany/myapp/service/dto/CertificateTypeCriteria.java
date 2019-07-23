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
 * Criteria class for the {@link com.mycompany.myapp.domain.CertificateType} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.CertificateTypeResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /certificate-types?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CertificateTypeCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private LongFilter employeeSkillCertificateTypeId;

    private StringFilter employeeSkillCertificateSkillName;

    private StringFilter employeeSkillCertificateSkillEmployeeUsername;

    public CertificateTypeCriteria(){
    }

    public CertificateTypeCriteria(CertificateTypeCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.employeeSkillCertificateTypeId = other.employeeSkillCertificateTypeId == null ? null : other.employeeSkillCertificateTypeId.copy();
        this.employeeSkillCertificateSkillName = other.employeeSkillCertificateSkillName == null ? null : other.employeeSkillCertificateSkillName.copy();
        this.employeeSkillCertificateSkillEmployeeUsername = other.employeeSkillCertificateSkillEmployeeUsername == null ? null : other.employeeSkillCertificateSkillEmployeeUsername.copy();
    }

    @Override
    public CertificateTypeCriteria copy() {
        return new CertificateTypeCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getName() {
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public LongFilter getEmployeeSkillCertificateTypeId() {
        return employeeSkillCertificateTypeId;
    }

    public void setEmployeeSkillCertificateTypeId(LongFilter employeeSkillCertificateTypeId) {
        this.employeeSkillCertificateTypeId = employeeSkillCertificateTypeId;
    }

    public StringFilter getEmployeeSkillCertificateSkillName() {
        return employeeSkillCertificateSkillName;
    }

    public void setEmployeeSkillCertificateSkillName(StringFilter employeeSkillCertificateSkillName) {
        this.employeeSkillCertificateSkillName = employeeSkillCertificateSkillName;
    }

    public StringFilter getEmployeeSkillCertificateSkillEmployeeUsername() {
        return employeeSkillCertificateSkillEmployeeUsername;
    }

    public void setEmployeeSkillCertificateSkillEmployeeUsername(StringFilter employeeSkillCertificateSkillEmployeeUsername) {
        this.employeeSkillCertificateSkillEmployeeUsername = employeeSkillCertificateSkillEmployeeUsername;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CertificateTypeCriteria that = (CertificateTypeCriteria) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(employeeSkillCertificateTypeId, that.employeeSkillCertificateTypeId) &&
            Objects.equals(employeeSkillCertificateSkillName, that.employeeSkillCertificateSkillName) &&
            Objects.equals(employeeSkillCertificateSkillEmployeeUsername, that.employeeSkillCertificateSkillEmployeeUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            name,
            employeeSkillCertificateTypeId,
            employeeSkillCertificateSkillName,
            employeeSkillCertificateSkillEmployeeUsername
        );
    }

    @Override
    public String toString() {
        return "CertificateTypeCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (employeeSkillCertificateTypeId != null ? "employeeSkillCertificateTypeId=" + employeeSkillCertificateTypeId + ", " : "") +
            (employeeSkillCertificateSkillName != null ? "employeeSkillCertificateSkillName=" + employeeSkillCertificateSkillName + ", " : "") +
            (employeeSkillCertificateSkillEmployeeUsername != null ? "employeeSkillCertificateSkillEmployeeUsername=" + employeeSkillCertificateSkillEmployeeUsername + ", " : "") +
            "}";
    }

}
