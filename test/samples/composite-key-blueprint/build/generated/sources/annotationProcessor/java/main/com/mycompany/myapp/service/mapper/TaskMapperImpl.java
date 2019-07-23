package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Task;
import com.mycompany.myapp.service.dto.TaskDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-07-23T09:59:55+0200",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public TaskDTO toDto(Task entity) {
        if ( entity == null ) {
            return null;
        }

        TaskDTO taskDTO = new TaskDTO();

        taskDTO.setId( entity.getId() );
        taskDTO.setName( entity.getName() );

        return taskDTO;
    }

    @Override
    public List<Task> toEntity(List<TaskDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Task> list = new ArrayList<Task>( dtoList.size() );
        for ( TaskDTO taskDTO : dtoList ) {
            list.add( toEntity( taskDTO ) );
        }

        return list;
    }

    @Override
    public List<TaskDTO> toDto(List<Task> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<TaskDTO> list = new ArrayList<TaskDTO>( entityList.size() );
        for ( Task task : entityList ) {
            list.add( toDto( task ) );
        }

        return list;
    }

    @Override
    public Task toEntity(TaskDTO taskDTO) {
        if ( taskDTO == null ) {
            return null;
        }

        Task task = new Task();

        task.setId( taskDTO.getId() );
        task.setName( taskDTO.getName() );

        return task;
    }
}
