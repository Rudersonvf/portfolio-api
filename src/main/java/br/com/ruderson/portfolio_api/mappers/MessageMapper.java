package br.com.ruderson.portfolio_api.mappers;

import br.com.ruderson.portfolio_api.dto.MessageDto;
import br.com.ruderson.portfolio_api.dto.MessageStatusDto;
import br.com.ruderson.portfolio_api.entities.Message;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MessageMapper {
    MessageDto toDto(Message entity);

    Message toEntity(MessageDto dto);

    MessageStatusDto toUnreadDto(Message entity);
}
