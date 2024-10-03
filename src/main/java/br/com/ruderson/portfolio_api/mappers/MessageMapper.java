package br.com.ruderson.portfolio_api.mappers;

import br.com.ruderson.portfolio_api.dto.MessageDTO;
import br.com.ruderson.portfolio_api.entities.Message;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MessageMapper {
    MessageDTO toDto(Message entity);

    Message toEntity(MessageDTO dto);
}
