package com.marulov.youcontribute.dto.project;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectDto {
    private Long id;
    private String organization;
    private String repository;
}
