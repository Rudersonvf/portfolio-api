package br.com.ruderson.portfolio_api.services;

import br.com.ruderson.portfolio_api.dto.MessageDTO;
import br.com.ruderson.portfolio_api.dto.MessageStatusDto;
import br.com.ruderson.portfolio_api.projections.MessageSummaryProjection;

import java.util.List;

public interface MessageService {

    List<MessageSummaryProjection> findAll();

    MessageDTO findById(Long id);

    MessageDTO insert(MessageDTO dto);

    MessageStatusDto switchMessageStatus(Long id, MessageStatusDto dto);

    void deleteById(Long id);
}
