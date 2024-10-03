package br.com.ruderson.portfolio_api.mappers;

import br.com.ruderson.portfolio_api.dto.ExperienceDTO;
import br.com.ruderson.portfolio_api.dto.ExperienceResponse;
import br.com.ruderson.portfolio_api.entities.Experience;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ExperienceMapper {
    ExperienceResponse responseToDto(Experience entity);

    ExperienceDTO toDto(Experience entity);

    Experience toEntity(ExperienceDTO dto);
}
