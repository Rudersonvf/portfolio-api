package br.com.ruderson.portfolio_api.services;

import br.com.ruderson.portfolio_api.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> findAll();

    CategoryDto findById(Long id);

    CategoryDto insert(CategoryDto dto);

    void deleteById(Long id);
}
