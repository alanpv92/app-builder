package com.appbuilder.appbuilder.services.impl;

import com.appbuilder.appbuilder.dto.project.FileContentResponseDto;
import com.appbuilder.appbuilder.dto.project.FileNodeDto;
import com.appbuilder.appbuilder.services.FileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public List<FileNodeDto> getFileTree(String projectId, String userId) {
        return List.of();
    }

    @Override
    public FileContentResponseDto getFileContent(String projectId, String path, String userId) {
        return null;
    }
}
