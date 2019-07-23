package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Employee;
import com.mycompany.myapp.domain.EmployeeSkill;
import com.mycompany.myapp.domain.EmployeeSkillId;
import com.mycompany.myapp.domain.Task;
import com.mycompany.myapp.service.dto.EmployeeSkillDTO;
import com.mycompany.myapp.service.dto.TaskDTO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-07-23T09:59:55+0200",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class EmployeeSkillMapperImpl implements EmployeeSkillMapper {

    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<EmployeeSkill> toEntity(List<EmployeeSkillDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<EmployeeSkill> list = new ArrayList<EmployeeSkill>( dtoList.size() );
        for ( EmployeeSkillDTO employeeSkillDTO : dtoList ) {
            list.add( toEntity( employeeSkillDTO ) );
        }

        return list;
    }

    @Override
    public List<EmployeeSkillDTO> toDto(List<EmployeeSkill> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<EmployeeSkillDTO> list = new ArrayList<EmployeeSkillDTO>( entityList.size() );
        for ( EmployeeSkill employeeSkill : entityList ) {
            list.add( toDto( employeeSkill ) );
        }

        return list;
    }

    @Override
    public EmployeeSkillDTO toDto(EmployeeSkill employeeSkill) {
        if ( employeeSkill == null ) {
            return null;
        }

        EmployeeSkillDTO employeeSkillDTO = new EmployeeSkillDTO();

        employeeSkillDTO.setEmployeeUsername( employeeSkillIdEmployeeUsername( employeeSkill ) );
        employeeSkillDTO.setName( employeeSkillIdName( employeeSkill ) );
        employeeSkillDTO.setEmployeeFullname( employeeSkillEmployeeFullname( employeeSkill ) );
        employeeSkillDTO.setLevel( employeeSkill.getLevel() );
        employeeSkillDTO.setTasks( taskSetToTaskDTOSet( employeeSkill.getTasks() ) );

        return employeeSkillDTO;
    }

    @Override
    public EmployeeSkill toEntity(EmployeeSkillDTO employeeSkillDTO) {
        if ( employeeSkillDTO == null ) {
            return null;
        }

        EmployeeSkill employeeSkill = new EmployeeSkill();

        employeeSkill.setId( employeeSkillDTOToEmployeeSkillId( employeeSkillDTO ) );
        employeeSkill.setEmployee( employeeMapper.fromUsername( employeeSkillDTO.getEmployeeUsername() ) );
        employeeSkill.setName( employeeSkillDTO.getName() );
        employeeSkill.setLevel( employeeSkillDTO.getLevel() );
        employeeSkill.setTasks( taskDTOSetToTaskSet( employeeSkillDTO.getTasks() ) );

        return employeeSkill;
    }

    private String employeeSkillIdEmployeeUsername(EmployeeSkill employeeSkill) {
        if ( employeeSkill == null ) {
            return null;
        }
        EmployeeSkillId id = employeeSkill.getId();
        if ( id == null ) {
            return null;
        }
        String employeeUsername = id.getEmployeeUsername();
        if ( employeeUsername == null ) {
            return null;
        }
        return employeeUsername;
    }

    private String employeeSkillIdName(EmployeeSkill employeeSkill) {
        if ( employeeSkill == null ) {
            return null;
        }
        EmployeeSkillId id = employeeSkill.getId();
        if ( id == null ) {
            return null;
        }
        String name = id.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String employeeSkillEmployeeFullname(EmployeeSkill employeeSkill) {
        if ( employeeSkill == null ) {
            return null;
        }
        Employee employee = employeeSkill.getEmployee();
        if ( employee == null ) {
            return null;
        }
        String fullname = employee.getFullname();
        if ( fullname == null ) {
            return null;
        }
        return fullname;
    }

    protected Set<TaskDTO> taskSetToTaskDTOSet(Set<Task> set) {
        if ( set == null ) {
            return null;
        }

        Set<TaskDTO> set1 = new HashSet<TaskDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Task task : set ) {
            set1.add( taskMapper.toDto( task ) );
        }

        return set1;
    }

    protected EmployeeSkillId employeeSkillDTOToEmployeeSkillId(EmployeeSkillDTO employeeSkillDTO) {
        if ( employeeSkillDTO == null ) {
            return null;
        }

        EmployeeSkillId employeeSkillId = new EmployeeSkillId();

        employeeSkillId.setName( employeeSkillDTO.getName() );
        employeeSkillId.setEmployeeUsername( employeeSkillDTO.getEmployeeUsername() );

        return employeeSkillId;
    }

    protected Set<Task> taskDTOSetToTaskSet(Set<TaskDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Task> set1 = new HashSet<Task>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( TaskDTO taskDTO : set ) {
            set1.add( taskMapper.toEntity( taskDTO ) );
        }

        return set1;
    }
}
