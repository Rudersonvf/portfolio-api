package br.com.ruderson.portfolio_api.controllers;

import br.com.ruderson.portfolio_api.dto.MessageDto;
import br.com.ruderson.portfolio_api.dto.MessageStatusDto;
import br.com.ruderson.portfolio_api.projections.MessageSummaryProjection;
import br.com.ruderson.portfolio_api.services.impl.MessageServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    MessageServiceImpl messageService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<MessageSummaryProjection>> findAll() {
        return ResponseEntity.ok(messageService.findAll());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<MessageDto> findById(@PathVariable Long id) {
        MessageDto dto = messageService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<MessageStatusDto> switchToUnread(@PathVariable Long id,
                                                           @RequestBody MessageStatusDto dto) {
        dto = messageService.switchMessageStatus(id, dto);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<MessageDto> insert(@RequestBody @Valid MessageDto dto) {
        dto = messageService.insert(dto);
        return ResponseEntity.created(URI.create("/messages/" + dto.getId())).body(dto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        messageService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
