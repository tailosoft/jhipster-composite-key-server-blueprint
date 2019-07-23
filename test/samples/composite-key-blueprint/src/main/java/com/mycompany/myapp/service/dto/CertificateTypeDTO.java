package com.mycompany.myapp.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.CertificateType} entity.
 */
public class CertificateTypeDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CertificateTypeDTO certificateTypeDTO = (CertificateTypeDTO) o;
        if (certificateTypeDTO.getId() == null && getId() == null){
            return false;
        }
        return Objects.equals(getId(), certificateTypeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "CertificateTypeDTO{" +
            ", id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
