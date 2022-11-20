package com.marulov.youcontribute.converter.project;

import com.marulov.youcontribute.dto.project.CreateProjectRequest;
import com.marulov.youcontribute.model.Project;
import org.springframework.stereotype.Component;


@Component
public class CreateProjectRequestConverter {

    public Project convert(CreateProjectRequest request) {
        return Project.builder()
                .organization(request.getOrganization())
                .repository(request.getRepository())
                .build();
    }
}
