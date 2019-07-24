package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.TaskCommentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TaskComment} and its DTO {@link TaskCommentDTO}.
 */
@Mapper(componentModel = "spring", uses = {TaskMapper.class})
public interface TaskCommentMapper extends EntityMapper<TaskCommentDTO, TaskComment> {

    @Mapping(source = "task.id", target = "taskId")
    @Mapping(source = "task.name", target = "taskName")
    TaskCommentDTO toDto(TaskComment taskComment);

    @Mapping(source = "taskId", target = "task")
    TaskComment toEntity(TaskCommentDTO taskCommentDTO);

    default TaskComment fromId(Long id) {
        if (id == null) {
            return null;
        }
        TaskComment taskComment = new TaskComment();
        taskComment.setId(id);
        return taskComment;
    }
}
