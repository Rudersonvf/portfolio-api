package br.com.ruderson.portfolio_api.mappers;

import br.com.ruderson.portfolio_api.dto.VisitorDto;
import br.com.ruderson.portfolio_api.entities.Visitor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface VisitorMapper {
    VisitorDto toDto(Visitor entity);

    Visitor toEntity(VisitorDto dto);
}
