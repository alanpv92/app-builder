package com.appbuilder.appbuilder.services;

import com.appbuilder.appbuilder.dto.project.FileContentResponseDto;
import com.appbuilder.appbuilder.dto.project.FileNodeDto;

import java.util.List;

public interface FileService {

    List<FileNodeDto> getFileTree(String projectId,String userId);

    FileContentResponseDto getFileContent(String projectId,String path,String userId);
}
