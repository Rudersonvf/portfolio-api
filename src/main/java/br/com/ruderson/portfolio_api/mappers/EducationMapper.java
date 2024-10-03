package br.com.ruderson.portfolio_api.mappers;

import br.com.ruderson.portfolio_api.dto.EducationDto;
import br.com.ruderson.portfolio_api.dto.EducationResponse;
import br.com.ruderson.portfolio_api.entities.Education;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EducationMapper {
    EducationResponse responseToDto(Education entity);

    Education toEntity(EducationDto dto);

    EducationDto toDto(Education entity);
}
