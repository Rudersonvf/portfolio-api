package br.com.ruderson.portfolio_api.mappers;

import br.com.ruderson.portfolio_api.dto.CategoryDTO;
import br.com.ruderson.portfolio_api.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {
    CategoryDTO toDto(Category entity);

    Category toEntity(CategoryDTO dto);

}
