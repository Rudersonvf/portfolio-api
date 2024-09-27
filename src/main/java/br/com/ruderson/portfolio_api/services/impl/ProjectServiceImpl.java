package br.com.ruderson.portfolio_api.services.impl;

import br.com.ruderson.portfolio_api.dto.project.ProjectDTO;
import br.com.ruderson.portfolio_api.dto.project.ProjectDetailsProjection;
import br.com.ruderson.portfolio_api.dto.project.ProjectSummaryProjection;
import br.com.ruderson.portfolio_api.entities.Project;
import br.com.ruderson.portfolio_api.mappers.ProjectMapper;
import br.com.ruderson.portfolio_api.repositories.ProjectRepository;
import br.com.ruderson.portfolio_api.services.ProjectService;
import br.com.ruderson.portfolio_api.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final String PROJ_NOT_FOUND_ERROR = "Project not found with id ";

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    ProjectMapper projectMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ProjectDetailsProjection> findAll() {
        return projectRepository.findAllProjectDetails();
    }

    @Override
    public List<ProjectSummaryProjection> findAllProjectedBy() {
        return List.of();
    }

    @Override
    @Transactional(readOnly = true)
    public ProjectDTO findById(Long id) {
        Project result = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(PROJ_NOT_FOUND_ERROR + id));
        return projectMapper.toDto(result);
    }

    @Override
    @Transactional
    public ProjectDTO insert(ProjectDTO dto) {
        Project entity = projectMapper.toEntity(dto);
        entity = projectRepository.save(entity);
        return projectMapper.toDto(entity);
    }

    @Override
    @Transactional
    public ProjectDTO update(Long id, ProjectDTO dto) {
//        try {
//            Project entity = projectRepository.getReferenceById(id);
//            entity.setTitle(dto.getTitle());
//            entity.setDescription(dto.getDescription());
//            entity.setRepositoryUrl(dto.getRepositoryUrl());
//            entity.setLiveUrl(dto.getLiveUrl());
//
//        }

        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
