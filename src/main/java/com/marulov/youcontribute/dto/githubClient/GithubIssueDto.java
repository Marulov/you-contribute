package com.marulov.youcontribute.dto.githubClient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GithubIssueDto {
    private String owner;
    private String repository;
    private LocalDate since;
}
