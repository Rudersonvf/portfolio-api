package br.com.ruderson.portfolio_api.services.impl;

import br.com.ruderson.portfolio_api.dto.MessageDTO;
import br.com.ruderson.portfolio_api.projections.MessageSummaryProjection;
import br.com.ruderson.portfolio_api.entities.Message;
import br.com.ruderson.portfolio_api.mappers.MessageMapper;
import br.com.ruderson.portfolio_api.repositories.MessageRepository;
import br.com.ruderson.portfolio_api.services.MessageService;
import br.com.ruderson.portfolio_api.services.exceptions.DatabaseException;
import br.com.ruderson.portfolio_api.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    private final String MSG_NOT_FOUND_ERROR = "Message not found with id ";

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MessageMapper messageMapper;

    @Override
    @Transactional(readOnly = true)
    public List<MessageSummaryProjection> findAll() {
        return messageRepository.findAllProjectedBy();
    }

    @Override
    @Transactional(readOnly = true)
    public MessageDTO findById(Long id) {
        Message result = messageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND_ERROR + id));
        return messageMapper.toDto(result);
    }

    @Override
    @Transactional
    public MessageDTO insert(MessageDTO dto) {
        Message entity = messageMapper.toEntity(dto);
        return messageMapper.toDto(messageRepository.save(entity));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteById(Long id) {
        if (!messageRepository.existsById(id)) {
            throw new ResourceNotFoundException(MSG_NOT_FOUND_ERROR + id);
        }

        try {
            messageRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Data integrity violation");
        }
    }
}
