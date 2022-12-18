package com.marulov.youcontribute.converter.project;

import com.marulov.youcontribute.dto.project.ProjectDto;
import com.marulov.youcontribute.model.Project;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProjectDtoConverter {

    public ProjectDto convert(Project project) {
        return ProjectDto.builder()
                .id(project.getId())
                .organization(project.getOrganization())
                .repository(project.getRepository())
                .build();
    }

    public List<ProjectDto> convert(List<Project> projects) {
        return projects.stream().map(this::convert).collect(Collectors.toList());
    }
}