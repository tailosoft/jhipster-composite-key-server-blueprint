package com.mycompany.myapp.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.EmployeeSkill} entity.
 */
public class EmployeeSkillDTO implements Serializable {

    @NotNull
    private String name;

    @NotNull
    private String employeeUsername;

    @NotNull
    private Integer level;

    private Set<TaskDTO> tasks = new HashSet<>();

    private String employeeFullname;

    private String teacherUsername;

    private String teacherFullname;

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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Set<TaskDTO> getTasks() {
        return tasks;
    }

    public void setTasks(Set<TaskDTO> tasks) {
        this.tasks = tasks;
    }

    public String getEmployeeFullname() {
        return employeeFullname;
    }

    public void setEmployeeFullname(String employeeFullname) {
        this.employeeFullname = employeeFullname;
    }

    public String getTeacherUsername() {
        return teacherUsername;
    }

    public void setTeacherUsername(String teacherUsername) {
        this.teacherUsername = teacherUsername;
    }

    public String getTeacherFullname() {
        return teacherFullname;
    }

    public void setTeacherFullname(String teacherFullname) {
        this.teacherFullname = teacherFullname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EmployeeSkillDTO employeeSkillDTO = (EmployeeSkillDTO) o;
        if (employeeSkillDTO.getName() == null && getName() == null &&
            employeeSkillDTO.getEmployeeUsername() == null && getEmployeeUsername() == null){
            return false;
        }
        return Objects.equals(getName(), employeeSkillDTO.getName()) &&
            Objects.equals(getEmployeeUsername(), employeeSkillDTO.getEmployeeUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, employeeUsername);
    }

    @Override
    public String toString() {
        return "EmployeeSkillDTO{" +
            ", name='" + getName() + "'" +
            ", employeeUsername='" + getEmployeeUsername() + "'" +
            ", level=" + getLevel() +
            ", employeeFullname='" + getEmployeeFullname() + "'" +
            ", teacherUsername='" + getTeacherUsername() + "'" +
            ", teacherFullname='" + getTeacherFullname() + "'" +
            "}";
    }
}
