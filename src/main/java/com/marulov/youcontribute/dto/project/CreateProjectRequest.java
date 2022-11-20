package com.marulov.youcontribute.dto.project;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateProjectRequest {
    private String organization;
    private String repository;
}