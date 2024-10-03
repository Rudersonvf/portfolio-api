package br.com.ruderson.portfolio_api.services;

import br.com.ruderson.portfolio_api.dto.EducationDto;
import br.com.ruderson.portfolio_api.dto.EducationResponse;

import java.util.List;

public interface EducationService {
    List<EducationResponse> findAll();

    EducationResponse findById(Long id);

    EducationDto insert(EducationDto dto);

    EducationDto update(Long id, EducationDto dto);

    void deleteById(Long id);
}
