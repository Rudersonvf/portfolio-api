package br.com.ruderson.portfolio_api.services;

import br.com.ruderson.portfolio_api.dto.ExperienceDto;
import br.com.ruderson.portfolio_api.dto.ExperienceResponse;

import java.util.List;

public interface ExperienceService {
    List<ExperienceResponse> findAll();

    ExperienceResponse findById(Long id);

    ExperienceDto insert(ExperienceDto dto);

    ExperienceDto update(Long id, ExperienceDto dto);

    void deleteById(Long id);
}
