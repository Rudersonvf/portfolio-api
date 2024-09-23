package br.com.ruderson.portfolio_api.mappers;

import br.com.ruderson.portfolio_api.dto.experience.ExperienceDTO;
import br.com.ruderson.portfolio_api.dto.experience.ExperienceResponse;
import br.com.ruderson.portfolio_api.entities.Experience;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ExperienceMapper {
    ExperienceMapper INSTANCE = Mappers.getMapper(ExperienceMapper.class);

    ExperienceResponse responseToDto(Experience entity);

    ExperienceDTO toDto(Experience entity);

    Experience toEntity(ExperienceDTO dto);
}
