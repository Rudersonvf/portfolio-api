package br.com.ruderson.portfolio_api.services.impl;

import br.com.ruderson.portfolio_api.dto.VisitorDto;
import br.com.ruderson.portfolio_api.entities.Visitor;
import br.com.ruderson.portfolio_api.mappers.VisitorMapper;
import br.com.ruderson.portfolio_api.repositories.VisitorRepository;
import br.com.ruderson.portfolio_api.services.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitorServiceImpl implements VisitorService {
    @Autowired
    private VisitorRepository visitorRepository;

    @Autowired
    private VisitorMapper visitorMapper;

    @Override
    public VisitorDto insert(VisitorDto dto) {
        Visitor entity = visitorMapper.toEntity(dto);
        return visitorMapper.toDto(visitorRepository.save(entity));
    }

    public List<VisitorDto> getAll(){
        List<Visitor> result = visitorRepository.findAll();
        return result.stream().map(visitorMapper::toDto).toList();
    }
}
