package br.com.ruderson.portfolio_api.mappers;

import br.com.ruderson.portfolio_api.dto.project.ProjectDTO;
import br.com.ruderson.portfolio_api.dto.category.CategoryDTO;
import br.com.ruderson.portfolio_api.dto.skill.SkillDTO;
import br.com.ruderson.portfolio_api.entities.Project;
import br.com.ruderson.portfolio_api.entities.Category;
import br.com.ruderson.portfolio_api.entities.Skill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    @Mappings({
            @Mapping(target = "categories", source = "categories"),
            @Mapping(target = "skills", source = "skills")
    })
    ProjectDTO toDto(Project entity);

    CategoryDTO toCategoryDto(Category category);
    SkillDTO toSkillDto(Skill skill);

    List<CategoryDTO> toCategoryDtos(List<Category> categories);
    List<SkillDTO> toSkillDtos(List<Skill> skills);

    Project toEntity(ProjectDTO dto);
}
