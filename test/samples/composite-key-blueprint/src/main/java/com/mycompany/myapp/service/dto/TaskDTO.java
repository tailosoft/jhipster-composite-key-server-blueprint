package com.mycompany.myapp.service.dto;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import com.mycompany.myapp.domain.enumeration.TaskType;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Task} entity.
 */
public class TaskDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private TaskType type;

    private LocalDate endDate;

    @NotNull
    private ZonedDateTime createdAt;

    @NotNull
    private Instant modifiedAt;

    @NotNull
    private Boolean done;

    
    @Lob
    private String description;

    
    @Lob
    private byte[] attachment;

    private String attachmentContentType;

    
    @Lob
    private byte[] picture;

    private String pictureContentType;

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

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Instant modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public Boolean isDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }

    public String getAttachmentContentType() {
        return attachmentContentType;
    }

    public void setAttachmentContentType(String attachmentContentType) {
        this.attachmentContentType = attachmentContentType;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getPictureContentType() {
        return pictureContentType;
    }

    public void setPictureContentType(String pictureContentType) {
        this.pictureContentType = pictureContentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TaskDTO taskDTO = (TaskDTO) o;
        if (taskDTO.getId() == null && getId() == null){
            return false;
        }
        return Objects.equals(getId(), taskDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "TaskDTO{" +
            ", id=" + getId() +
            ", name='" + getName() + "'" +
            ", type='" + getType() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", modifiedAt='" + getModifiedAt() + "'" +
            ", done='" + isDone() + "'" +
            ", description='" + getDescription() + "'" +
            ", attachment='" + getAttachment() + "'" +
            ", picture='" + getPicture() + "'" +
            "}";
    }
}
