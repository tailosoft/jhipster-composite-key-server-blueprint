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
 * Criteria class for the {@link com.mycompany.myapp.domain.EmployeeSkill} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.EmployeeSkillResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /employee-skills?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class EmployeeSkillCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private StringFilter name;

    private StringFilter employeeUsername;

    private IntegerFilter level;

    private LongFilter employeeSkillCertificateTypeId;

    private LongFilter taskId;

    public EmployeeSkillCriteria(){
    }

    public EmployeeSkillCriteria(EmployeeSkillCriteria other){
        this.name = other.name == null ? null : other.name.copy();
        this.employeeUsername = other.employeeUsername == null ? null : other.employeeUsername.copy();
        this.level = other.level == null ? null : other.level.copy();
        this.employeeSkillCertificateTypeId = other.employeeSkillCertificateTypeId == null ? null : other.employeeSkillCertificateTypeId.copy();
        this.taskId = other.taskId == null ? null : other.taskId.copy();
    }

    @Override
    public EmployeeSkillCriteria copy() {
        return new EmployeeSkillCriteria(this);
    }

    public StringFilter getName() {
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public StringFilter getEmployeeUsername() {
        return employeeUsername;
    }

    public void setEmployeeUsername(StringFilter employeeUsername) {
        this.employeeUsername = employeeUsername;
    }

    public IntegerFilter getLevel() {
        return level;
    }

    public void setLevel(IntegerFilter level) {
        this.level = level;
    }

    public LongFilter getEmployeeSkillCertificateTypeId() {
        return employeeSkillCertificateTypeId;
    }

    public void setEmployeeSkillCertificateTypeId(LongFilter employeeSkillCertificateTypeId) {
        this.employeeSkillCertificateTypeId = employeeSkillCertificateTypeId;
    }

    public LongFilter getTaskId() {
        return taskId;
    }

    public void setTaskId(LongFilter taskId) {
        this.taskId = taskId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final EmployeeSkillCriteria that = (EmployeeSkillCriteria) o;
        return Objects.equals(name, that.name) &&
            Objects.equals(employeeUsername, that.employeeUsername) &&
            Objects.equals(level, that.level) &&
            Objects.equals(employeeSkillCertificateTypeId, that.employeeSkillCertificateTypeId) &&
            Objects.equals(taskId, that.taskId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            name,
            employeeUsername,
            level,
            employeeSkillCertificateTypeId,
            taskId
        );
    }

    @Override
    public String toString() {
        return "EmployeeSkillCriteria{" +
            (name != null ? "name=" + name + ", " : "") +
            (employeeUsername != null ? "employeeUsername=" + employeeUsername + ", " : "") +
            (level != null ? "level=" + level + ", " : "") +
            (employeeSkillCertificateTypeId != null ? "employeeSkillCertificateTypeId=" + employeeSkillCertificateTypeId + ", " : "") +
            (taskId != null ? "taskId=" + taskId + ", " : "") +
            "}";
    }

}
