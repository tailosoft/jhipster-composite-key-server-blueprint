package com.mycompany.myapp.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Employee} entity.
 */
public class EmployeeDTO implements Serializable {

    @NotNull
    private String username;

    @NotNull
    private String fullname;

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EmployeeDTO employeeDTO = (EmployeeDTO) o;
        if (employeeDTO.getUsername() == null && getUsername() == null){
            return false;
        }
        return Objects.equals(getUsername(), employeeDTO.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
            ", username='" + getUsername() + "'" +
            ", fullname='" + getFullname() + "'" +
            "}";
    }
}
