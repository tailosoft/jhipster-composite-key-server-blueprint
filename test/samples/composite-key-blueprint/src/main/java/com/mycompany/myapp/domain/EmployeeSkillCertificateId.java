package com.mycompany.myapp.domain;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.*;
import java.util.Objects;

@Embeddable
public class EmployeeSkillCertificateId implements java.io.Serializable {

    @NotNull
    @Column(name = "type_id")
    private Long typeId;

    @NotNull
    @Column(name = "skill_name")
    private String skillName;

    @NotNull
    @Column(name = "skill_employee_username")
    private String skillEmployeeUsername;

    public EmployeeSkillCertificateId(){}

    public EmployeeSkillCertificateId(Long typeId, String skillName, String skillEmployeeUsername){
        this.typeId = typeId;
        this.skillName = skillName;
        this.skillEmployeeUsername = skillEmployeeUsername;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EmployeeSkillCertificateId)) {
            return false;
        }

        EmployeeSkillCertificateId employeeSkillCertificateId = (EmployeeSkillCertificateId) o;
        return Objects.equals(typeId, employeeSkillCertificateId.typeId)
            && Objects.equals(skillName, employeeSkillCertificateId.skillName)
            && Objects.equals(skillEmployeeUsername, employeeSkillCertificateId.skillEmployeeUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeId, skillName, skillEmployeeUsername);
    }

    @Override
    public String toString() {
        return "EmployeeSkillCertificateId{" +
            ", typeId=" + getTypeId() +
            ", skillName='" + getSkillName() + "'" +
            ", skillEmployeeUsername='" + getSkillEmployeeUsername() + "'" +
            "}";
    }
}
