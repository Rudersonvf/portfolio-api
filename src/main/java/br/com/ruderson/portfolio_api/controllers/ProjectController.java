package br.com.ruderson.portfolio_api.controllers;

import br.com.ruderson.portfolio_api.dto.ProjectDto;
import br.com.ruderson.portfolio_api.projections.ProjectSummaryProjection;
import br.com.ruderson.portfolio_api.services.impl.ProjectServiceImpl;
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
@RequestMapping("/api/projects")
@Tag(name = "Project")
public class ProjectController {
    @Autowired
    private ProjectServiceImpl projectService;

    @Operation(summary = "Retrieve all projects records", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all projects records"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @GetMapping
    public ResponseEntity<List<ProjectDto>> findAll(){
        return ResponseEntity.ok(projectService.findAll());
    }

    @Operation(summary = "Retrieve all projects records summarized",
            method = "GET",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all projects records summarized"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/summary")
    public ResponseEntity<List<ProjectSummaryProjection>> findAllSummary(){
        return ResponseEntity.ok(projectService.findAllProjectedBy());
    }

    @Operation(summary = "Retrieve a project record by its ID",
            method = "GET",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the project record"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Project record not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> findById(@PathVariable Long id) {
        ProjectDto dto = projectService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Create a new project record",
            method = "POST",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created the project record"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "422", description = "Invalid data"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<ProjectDto> insert(@RequestBody @Valid ProjectDto dto) {
        dto = projectService.insert(dto);
        return ResponseEntity.created(URI.create("/projects/" + dto.getId())).body(dto);
    }

    @Operation(summary = "Update an existing project record by its ID",
            method = "PUT",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created the education record"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Project record not found"),
            @ApiResponse(responseCode = "422", description = "Invalid data"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ProjectDto> update(@PathVariable Long id, @RequestBody @Valid ProjectDto dto) {
        dto = projectService.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Delete a project record by its ID",
            method = "DELETE",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted the project record"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Project record not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        projectService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
