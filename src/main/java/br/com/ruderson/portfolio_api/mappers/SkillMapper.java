package br.com.ruderson.portfolio_api.mappers;

import br.com.ruderson.portfolio_api.dto.SkillDto;
import br.com.ruderson.portfolio_api.dto.SkillResponse;
import br.com.ruderson.portfolio_api.entities.Skill;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SkillMapper {
    SkillResponse entityToResponse(Skill entity);

    SkillDto toDto(Skill entity);

    Skill toEntity(SkillDto dto);
}
