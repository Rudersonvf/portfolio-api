package br.com.ruderson.portfolio_api.controllers;

import br.com.ruderson.portfolio_api.dto.VisitorDto;
import br.com.ruderson.portfolio_api.services.impl.VisitorServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/visitors")
public class VisitorController {
    @Autowired
    VisitorServiceImpl visitorService;

    @GetMapping
    public ResponseEntity<List<VisitorDto>> findAll() {
        return ResponseEntity.ok(visitorService.getAll());
    }

    @PostMapping
    public ResponseEntity<VisitorDto> insert(@RequestBody @Valid VisitorDto dto, HttpServletRequest request) {
        dto.setIpAddress(request.getRemoteAddr());
        VisitorDto visitor = visitorService.insert(dto);
        return ResponseEntity.created(URI.create("/experiences/" + visitor.getId())).body(visitor);
    }
}
