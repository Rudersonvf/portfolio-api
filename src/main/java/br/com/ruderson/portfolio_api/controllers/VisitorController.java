package br.com.ruderson.portfolio_api.controllers;

import br.com.ruderson.portfolio_api.dto.VisitorDto;
import br.com.ruderson.portfolio_api.services.impl.VisitorServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/visitors")
@Tag(name = "Visitor")
@RestControllerAdvice(basePackageClasses = VisitorController.class)
public class VisitorController {
    
    @Autowired
    VisitorServiceImpl visitorService;

    @Operation(summary = "Create a new visitor record", method = "POST")
    @ApiResponses(value={
            @ApiResponse(responseCode = "201", description = "Successfully created the visitor record"),
            @ApiResponse(responseCode = "422", description = "Invalid data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<VisitorDto> insert(@RequestBody @Valid VisitorDto dto, HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        
        if (ipAddress == null || ipAddress.isEmpty()) {
            ipAddress = request.getHeader("X-Real-IP");
        }
        
        if (ipAddress == null || ipAddress.isEmpty()) {
            ipAddress = request.getRemoteAddr();
        }

        dto.setIpAddress(ipAddress);
        
        VisitorDto visitor = visitorService.insert(dto);
        return ResponseEntity.created(URI.create("/api/visitors/" + visitor.getId())).body(visitor);
    }
}
