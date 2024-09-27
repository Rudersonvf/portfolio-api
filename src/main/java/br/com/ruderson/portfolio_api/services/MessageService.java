package br.com.ruderson.portfolio_api.services;

import br.com.ruderson.portfolio_api.dto.message.MessageDTO;
import br.com.ruderson.portfolio_api.dto.message.MessageSummaryProjection;

import java.util.List;

public interface MessageService {

    List<MessageSummaryProjection> findAll();

    MessageDTO findById(Long id);

    MessageDTO insert(MessageDTO dto);

    void deleteById(Long id);
}
