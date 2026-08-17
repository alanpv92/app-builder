package com.appbuilder.appbuilder.services;

import com.appbuilder.appbuilder.dto.project.ProjectCreationRequestDto;
import com.appbuilder.appbuilder.dto.project.ProjectResponseDto;
import com.appbuilder.appbuilder.dto.project.ProjectSummaryResponseDto;

import java.util.List;

public interface ProjectService {

    List<ProjectSummaryResponseDto> getUserProjects(String userId);

    ProjectResponseDto getUserProjectById(String id, String userId);

    ProjectResponseDto createProject(ProjectCreationRequestDto request, String userId);

    ProjectResponseDto updateProject(String id, ProjectCreationRequestDto request, String userId);

    void softDelete(String id, String userId);
}
