package br.com.ruderson.portfolio_api.mappers;

import br.com.ruderson.portfolio_api.dto.skill.SkillDTO;
import br.com.ruderson.portfolio_api.dto.skill.SkillResponse;
import br.com.ruderson.portfolio_api.entities.Skill;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SkillMapper {
    SkillMapper INSTANCE = Mappers.getMapper(SkillMapper.class);

    SkillResponse entityToResponse(Skill entity);

    SkillDTO toDto(Skill entity);

    Skill toEntity(SkillDTO dto);
}
