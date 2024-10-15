package br.com.ruderson.portfolio_api.controllers;

import br.com.ruderson.portfolio_api.dto.ProjectDto;
import br.com.ruderson.portfolio_api.projections.ProjectSummaryProjection;
import br.com.ruderson.portfolio_api.services.impl.ProjectServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    private ProjectServiceImpl projectService;

    @GetMapping
    public ResponseEntity<List<ProjectDto>> findAll(){
        return ResponseEntity.ok(projectService.findAll());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/summary")
    public ResponseEntity<List<ProjectSummaryProjection>> findAllSummary(){
        return ResponseEntity.ok(projectService.findAllProjectedBy());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> findById(@PathVariable Long id) {
        ProjectDto dto = projectService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<ProjectDto> insert(@RequestBody @Valid ProjectDto dto) {
        dto = projectService.insert(dto);
        return ResponseEntity.created(URI.create("/projects/" + dto.getId())).body(dto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ProjectDto> update(@PathVariable Long id, @RequestBody @Valid ProjectDto dto) {
        dto = projectService.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        projectService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
