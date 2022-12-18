package com.marulov.youcontribute.dto.issue;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IssuesListDto {
    private Long id;

    private String title;

    private String body;

    private String htmlUrl;
}