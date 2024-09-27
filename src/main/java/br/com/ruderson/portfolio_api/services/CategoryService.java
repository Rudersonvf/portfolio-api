package br.com.ruderson.portfolio_api.services;

import br.com.ruderson.portfolio_api.dto.category.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> findAll();

    CategoryDTO findById(Long id);

    CategoryDTO insert(CategoryDTO dto);

    void deleteById(Long id);
}
