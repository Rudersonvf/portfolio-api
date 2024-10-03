package br.com.ruderson.portfolio_api.services.impl;

import br.com.ruderson.portfolio_api.dto.ExperienceDto;
import br.com.ruderson.portfolio_api.dto.ExperienceResponse;
import br.com.ruderson.portfolio_api.entities.Experience;
import br.com.ruderson.portfolio_api.mappers.ExperienceMapper;
import br.com.ruderson.portfolio_api.repositories.ExperienceRepository;
import br.com.ruderson.portfolio_api.services.ExperienceService;
import br.com.ruderson.portfolio_api.services.exceptions.DatabaseException;
import br.com.ruderson.portfolio_api.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExperienceServiceImpl implements ExperienceService {
    private final String EXP_NOT_FOUND_ERROR = "Experience not found with id ";

    @Autowired
    private ExperienceRepository experienceRepository;

    @Autowired
    private ExperienceMapper experienceMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ExperienceResponse> findAll() {
        List<Experience> result = experienceRepository.findAll();
        return result.stream().map(experienceMapper::responseToDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ExperienceResponse findById(Long id) {
        Experience result = experienceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(EXP_NOT_FOUND_ERROR + id));
        return experienceMapper.responseToDto(result);
    }

    @Override
    @Transactional
    public ExperienceDto insert(ExperienceDto dto) {
        Experience entity = experienceMapper.toEntity(dto);
        return experienceMapper.toDto(experienceRepository.save(entity));
    }

    @Override
    @Transactional
    public ExperienceDto update(Long id, ExperienceDto dto) {
        try {
            Experience entity = experienceRepository.getReferenceById(id);
            entity.setPosition(dto.getPosition());
            entity.setCompany(dto.getCompany());
            entity.setDescription(dto.getDescription());
            entity.setStartDate(dto.getStartDate());
            entity.setEndDate(dto.getEndDate());
            entity = experienceRepository.save(entity);
            return experienceMapper.toDto(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(EXP_NOT_FOUND_ERROR + id);
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteById(Long id) {
        if (!experienceRepository.existsById(id)) {
            throw new ResourceNotFoundException(EXP_NOT_FOUND_ERROR + id);
        }

        try {
            experienceRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Data integrity violation");
        }
    }
}

