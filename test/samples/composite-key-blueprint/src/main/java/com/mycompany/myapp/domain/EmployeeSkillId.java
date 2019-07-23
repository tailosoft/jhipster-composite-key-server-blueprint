package com.mycompany.myapp.domain;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class EmployeeSkillId implements java.io.Serializable {

    @Column(name = "name")
    private String name;

    @Column(name = "employee_username")
    private String employeeUsername;

    public EmployeeSkillId(){}

    public EmployeeSkillId(String name, String employeeUsername){
        this.name = name;
        this.employeeUsername = employeeUsername;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmployeeUsername(){
        return this.employeeUsername;
    }

    public void setEmployeeUsername(String employeeUsername){
        this.employeeUsername = employeeUsername;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EmployeeSkillId)) {
            return false;
        }

        EmployeeSkillId employeeSkillId = (EmployeeSkillId) o;
        return Objects.equals(name, employeeSkillId.name)
            && Objects.equals(employeeUsername, employeeSkillId.employeeUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, employeeUsername);
    }

    @Override
    public String toString() {
        return "EmployeeSkillId{" +
            ", name='" + getName() + "'" +
            ", employeeUsername='" + getEmployeeUsername() + "'" +
            "}";
    }
}
