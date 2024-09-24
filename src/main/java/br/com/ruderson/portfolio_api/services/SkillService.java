package br.com.ruderson.portfolio_api.services;

import br.com.ruderson.portfolio_api.dto.skill.SkillDTO;
import br.com.ruderson.portfolio_api.dto.skill.SkillResponse;

import java.util.List;

public interface SkillService {
    List<SkillResponse> findAll();

    SkillResponse findById(Long id);

    SkillDTO insert(SkillDTO dto);

    SkillDTO update(Long id, SkillDTO dto);

    void deleteById(Long id);
}
