package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.CertificateType;
import com.mycompany.myapp.service.dto.CertificateTypeDTO;
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
public class CertificateTypeMapperImpl implements CertificateTypeMapper {

    @Override
    public CertificateTypeDTO toDto(CertificateType entity) {
        if ( entity == null ) {
            return null;
        }

        CertificateTypeDTO certificateTypeDTO = new CertificateTypeDTO();

        certificateTypeDTO.setId( entity.getId() );
        certificateTypeDTO.setName( entity.getName() );

        return certificateTypeDTO;
    }

    @Override
    public List<CertificateType> toEntity(List<CertificateTypeDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<CertificateType> list = new ArrayList<CertificateType>( dtoList.size() );
        for ( CertificateTypeDTO certificateTypeDTO : dtoList ) {
            list.add( toEntity( certificateTypeDTO ) );
        }

        return list;
    }

    @Override
    public List<CertificateTypeDTO> toDto(List<CertificateType> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<CertificateTypeDTO> list = new ArrayList<CertificateTypeDTO>( entityList.size() );
        for ( CertificateType certificateType : entityList ) {
            list.add( toDto( certificateType ) );
        }

        return list;
    }

    @Override
    public CertificateType toEntity(CertificateTypeDTO certificateTypeDTO) {
        if ( certificateTypeDTO == null ) {
            return null;
        }

        CertificateType certificateType = new CertificateType();

        certificateType.setId( certificateTypeDTO.getId() );
        certificateType.setName( certificateTypeDTO.getName() );

        return certificateType;
    }
}
