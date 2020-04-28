package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.LocalDate;

/**
 * A EmployeeSkillCertificate.
 */
@Entity
@Table(name = "employee_skill_certificate")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EmployeeSkillCertificate implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    EmployeeSkillCertificateId id;

    @NotNull
    @Column(name = "grade", nullable = false)
    private Integer grade;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(insertable = false, updatable = false)
    @JsonIgnoreProperties("employeeSkillCertificates")
    private CertificateType type;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumns({@JoinColumn(insertable = false, updatable = false), @JoinColumn(insertable = false, updatable = false)})
    @JsonIgnoreProperties("employeeSkillCertificates")
    private EmployeeSkill skill;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public EmployeeSkillCertificateId getId() {
        return id;
    }

    public void setId(EmployeeSkillCertificateId id) {
        this.id = id;
    }

    public Integer getGrade() {
        return grade;
    }

    public EmployeeSkillCertificate grade(Integer grade) {
        this.grade = grade;
        return this;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public LocalDate getDate() {
        return date;
    }

    public EmployeeSkillCertificate date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public CertificateType getType() {
        return type;
    }

    public EmployeeSkillCertificate type(CertificateType certificateType) {
        this.type = certificateType;
        return this;
    }

    public void setType(CertificateType certificateType) {
        this.type = certificateType;
    }

    public EmployeeSkill getSkill() {
        return skill;
    }

    public EmployeeSkillCertificate skill(EmployeeSkill employeeSkill) {
        this.skill = employeeSkill;
        return this;
    }

    public void setSkill(EmployeeSkill employeeSkill) {
        this.skill = employeeSkill;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EmployeeSkillCertificate)) {
            return false;
        }
        return id != null && id.equals(((EmployeeSkillCertificate) o).id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "EmployeeSkillCertificate{" +
            "id=" + getId() +
            ", grade=" + getGrade() +
            ", date='" + getDate() + "'" +
            "}";
    }
}
