package br.com.ruderson.portfolio_api.controllers;

import br.com.ruderson.portfolio_api.dto.EducationDto;
import br.com.ruderson.portfolio_api.dto.EducationResponse;
import br.com.ruderson.portfolio_api.services.impl.EducationServiceImpl;
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
@RequestMapping("/api/educations")
@Tag(name = "Education")
public class EducationController {
    @Autowired
    private EducationServiceImpl educationService;

    @Operation(summary = "Retrieve all educations records", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of educations"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @GetMapping
    public ResponseEntity<List<EducationResponse>> findAll() {
        return ResponseEntity.ok(educationService.findAll());
    }

    @Operation(summary = "Retrieve a specific education record by its ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the education record"),
            @ApiResponse(responseCode = "404", description = "Education record not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<EducationResponse> findById(@PathVariable Long id) {
        EducationResponse dto = educationService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Create a new education record",
            method = "POST",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created the education record"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "422", description = "Invalid data"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<EducationDto> insert(@RequestBody @Valid EducationDto dto) {
        EducationDto education = educationService.insert(dto);
        return ResponseEntity.created(URI.create("/educations/" + education.getId())).body(education);
    }

    @Operation(summary = "Update a education record by its ID",
            method = "PUT",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the education record"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Education record not found"),
            @ApiResponse(responseCode = "422", description = "Invalid data"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<EducationDto> update(@PathVariable Long id, @RequestBody @Valid EducationDto dto) {
        dto = educationService.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Delete a education record by its ID",
            method = "DELETE",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted the skill record"),
            @ApiResponse(responseCode = "400", description = "Data integrity violation"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Skill record not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        educationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
