package br.com.ruderson.portfolio_api.mappers;

import br.com.ruderson.portfolio_api.dto.category.CategoryDTO;
import br.com.ruderson.portfolio_api.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    //CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO toDto(Category entity);

    Category toEntity(CategoryDTO dto);

}
