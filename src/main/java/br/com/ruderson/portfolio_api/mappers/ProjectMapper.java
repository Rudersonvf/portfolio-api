package br.com.ruderson.portfolio_api.mappers;

import br.com.ruderson.portfolio_api.dto.project.ProjectDTO;
import br.com.ruderson.portfolio_api.entities.Project;
import org.mapstruct.*;


@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectDTO toDto(Project entity);

    Project toEntity(ProjectDTO dto);
}

