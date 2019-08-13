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
 * Criteria class for the {@link com.mycompany.myapp.domain.Employee} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.EmployeeResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /employees?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class EmployeeCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private StringFilter username;

    private StringFilter fullname;

    private StringFilter skillName;

    public EmployeeCriteria(){
    }

    public EmployeeCriteria(EmployeeCriteria other){
        this.username = other.username == null ? null : other.username.copy();
        this.fullname = other.fullname == null ? null : other.fullname.copy();
        this.skillName = other.skillName == null ? null : other.skillName.copy();
    }

    @Override
    public EmployeeCriteria copy() {
        return new EmployeeCriteria(this);
    }

    public StringFilter getUsername() {
        return username;
    }

    public void setUsername(StringFilter username) {
        this.username = username;
    }

    public StringFilter getFullname() {
        return fullname;
    }

    public void setFullname(StringFilter fullname) {
        this.fullname = fullname;
    }

    public StringFilter getSkillName() {
        return skillName;
    }

    public void setSkillName(StringFilter skillName) {
        this.skillName = skillName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final EmployeeCriteria that = (EmployeeCriteria) o;
        return Objects.equals(username, that.username) &&
            Objects.equals(fullname, that.fullname) &&
            Objects.equals(skillName, that.skillName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            username,
            fullname,
            skillName
        );
    }

    @Override
    public String toString() {
        return "EmployeeCriteria{" +
            (username != null ? "username=" + username + ", " : "") +
            (fullname != null ? "fullname=" + fullname + ", " : "") +
            (skillName != null ? "skillName=" + skillName + ", " : "") +
            "}";
    }

}
