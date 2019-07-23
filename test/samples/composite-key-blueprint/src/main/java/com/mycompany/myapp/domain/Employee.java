package com.mycompany.myapp.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

/**
 * A Employee.
 */
@Entity
@Table(name = "employee")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Column(name = "username", nullable = false, insertable = false, updatable = false)
    private String username;

    @NotNull
    @Column(name = "fullname", nullable = false)
    private String fullname;

    @OneToMany(mappedBy = "employee")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<EmployeeSkill> skills = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public String getUsername() {
        return username;
    }

    public Employee username(String username) {
        this.username = username;
        return this;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public Employee fullname(String fullname) {
        this.fullname = fullname;
        return this;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public Employee skills(Set<EmployeeSkill> employeeSkills) {
        this.skills = employeeSkills;
        return this;
    }

    public Employee addSkill(EmployeeSkill employeeSkill) {
        this.skills.add(employeeSkill);
        employeeSkill.setEmployee(this);
        return this;
    }

    public Employee removeSkill(EmployeeSkill employeeSkill) {
        this.skills.remove(employeeSkill);
        employeeSkill.setEmployee(null);
        return this;
    }

    public void setSkills(Set<EmployeeSkill> employeeSkills) {
        this.skills = employeeSkills;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Employee)) {
            return false;
        }
        return username != null && username.equals(((Employee) o).username);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(username);
    }

    @Override
    public String toString() {
        return "Employee{" +
            ", username='" + getUsername() + "'" +
            ", fullname='" + getFullname() + "'" +
            "}";
    }
}
