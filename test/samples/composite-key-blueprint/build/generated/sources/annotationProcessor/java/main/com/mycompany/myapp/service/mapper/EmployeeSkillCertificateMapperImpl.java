package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.CertificateType;
import com.mycompany.myapp.domain.EmployeeSkillCertificate;
import com.mycompany.myapp.domain.EmployeeSkillCertificateId;
import com.mycompany.myapp.service.dto.EmployeeSkillCertificateDTO;
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
public class EmployeeSkillCertificateMapperImpl extends EmployeeSkillCertificateMapper {

    @Autowired
    private CertificateTypeMapper certificateTypeMapper;

    @Override
    public List<EmployeeSkillCertificate> toEntity(List<EmployeeSkillCertificateDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<EmployeeSkillCertificate> list = new ArrayList<EmployeeSkillCertificate>( dtoList.size() );
        for ( EmployeeSkillCertificateDTO employeeSkillCertificateDTO : dtoList ) {
            list.add( toEntity( employeeSkillCertificateDTO ) );
        }

        return list;
    }

    @Override
    public List<EmployeeSkillCertificateDTO> toDto(List<EmployeeSkillCertificate> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<EmployeeSkillCertificateDTO> list = new ArrayList<EmployeeSkillCertificateDTO>( entityList.size() );
        for ( EmployeeSkillCertificate employeeSkillCertificate : entityList ) {
            list.add( toDto( employeeSkillCertificate ) );
        }

        return list;
    }

    @Override
    public EmployeeSkillCertificateDTO toDto(EmployeeSkillCertificate employeeSkillCertificate) {
        if ( employeeSkillCertificate == null ) {
            return null;
        }

        EmployeeSkillCertificateDTO employeeSkillCertificateDTO = new EmployeeSkillCertificateDTO();

        employeeSkillCertificateDTO.setSkillName( employeeSkillCertificateIdSkillName( employeeSkillCertificate ) );
        employeeSkillCertificateDTO.setTypeName( employeeSkillCertificateTypeName( employeeSkillCertificate ) );
        employeeSkillCertificateDTO.setTypeId( employeeSkillCertificateIdTypeId( employeeSkillCertificate ) );
        employeeSkillCertificateDTO.setSkillEmployeeUsername( employeeSkillCertificateIdSkillEmployeeUsername( employeeSkillCertificate ) );
        employeeSkillCertificateDTO.setGrade( employeeSkillCertificate.getGrade() );
        employeeSkillCertificateDTO.setDate( employeeSkillCertificate.getDate() );

        return employeeSkillCertificateDTO;
    }

    @Override
    public EmployeeSkillCertificate toEntity(EmployeeSkillCertificateDTO employeeSkillCertificateDTO) {
        if ( employeeSkillCertificateDTO == null ) {
            return null;
        }

        EmployeeSkillCertificate employeeSkillCertificate = new EmployeeSkillCertificate();

        employeeSkillCertificate.setId( employeeSkillCertificateDTOToEmployeeSkillCertificateId( employeeSkillCertificateDTO ) );
        employeeSkillCertificate.setType( certificateTypeMapper.fromId( employeeSkillCertificateDTO.getTypeId() ) );
        employeeSkillCertificate.setGrade( employeeSkillCertificateDTO.getGrade() );
        employeeSkillCertificate.setDate( employeeSkillCertificateDTO.getDate() );

        employeeSkillCertificate.setSkill( this.employeeSkillMapper.fromId(new com.mycompany.myapp.domain.EmployeeSkillId(employeeSkillCertificateDTO.getSkillName(), employeeSkillCertificateDTO.getSkillEmployeeUsername())) );

        return employeeSkillCertificate;
    }

    private String employeeSkillCertificateIdSkillName(EmployeeSkillCertificate employeeSkillCertificate) {
        if ( employeeSkillCertificate == null ) {
            return null;
        }
        EmployeeSkillCertificateId id = employeeSkillCertificate.getId();
        if ( id == null ) {
            return null;
        }
        String skillName = id.getSkillName();
        if ( skillName == null ) {
            return null;
        }
        return skillName;
    }

    private String employeeSkillCertificateTypeName(EmployeeSkillCertificate employeeSkillCertificate) {
        if ( employeeSkillCertificate == null ) {
            return null;
        }
        CertificateType type = employeeSkillCertificate.getType();
        if ( type == null ) {
            return null;
        }
        String name = type.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private Long employeeSkillCertificateIdTypeId(EmployeeSkillCertificate employeeSkillCertificate) {
        if ( employeeSkillCertificate == null ) {
            return null;
        }
        EmployeeSkillCertificateId id = employeeSkillCertificate.getId();
        if ( id == null ) {
            return null;
        }
        Long typeId = id.getTypeId();
        if ( typeId == null ) {
            return null;
        }
        return typeId;
    }

    private String employeeSkillCertificateIdSkillEmployeeUsername(EmployeeSkillCertificate employeeSkillCertificate) {
        if ( employeeSkillCertificate == null ) {
            return null;
        }
        EmployeeSkillCertificateId id = employeeSkillCertificate.getId();
        if ( id == null ) {
            return null;
        }
        String skillEmployeeUsername = id.getSkillEmployeeUsername();
        if ( skillEmployeeUsername == null ) {
            return null;
        }
        return skillEmployeeUsername;
    }

    protected EmployeeSkillCertificateId employeeSkillCertificateDTOToEmployeeSkillCertificateId(EmployeeSkillCertificateDTO employeeSkillCertificateDTO) {
        if ( employeeSkillCertificateDTO == null ) {
            return null;
        }

        EmployeeSkillCertificateId employeeSkillCertificateId = new EmployeeSkillCertificateId();

        employeeSkillCertificateId.setSkillName( employeeSkillCertificateDTO.getSkillName() );
        employeeSkillCertificateId.setTypeId( employeeSkillCertificateDTO.getTypeId() );
        employeeSkillCertificateId.setSkillEmployeeUsername( employeeSkillCertificateDTO.getSkillEmployeeUsername() );

        return employeeSkillCertificateId;
    }
}
