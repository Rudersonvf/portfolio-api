package br.com.ruderson.portfolio_api.services.impl;

import br.com.ruderson.portfolio_api.dto.category.CategoryDTO;
import br.com.ruderson.portfolio_api.dto.image.ImageDTO;
import br.com.ruderson.portfolio_api.dto.project.ProjectDTO;
import br.com.ruderson.portfolio_api.dto.project.ProjectDetailsProjection;
import br.com.ruderson.portfolio_api.dto.project.ProjectSummaryProjection;
import br.com.ruderson.portfolio_api.dto.skill.SkillDTO;
import br.com.ruderson.portfolio_api.entities.Category;
import br.com.ruderson.portfolio_api.entities.Image;
import br.com.ruderson.portfolio_api.entities.Project;
import br.com.ruderson.portfolio_api.entities.Skill;
import br.com.ruderson.portfolio_api.mappers.ProjectMapper;
import br.com.ruderson.portfolio_api.repositories.CategoryRepository;
import br.com.ruderson.portfolio_api.repositories.ImageRepository;
import br.com.ruderson.portfolio_api.repositories.ProjectRepository;
import br.com.ruderson.portfolio_api.repositories.SkillRepository;
import br.com.ruderson.portfolio_api.services.ProjectService;
import br.com.ruderson.portfolio_api.services.exceptions.DatabaseException;
import br.com.ruderson.portfolio_api.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final String PROJ_NOT_FOUND_ERROR = "Project not found with id ";

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    ProjectMapper projectMapper;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    ImageRepository imageRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ProjectDetailsProjection> findAll() {
        return projectRepository.findAllProjectDetails();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectSummaryProjection> findAllProjectedBy() {
        return projectRepository.findAllProjectedBy();
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
        Project entity = new Project();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setRepositoryUrl(dto.getRepositoryUrl());
        entity.setLiveUrl(dto.getLiveUrl());
        entity.setCreatedAt(Instant.now());

       Set<Image> images = new HashSet<>();
       for(ImageDTO imgDto : dto.getImages()) {
           Image imgEntity = new Image();
           imgEntity.setUrl(imgDto.getUrl());
           imgEntity.setProject(entity);
           images.add(imgEntity);
       }
        entity.setImages(images);

        List<Long> categoriesIds = dto.getCategories().stream().map(CategoryDTO::getId).toList();
        List<Long> skillsIds = dto.getSkills().stream().map(SkillDTO::getId).toList();

        Set<Category> categories = new HashSet<>(categoryRepository.findAllById(categoriesIds));
        Set<Skill> technologies = new HashSet<>(skillRepository.findAllById(skillsIds));

        entity.setCategories(categories);
        entity.setSkills(technologies);

        entity = projectRepository.save(entity);

        return projectMapper.toDto(entity);
    }

    @Override
    @Transactional
    public ProjectDTO update(Long id, ProjectDTO dto) {
        try {
            Project entity = projectRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(PROJ_NOT_FOUND_ERROR + id));

            entity.setTitle(dto.getTitle());
            entity.setDescription(dto.getDescription());
            entity.setRepositoryUrl(dto.getRepositoryUrl());
            entity.setLiveUrl(dto.getLiveUrl());

            entity.getImages().clear();

            Set<Image> newImages = new HashSet<>();
            for (ImageDTO imgDto : dto.getImages()) {
                Image imgEntity = new Image();
                imgEntity.setUrl(imgDto.getUrl());
                imgEntity.setProject(entity);
                newImages.add(imgEntity);
            }

            entity.getImages().addAll(newImages);

            List<Long> categoriesIds = dto.getCategories().stream().map(CategoryDTO::getId).toList();
            List<Long> skillsIds = dto.getSkills().stream().map(SkillDTO::getId).toList();

            Set<Category> categories = new HashSet<>(categoryRepository.findAllById(categoriesIds));
            Set<Skill> technologies = new HashSet<>(skillRepository.findAllById(skillsIds));

            entity.setCategories(categories);
            entity.setSkills(technologies);

            entity = projectRepository.save(entity);

            return projectMapper.toDto(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(PROJ_NOT_FOUND_ERROR + id);
        }
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteById(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new ResourceNotFoundException(PROJ_NOT_FOUND_ERROR + id);
        }

        try {
            projectRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Data integrity violation");
        }
    }
}
