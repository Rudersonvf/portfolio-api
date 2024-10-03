package br.com.ruderson.portfolio_api.services;

import br.com.ruderson.portfolio_api.dto.ProjectDTO;
import br.com.ruderson.portfolio_api.projections.ProjectDetailsProjection;
import br.com.ruderson.portfolio_api.projections.ProjectSummaryProjection;

import java.util.List;

public interface ProjectService {
    List<ProjectDetailsProjection> findAll();

    List<ProjectSummaryProjection> findAllProjectedBy();

    ProjectDTO findById(Long id);

    ProjectDTO insert(ProjectDTO dto);

    ProjectDTO update(Long id, ProjectDTO dto);

    void deleteById(Long id);
}
