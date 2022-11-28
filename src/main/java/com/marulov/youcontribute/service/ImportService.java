package com.marulov.youcontribute.service;

import com.marulov.youcontribute.client.GithubClient;
import com.marulov.youcontribute.dto.githubClient.IssuesDto;
import com.marulov.youcontribute.dto.githubClient.GithubIssueResponse;
import com.marulov.youcontribute.dto.project.CreateProjectRequest;
import com.marulov.youcontribute.model.Issue;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImportService {

    private final ProjectService projectService;

    private final IssueService issueService;

    private final GithubClient githubClient;

    public void importProject(CreateProjectRequest request) {
        projectService.create(request);
    }

    @Async
    public void importIssues(IssuesDto issuesDto) {
        issuesDto.setSince(LocalDate.from(LocalDateTime
                .ofInstant(Instant.now().minus(1, ChronoUnit.DAYS), ZoneId.systemDefault())));

        GithubIssueResponse[] githubIssueResponses = githubClient.getAllIssues(issuesDto);
        List<Issue> issues = Arrays.stream(githubIssueResponses).map(githubIssue ->
                        new Issue(githubIssue.getId(), githubIssue.getTitle(), githubIssue.getBody()))
                .collect(Collectors.toList());

        issueService.saveAll(issues);
    }
}