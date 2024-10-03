package br.com.ruderson.portfolio_api.controllers;

import br.com.ruderson.portfolio_api.dto.EducationDto;
import br.com.ruderson.portfolio_api.dto.EducationResponse;
import br.com.ruderson.portfolio_api.services.impl.EducationServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/educations")
public class EducationController {
    @Autowired
    private EducationServiceImpl educationService;

    @GetMapping
    public ResponseEntity<List<EducationResponse>> findAll() {
        return ResponseEntity.ok(educationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EducationResponse> findById(@PathVariable Long id) {
        EducationResponse dto = educationService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<EducationDto> insert(@RequestBody @Valid EducationDto dto) {
        EducationDto education = educationService.insert(dto);
        return ResponseEntity.created(URI.create("/educations/" + education.getId())).body(education);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<EducationDto> update(@PathVariable Long id, @RequestBody @Valid EducationDto dto) {
        dto = educationService.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        educationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
