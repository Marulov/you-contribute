package com.marulov.youcontribute.service;

import com.marulov.youcontribute.client.GithubClient;
import com.marulov.youcontribute.dto.githubClient.GithubIssueResponse;
import com.marulov.youcontribute.dto.project.CreateProjectRequest;
import com.marulov.youcontribute.dto.project.ProjectDto;
import com.marulov.youcontribute.model.Issue;
import com.marulov.youcontribute.model.Project;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Data
public class ImportService {

    private final ProjectService projectService;

    private final IssueService issueService;

    private final GithubClient githubClient;

    @Value("${application.import-frequency}")
    private Integer importFrequency;

    public void importProject(CreateProjectRequest request) {
        projectService.create(request);
    }

    @Async
    public void importIssues(ProjectDto projectDto) {

        int schedulerFrequencyInMinute = importFrequency / 60000;
        LocalDate since = LocalDate.from(LocalDateTime
                .ofInstant(Instant.now().minus(schedulerFrequencyInMinute, ChronoUnit.MINUTES), ZoneOffset.UTC));

        GithubIssueResponse[] githubIssueResponses = githubClient.getAllIssues(projectDto, since);

        Project project = projectService.getProjectById(projectDto.getId());

        List<Issue> issues = Arrays.stream(githubIssueResponses).filter(githubIssue -> Objects.isNull(githubIssue.getPullRequest()))
                .map(githubIssue -> new Issue(githubIssue.getId(), project, githubIssue.getTitle(), githubIssue.getBody(), githubIssue.getHtmlUrl()))
                .collect(Collectors.toList());

        issueService.saveAll(issues);
    }
}