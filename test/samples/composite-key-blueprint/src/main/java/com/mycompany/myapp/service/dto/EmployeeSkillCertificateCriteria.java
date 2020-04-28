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
import io.github.jhipster.service.filter.LocalDateFilter;

/**
 * Criteria class for the {@link com.mycompany.myapp.domain.EmployeeSkillCertificate} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.EmployeeSkillCertificateResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /employee-skill-certificates?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class EmployeeSkillCertificateCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter typeId;

    private StringFilter skillName;

    private StringFilter skillEmployeeUsername;

    private IntegerFilter grade;

    private LocalDateFilter date;

    public EmployeeSkillCertificateCriteria() {
    }

    public EmployeeSkillCertificateCriteria(EmployeeSkillCertificateCriteria other) {
        this.typeId = other.typeId == null ? null : other.typeId.copy();
        this.skillName = other.skillName == null ? null : other.skillName.copy();
        this.skillEmployeeUsername = other.skillEmployeeUsername == null ? null : other.skillEmployeeUsername.copy();
        this.grade = other.grade == null ? null : other.grade.copy();
        this.date = other.date == null ? null : other.date.copy();
    }

    @Override
    public EmployeeSkillCertificateCriteria copy() {
        return new EmployeeSkillCertificateCriteria(this);
    }

    public LongFilter getTypeId() {
        return typeId;
    }

    public void setTypeId(LongFilter typeId) {
        this.typeId = typeId;
    }

    public StringFilter getSkillName() {
        return skillName;
    }

    public void setSkillName(StringFilter skillName) {
        this.skillName = skillName;
    }

    public StringFilter getSkillEmployeeUsername() {
        return skillEmployeeUsername;
    }

    public void setSkillEmployeeUsername(StringFilter skillEmployeeUsername) {
        this.skillEmployeeUsername = skillEmployeeUsername;
    }

    public IntegerFilter getGrade() {
        return grade;
    }

    public void setGrade(IntegerFilter grade) {
        this.grade = grade;
    }

    public LocalDateFilter getDate() {
        return date;
    }

    public void setDate(LocalDateFilter date) {
        this.date = date;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final EmployeeSkillCertificateCriteria that = (EmployeeSkillCertificateCriteria) o;
        return Objects.equals(typeId, that.typeId) &&
            Objects.equals(skillName, that.skillName) &&
            Objects.equals(skillEmployeeUsername, that.skillEmployeeUsername) &&
            Objects.equals(grade, that.grade) &&
            Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            typeId,
            skillName,
            skillEmployeeUsername,
            grade,
            date
        );
    }

    @Override
    public String toString() {
        return "EmployeeSkillCertificateCriteria{" +
            (typeId != null ? "typeId=" + typeId + ", " : "") +
            (skillName != null ? "skillName=" + skillName + ", " : "") +
            (skillEmployeeUsername != null ? "skillEmployeeUsername=" + skillEmployeeUsername + ", " : "") +
            (grade != null ? "grade=" + grade + ", " : "") +
            (date != null ? "date=" + date + ", " : "") +
            "}";
    }

}
