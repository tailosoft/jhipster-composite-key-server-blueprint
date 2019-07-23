package com.mycompany.myapp.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.EmployeeSkillCertificate} entity.
 */
public class EmployeeSkillCertificateDTO implements Serializable {

    @NotNull
    private Long typeId;

    @NotNull
    private String skillName;

    @NotNull
    private String skillEmployeeUsername;

    @NotNull
    private Integer grade;

    @NotNull
    private LocalDate date;

    private String typeName;

    public Long getTypeId(){
        return this.typeId;
    }

    public void setTypeId(Long typeId){
        this.typeId = typeId;
    }

    public String getSkillName(){
        return this.skillName;
    }

    public void setSkillName(String skillName){
        this.skillName = skillName;
    }

    public String getSkillEmployeeUsername(){
        return this.skillEmployeeUsername;
    }

    public void setSkillEmployeeUsername(String skillEmployeeUsername){
        this.skillEmployeeUsername = skillEmployeeUsername;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EmployeeSkillCertificateDTO employeeSkillCertificateDTO = (EmployeeSkillCertificateDTO) o;
        if (employeeSkillCertificateDTO.getTypeId() == null && getTypeId() == null &&
            employeeSkillCertificateDTO.getSkillName() == null && getSkillName() == null &&
            employeeSkillCertificateDTO.getSkillEmployeeUsername() == null && getSkillEmployeeUsername() == null){
            return false;
        }
        return Objects.equals(getTypeId(), employeeSkillCertificateDTO.getTypeId()) &&
            Objects.equals(getSkillName(), employeeSkillCertificateDTO.getSkillName()) &&
            Objects.equals(getSkillEmployeeUsername(), employeeSkillCertificateDTO.getSkillEmployeeUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeId, skillName, skillEmployeeUsername);
    }

    @Override
    public String toString() {
        return "EmployeeSkillCertificateDTO{" +
            ", typeId=" + getTypeId() +
            ", skillName='" + getSkillName() + "'" +
            ", skillEmployeeUsername='" + getSkillEmployeeUsername() + "'" +
            ", grade=" + getGrade() +
            ", date='" + getDate() + "'" +
            ", typeName='" + getTypeName() + "'" +
            "}";
    }
}
