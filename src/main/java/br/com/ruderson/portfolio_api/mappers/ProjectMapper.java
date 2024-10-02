package br.com.ruderson.portfolio_api.mappers;

import br.com.ruderson.portfolio_api.dto.project.ProjectDTO;
import br.com.ruderson.portfolio_api.entities.Project;
import org.mapstruct.*;


@Mapper(componentModel = "spring")
public interface ProjectMapper {

    @Mapping(source = "images", target = "images")
    ProjectDTO toDto(Project entity);

    @Mapping(source = "images", target = "images")
    Project toEntity(ProjectDTO dto);
}

