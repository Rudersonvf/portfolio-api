package br.com.ruderson.portfolio_api.mappers;

import br.com.ruderson.portfolio_api.dto.education.EducationDTO;
import br.com.ruderson.portfolio_api.dto.education.EducationResponse;
import br.com.ruderson.portfolio_api.entities.Education;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EducationMapper {

    EducationResponse responseToDto(Education entity);

    Education toEntity(EducationDTO dto);

    EducationDTO toDto(Education entity);
}
