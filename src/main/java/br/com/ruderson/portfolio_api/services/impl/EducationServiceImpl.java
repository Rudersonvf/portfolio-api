package br.com.ruderson.portfolio_api.services.impl;

import br.com.ruderson.portfolio_api.dto.education.EducationDTO;
import br.com.ruderson.portfolio_api.dto.education.EducationResponse;
import br.com.ruderson.portfolio_api.entities.Education;
import br.com.ruderson.portfolio_api.mappers.EducationMapper;
import br.com.ruderson.portfolio_api.repositories.EducationRepository;
import br.com.ruderson.portfolio_api.services.EducationService;
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
public class EducationServiceImpl implements EducationService {
    private final String EDU_NOT_FOUND_ERROR = "Education not found with id ";

    @Autowired
    private EducationMapper educationMapper;

    @Autowired
    private EducationRepository educationRepository;

    @Override
    @Transactional(readOnly = true)
    public List<EducationResponse> findAll() {
        List<Education> result = educationRepository.findAll();
        return result.stream().map(educationMapper::responseToDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public EducationResponse findById(Long id) {
        Education result = educationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(EDU_NOT_FOUND_ERROR + id));
        return educationMapper.responseToDto(result);
    }

    @Override
    @Transactional
    public EducationDTO insert(EducationDTO dto) {
        Education entity = educationMapper.toEntity(dto);
        return educationMapper.toDto(educationRepository.save(entity));
    }

    @Override
    @Transactional
    public EducationDTO update(Long id, EducationDTO dto) {
        try {
            Education entity = educationRepository.getReferenceById(id);
            entity.setCourseName(dto.getCourseName());
            entity.setInstitution(dto.getInstitution());
            entity.setDescription(dto.getDescription());
            entity.setWorkload(dto.getWorkload());
            entity.setCertificateUrl(dto.getCertificateUrl());
            entity.setStartDate(dto.getStartDate());
            entity.setEndDate(dto.getEndDate());
            entity = educationRepository.save(entity);
            return educationMapper.toDto(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(EDU_NOT_FOUND_ERROR + id);
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteById(Long id) {
        if (!educationRepository.existsById(id)) {
            throw new ResourceNotFoundException(EDU_NOT_FOUND_ERROR + id);
        }

        try {
            educationRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Data integrity violation");
        }
    }
}
