package br.com.ruderson.portfolio_api.mappers;

import br.com.ruderson.portfolio_api.dto.EducationDTO;
import br.com.ruderson.portfolio_api.dto.EducationResponse;
import br.com.ruderson.portfolio_api.entities.Education;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EducationMapper {
    EducationResponse responseToDto(Education entity);

    Education toEntity(EducationDTO dto);

    EducationDTO toDto(Education entity);
}
