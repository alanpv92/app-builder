package com.appbuilder.appbuilder.controllers;


import com.appbuilder.appbuilder.dto.project.ProjectCreationRequestDto;
import com.appbuilder.appbuilder.dto.project.ProjectResponseDto;
import com.appbuilder.appbuilder.dto.project.ProjectSummaryResponseDto;
import com.appbuilder.appbuilder.services.ProjectService;
import com.appbuilder.appbuilder.utils.helpers.SecurityHelper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectControllerDto {

    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<ProjectSummaryResponseDto>> getMyProjects() {
        String userId = SecurityHelper.getId();
        return ResponseEntity.ok(projectService.getUserProjects(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> getProjectById(@PathVariable String id) {
        String userId = SecurityHelper.getId();
        return ResponseEntity.ok(projectService.getUserProjectById(id, userId));
    }

    @PostMapping
    public ResponseEntity<ProjectResponseDto> createProject(@RequestBody @Valid ProjectCreationRequestDto request) {
        String userId = SecurityHelper.getId();
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.createProject(request, userId));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> updateProject(@PathVariable String id, @RequestBody @Valid ProjectCreationRequestDto request) {
        String userId = SecurityHelper.getId();
        return ResponseEntity.ok(projectService.updateProject(id, request, userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable String id) {
        String userId = SecurityHelper.getId();
        projectService.softDelete(id, userId);
        return ResponseEntity.noContent().build();
    }
}
