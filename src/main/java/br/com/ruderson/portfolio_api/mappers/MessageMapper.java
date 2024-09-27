package br.com.ruderson.portfolio_api.mappers;

import br.com.ruderson.portfolio_api.dto.message.MessageDTO;
import br.com.ruderson.portfolio_api.dto.message.MessageSummaryProjection;
import br.com.ruderson.portfolio_api.entities.Message;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    MessageDTO toDto(Message entity);

    Message toEntity(MessageDTO dto);
}
