package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.mycompany.myapp.domain.Task} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.TaskResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /tasks?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class TaskCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private LongFilter commentId;

    private StringFilter employeeSkillName;

    private StringFilter employeeSkillEmployeeUsername;

    public TaskCriteria(){
    }

    public TaskCriteria(TaskCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.commentId = other.commentId == null ? null : other.commentId.copy();
        this.employeeSkillName = other.employeeSkillName == null ? null : other.employeeSkillName.copy();
        this.employeeSkillEmployeeUsername = other.employeeSkillEmployeeUsername == null ? null : other.employeeSkillEmployeeUsername.copy();
    }

    @Override
    public TaskCriteria copy() {
        return new TaskCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getName() {
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public LongFilter getCommentId() {
        return commentId;
    }

    public void setCommentId(LongFilter commentId) {
        this.commentId = commentId;
    }

    public StringFilter getEmployeeSkillName() {
        return employeeSkillName;
    }

    public void setEmployeeSkillName(StringFilter employeeSkillName) {
        this.employeeSkillName = employeeSkillName;
    }

    public StringFilter getEmployeeSkillEmployeeUsername() {
        return employeeSkillEmployeeUsername;
    }

    public void setEmployeeSkillEmployeeUsername(StringFilter employeeSkillEmployeeUsername) {
        this.employeeSkillEmployeeUsername = employeeSkillEmployeeUsername;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final TaskCriteria that = (TaskCriteria) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(commentId, that.commentId) &&
            Objects.equals(employeeSkillName, that.employeeSkillName) &&
            Objects.equals(employeeSkillEmployeeUsername, that.employeeSkillEmployeeUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            name,
            commentId,
            employeeSkillName,
            employeeSkillEmployeeUsername
        );
    }

    @Override
    public String toString() {
        return "TaskCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (commentId != null ? "commentId=" + commentId + ", " : "") +
            (employeeSkillName != null ? "employeeSkillName=" + employeeSkillName + ", " : "") +
            (employeeSkillEmployeeUsername != null ? "employeeSkillEmployeeUsername=" + employeeSkillEmployeeUsername + ", " : "") +
            "}";
    }

}
