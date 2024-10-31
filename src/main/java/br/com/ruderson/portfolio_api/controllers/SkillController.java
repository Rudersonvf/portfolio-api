package br.com.ruderson.portfolio_api.controllers;

import br.com.ruderson.portfolio_api.dto.SkillDto;
import br.com.ruderson.portfolio_api.dto.SkillResponse;
import br.com.ruderson.portfolio_api.services.impl.SkillServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/skills")
@Tag(name = "Skill ")
public class SkillController {
    @Autowired
    private SkillServiceImpl skillService;

    @Operation(summary = "Retrieve all skills records", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all skills records"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @GetMapping
    public ResponseEntity<List<SkillResponse>> findAll() {
        return ResponseEntity.ok(skillService.findAll());
    }

    @Operation(summary = "Retrieve a specific skill record by its ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the skill record"),
            @ApiResponse(responseCode = "404", description = "Skill record not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<SkillResponse> findById(@PathVariable Long id) {
        SkillResponse dto = skillService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Create a new skill record", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created the skill record"),
            @ApiResponse(responseCode = "422", description = "Invalid data"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<SkillDto> insert(@RequestBody @Valid SkillDto dto) {
        SkillDto skill = skillService.insert(dto);
        return ResponseEntity.created(URI.create("/experiences/" + skill.getId())).body(skill);
    }

    @Operation(summary = "Update a skill record", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the skill record"),
            @ApiResponse(responseCode = "404", description = "Skill record not found"),
            @ApiResponse(responseCode = "422", description = "Invalid data"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<SkillDto> update(@PathVariable Long id, @RequestBody @Valid SkillDto dto) {
        dto = skillService.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Delete a skill record by its ID", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted the skill record"),
            @ApiResponse(responseCode = "400", description = "Data integrity violation"),
            @ApiResponse(responseCode = "404", description = "Skill record not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        skillService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
