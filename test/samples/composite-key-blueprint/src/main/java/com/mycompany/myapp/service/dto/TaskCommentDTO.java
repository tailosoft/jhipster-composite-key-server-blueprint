package com.mycompany.myapp.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.TaskComment} entity.
 */
public class TaskCommentDTO implements Serializable {

    private Long id;

    @NotNull
    private String value;

    private Long taskId;

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
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

        TaskCommentDTO taskCommentDTO = (TaskCommentDTO) o;
        if (taskCommentDTO.getId() == null && getId() == null){
            return false;
        }
        return Objects.equals(getId(), taskCommentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "TaskCommentDTO{" +
            ", id=" + getId() +
            ", value='" + getValue() + "'" +
            ", taskId='" + getTaskId() + "'" +
            "}";
    }
}
