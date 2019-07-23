package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Task;
import com.mycompany.myapp.domain.TaskComment;
import com.mycompany.myapp.service.dto.TaskCommentDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-07-23T09:59:55+0200",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class TaskCommentMapperImpl implements TaskCommentMapper {

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public List<TaskComment> toEntity(List<TaskCommentDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<TaskComment> list = new ArrayList<TaskComment>( dtoList.size() );
        for ( TaskCommentDTO taskCommentDTO : dtoList ) {
            list.add( toEntity( taskCommentDTO ) );
        }

        return list;
    }

    @Override
    public List<TaskCommentDTO> toDto(List<TaskComment> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<TaskCommentDTO> list = new ArrayList<TaskCommentDTO>( entityList.size() );
        for ( TaskComment taskComment : entityList ) {
            list.add( toDto( taskComment ) );
        }

        return list;
    }

    @Override
    public TaskCommentDTO toDto(TaskComment taskComment) {
        if ( taskComment == null ) {
            return null;
        }

        TaskCommentDTO taskCommentDTO = new TaskCommentDTO();

        taskCommentDTO.setTaskId( taskCommentTaskId( taskComment ) );
        taskCommentDTO.setId( taskComment.getId() );
        taskCommentDTO.setValue( taskComment.getValue() );

        return taskCommentDTO;
    }

    @Override
    public TaskComment toEntity(TaskCommentDTO taskCommentDTO) {
        if ( taskCommentDTO == null ) {
            return null;
        }

        TaskComment taskComment = new TaskComment();

        taskComment.setTask( taskMapper.fromId( taskCommentDTO.getTaskId() ) );
        taskComment.setId( taskCommentDTO.getId() );
        taskComment.setValue( taskCommentDTO.getValue() );

        return taskComment;
    }

    private Long taskCommentTaskId(TaskComment taskComment) {
        if ( taskComment == null ) {
            return null;
        }
        Task task = taskComment.getTask();
        if ( task == null ) {
            return null;
        }
        Long id = task.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
