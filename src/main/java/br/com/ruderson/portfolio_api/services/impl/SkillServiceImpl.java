package br.com.ruderson.portfolio_api.services.impl;

import br.com.ruderson.portfolio_api.dto.SkillDTO;
import br.com.ruderson.portfolio_api.dto.SkillResponse;
import br.com.ruderson.portfolio_api.entities.Skill;
import br.com.ruderson.portfolio_api.mappers.SkillMapper;
import br.com.ruderson.portfolio_api.repositories.SkillRepository;
import br.com.ruderson.portfolio_api.services.SkillService;
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
public class SkillServiceImpl implements SkillService {
    private final String SKILL_NOT_FOUND_ERROR = "Skill not found with id ";

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private SkillMapper skillMapper;

    @Override
    @Transactional(readOnly = true)
    public List<SkillResponse> findAll() {
        List<Skill> result = skillRepository.findAll();
        return result.stream().map(skillMapper::entityToResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public SkillResponse findById(Long id) {
        Skill result = skillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(SKILL_NOT_FOUND_ERROR + id));
        return skillMapper.entityToResponse(result);
    }

    @Override
    @Transactional
    public SkillDTO insert(SkillDTO dto) {
        Skill entity = skillMapper.toEntity(dto);
        return skillMapper.toDto(skillRepository.save(entity));
    }

    @Override
    @Transactional
    public SkillDTO update(Long id, SkillDTO dto) {
        try {
            Skill entity = skillRepository.getReferenceById(id);
            entity.setName(dto.getName());
            entity.setIconUrl(dto.getIconUrl());
            entity.setDocUrl(dto.getDocUrl());
            entity.setLevel(dto.getLevel());
            entity = skillRepository.save(entity);
            return skillMapper.toDto(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(SKILL_NOT_FOUND_ERROR + id);
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteById(Long id) {
        if (!skillRepository.existsById(id)) {
            throw new ResourceNotFoundException(SKILL_NOT_FOUND_ERROR + id);
        }

        try {
            skillRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Data integrity violation");
        }
    }
}
