package br.com.ruderson.portfolio_api.controllers;

import br.com.ruderson.portfolio_api.dto.ExperienceDto;
import br.com.ruderson.portfolio_api.dto.ExperienceResponse;
import br.com.ruderson.portfolio_api.services.impl.ExperienceServiceImpl;
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
@RequestMapping("/api/experiences")
@Tag(name = "Experience")
public class ExperienceController {

    @Autowired
    private ExperienceServiceImpl experienceService;

    @Operation(summary = "Retrieve all experiences records", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all experiences records"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @GetMapping
    public ResponseEntity<List<ExperienceResponse>> getAll() {
        return ResponseEntity.ok(experienceService.findAll());
    }

    @Operation(summary = "Retrieve a specific experience record by its ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the experience record"),
            @ApiResponse(responseCode = "404", description = "Experience record not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<ExperienceResponse> findById(@PathVariable Long id) {
        ExperienceResponse dto = experienceService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Create a new experience record",
            method = "POST",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created the experience record"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "422", description = "Invalid data"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<ExperienceDto> insert(@RequestBody @Valid ExperienceDto dto) {
        ExperienceDto experience = experienceService.insert(dto);
        return ResponseEntity.created(URI.create("/experiences/" + experience.getId())).body(experience);
    }

    @Operation(summary = "Update an existing experience record by its ID",
            method = "PUT",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the experience record"),
            @ApiResponse(responseCode = "404", description = "Experience record not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "422", description = "Invalid data"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ExperienceDto> update(@PathVariable Long id, @RequestBody @Valid ExperienceDto dto) {
        dto = experienceService.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Delete an existing experience record by its ID",
            method = "DELETE",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted the experience record"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Experience record not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
            @ApiResponse(responseCode = "404", description = "Experience record not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        experienceService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
