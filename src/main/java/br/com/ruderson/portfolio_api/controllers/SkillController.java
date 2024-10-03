package br.com.ruderson.portfolio_api.controllers;

import br.com.ruderson.portfolio_api.dto.SkillDTO;
import br.com.ruderson.portfolio_api.dto.SkillResponse;
import br.com.ruderson.portfolio_api.services.impl.SkillServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillController {
    @Autowired
    private SkillServiceImpl skillService;

    @GetMapping
    public ResponseEntity<List<SkillResponse>> findAll() {
        return ResponseEntity.ok(skillService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillResponse> findById(@PathVariable Long id) {
        SkillResponse dto = skillService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<SkillDTO> insert(@RequestBody @Valid SkillDTO dto) {
        SkillDTO skill = skillService.insert(dto);
        return ResponseEntity.created(URI.create("/experiences/" + skill.getId())).body(skill);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<SkillDTO> update(@PathVariable Long id, @RequestBody @Valid SkillDTO dto) {
        dto = skillService.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        skillService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
