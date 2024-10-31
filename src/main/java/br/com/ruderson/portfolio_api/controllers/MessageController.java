package br.com.ruderson.portfolio_api.controllers;

import br.com.ruderson.portfolio_api.dto.MessageDto;
import br.com.ruderson.portfolio_api.dto.MessageStatusDto;
import br.com.ruderson.portfolio_api.projections.MessageSummaryProjection;
import br.com.ruderson.portfolio_api.services.impl.MessageServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
@Tag(name = "Message")
public class MessageController {
    @Autowired
    MessageServiceImpl messageService;

    @Operation(summary = "Retrieve all educations records",
            method = "GET",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of messages"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<MessageSummaryProjection>> findAll() {
        return ResponseEntity.ok(messageService.findAll());
    }

    @Operation(summary = "Retrieve a specific education record by its ID ans mark as a read",
            method = "GET",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the education record"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Education record not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),

    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<MessageDto> findById(@PathVariable Long id) {
        MessageDto dto = messageService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Mark a specific education record as read or unread",
            method = "PUT",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the education record status"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Education record not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<MessageStatusDto> switchToUnread(@PathVariable Long id,
                                                           @RequestBody MessageStatusDto dto) {
        dto = messageService.switchMessageStatus(id, dto);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Create a new message record", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created the message record"),
            @ApiResponse(responseCode = "422", description = "Invalid data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<MessageDto> insert(@RequestBody @Valid MessageDto dto) {
        dto = messageService.insert(dto);
        return ResponseEntity.created(URI.create("/messages/" + dto.getId())).body(dto);
    }

    @Operation(summary = "Delete a message record by its ID",
            method = "DELETE",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted the message record"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Message record not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        messageService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
