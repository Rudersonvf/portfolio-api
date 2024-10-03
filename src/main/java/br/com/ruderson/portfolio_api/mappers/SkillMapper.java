package br.com.ruderson.portfolio_api.mappers;

import br.com.ruderson.portfolio_api.dto.SkillDTO;
import br.com.ruderson.portfolio_api.dto.SkillResponse;
import br.com.ruderson.portfolio_api.entities.Skill;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SkillMapper {
    SkillResponse entityToResponse(Skill entity);

    SkillDTO toDto(Skill entity);

    Skill toEntity(SkillDTO dto);
}
