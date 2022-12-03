package com.marulov.youcontribute.service;

import com.marulov.youcontribute.converter.project.CreateProjectRequestConverter;
import com.marulov.youcontribute.converter.project.ProjectDtoConverter;
import com.marulov.youcontribute.dto.project.CreateProjectRequest;
import com.marulov.youcontribute.dto.project.ProjectDto;
import com.marulov.youcontribute.exception.DuplicatedProjectException;
import com.marulov.youcontribute.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final CreateProjectRequestConverter createProjectRequestConverter;
    private final ProjectDtoConverter projectDtoConverter;

    public void create(CreateProjectRequest request) {
        projectIfExistValidate(request.getOrganization(), request.getRepository());
        projectRepository.save(createProjectRequestConverter.convert(request));
    }

    public List<ProjectDto> getAll() {
        return projectDtoConverter.convert(projectRepository.findAll());
    }

    private void projectIfExistValidate(String organization, String repository) {
        projectRepository.findProjectByOrganizationAndRepository(organization, repository)
                .ifPresent(project -> {
                    throw new DuplicatedProjectException(String.format("Organization: %s Repository: %s already exist",
                            project.getOrganization(), project.getRepository()));
                });
    }
}