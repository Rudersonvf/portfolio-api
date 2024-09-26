package br.com.ruderson.portfolio_api.services;

import br.com.ruderson.portfolio_api.dto.experience.ExperienceDTO;
import br.com.ruderson.portfolio_api.dto.experience.ExperienceResponse;

import java.util.List;

public interface ExperienceService {
    List<ExperienceResponse> findAll();

    ExperienceResponse findById(Long id);

    ExperienceDTO insert(ExperienceDTO dto);

    ExperienceDTO update(Long id, ExperienceDTO dto);

    void deleteById(Long id);
}