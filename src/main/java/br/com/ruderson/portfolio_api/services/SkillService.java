package br.com.ruderson.portfolio_api.services;

import br.com.ruderson.portfolio_api.dto.SkillDto;
import br.com.ruderson.portfolio_api.dto.SkillResponse;

import java.util.List;

public interface SkillService {
    List<SkillResponse> findAll();

    SkillResponse findById(Long id);

    SkillDto insert(SkillDto dto);

    SkillDto update(Long id, SkillDto dto);

    void deleteById(Long id);
}
