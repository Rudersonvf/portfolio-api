package br.com.ruderson.portfolio_api.controllers;

import br.com.ruderson.portfolio_api.dto.experience.ExperienceDTO;
import br.com.ruderson.portfolio_api.dto.experience.ExperienceResponse;
import br.com.ruderson.portfolio_api.entities.Experience;
import br.com.ruderson.portfolio_api.services.impl.ExperienceServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/experiences")
public class ExperienceController {

    @Autowired
    private ExperienceServiceImpl experienceService;

    @GetMapping
    public ResponseEntity<List<ExperienceResponse>> getAll() {
        return ResponseEntity.ok(experienceService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExperienceResponse> findById(@PathVariable Long id) {
        ExperienceResponse dto = experienceService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<ExperienceDTO> insert(@RequestBody @Valid ExperienceDTO dto) {
        ExperienceDTO experience = experienceService.insert(dto);
        return ResponseEntity.created(URI.create("/experiences/" + experience.getId())).body(experience);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ExperienceDTO> update(@PathVariable Long id, @RequestBody @Valid ExperienceDTO dto) {
        dto = experienceService.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        experienceService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
