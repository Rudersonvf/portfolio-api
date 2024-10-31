package br.com.ruderson.portfolio_api.controllers;

import br.com.ruderson.portfolio_api.dto.CategoryDto;
import br.com.ruderson.portfolio_api.services.impl.CategoryServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
@RequestMapping("/api/categories")
@Tag(name = "Category")
public class CategoryController {
    @Autowired
    private CategoryServiceImpl categoryService;

    @Operation(summary = "Retrieve all categories records", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all categories records"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @GetMapping
    public ResponseEntity<List<CategoryDto>> findAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @Operation(summary = "Retrieve a specific category record by its ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the category record"),
            @ApiResponse(responseCode = "404", description = "Category record not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> findById(@PathVariable Long id) {
        CategoryDto dto = categoryService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Create a new category record",
            method = "POST",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created the category record"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "422", description = "Invalid data"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<CategoryDto> insert(@RequestBody @Valid CategoryDto dto) {
        dto = categoryService.insert(dto);
        return ResponseEntity.created(URI.create("/categories/" + dto.getId())).body(dto);
    }

    @Operation(summary = "Delete a category record by its ID",
            method = "DELETE",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted the category record"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Category record not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
