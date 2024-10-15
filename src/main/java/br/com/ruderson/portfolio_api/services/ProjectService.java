package br.com.ruderson.portfolio_api.services;

import br.com.ruderson.portfolio_api.dto.ProjectDto;
import br.com.ruderson.portfolio_api.projections.ProjectSummaryProjection;

import java.util.List;

public interface ProjectService {
    List<ProjectDto> findAll();

    List<ProjectSummaryProjection> findAllProjectedBy();

    ProjectDto findById(Long id);

    ProjectDto insert(ProjectDto dto);

    ProjectDto update(Long id, ProjectDto dto);

    void deleteById(Long id);
}
