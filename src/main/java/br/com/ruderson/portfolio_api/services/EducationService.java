package br.com.ruderson.portfolio_api.services;

import br.com.ruderson.portfolio_api.dto.education.EducationDTO;
import br.com.ruderson.portfolio_api.dto.education.EducationResponse;

import java.util.List;

public interface EducationService {
    List<EducationResponse> findAll();

    EducationResponse findById(Long id);

    EducationDTO insert(EducationDTO dto);

    EducationDTO update(Long id, EducationDTO dto);

    void deleteById(Long id);
}
