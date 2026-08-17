package com.appbuilder.appbuilder.controllers;


import com.appbuilder.appbuilder.dto.project.FileContentResponseDto;
import com.appbuilder.appbuilder.dto.project.FileNodeDto;
import com.appbuilder.appbuilder.services.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects/{projectId}/files")
public class FileController {

    private final FileService fileService;

    @GetMapping
    public ResponseEntity<List<FileNodeDto>> getFileTree(@PathVariable String projectId) {
        var dummyUserId="11";
        return ResponseEntity.ok(fileService.getFileTree(projectId, dummyUserId));
    }

    @GetMapping("/{*path}")
    public ResponseEntity<FileContentResponseDto> getFile(
            @PathVariable String projectId,
            @PathVariable String path
    ) {
        String userId = "123";
        return ResponseEntity.ok(fileService.getFileContent(projectId, path, userId));
    }

}
