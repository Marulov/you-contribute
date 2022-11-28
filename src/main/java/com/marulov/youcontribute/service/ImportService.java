package com.marulov.youcontribute.service;

import com.marulov.youcontribute.client.GithubClient;
import com.marulov.youcontribute.dto.githubClient.GithubIssueResponse;
import com.marulov.youcontribute.dto.project.CreateProjectRequest;
import com.marulov.youcontribute.dto.project.ProjectDto;
import com.marulov.youcontribute.model.Issue;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

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
    public void importIssues(ProjectDto projectDto) {
        GithubIssueResponse[] githubIssueResponses = githubClient.getAllIssues(projectDto.getOrganization(),
                projectDto.getRepository());
        List<Issue> issues = Arrays.stream(githubIssueResponses).map(githubIssue ->
                        new Issue(githubIssue.getId(), githubIssue.getTitle(), githubIssue.getBody()))
                .collect(Collectors.toList());
        issueService.saveAll(issues);
    }
}