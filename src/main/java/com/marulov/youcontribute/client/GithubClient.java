package com.marulov.youcontribute.client;

import com.marulov.youcontribute.configuration.GithubProperties;
import com.marulov.youcontribute.dto.githubClient.GithubIssueResponse;
import com.marulov.youcontribute.dto.project.ProjectDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class GithubClient {

    private final RestTemplate restTemplate;

    private final GithubProperties githubProperties;

    public GithubIssueResponse[] getAllIssues(ProjectDto projectDto, LocalDate date) {

        String issuesUrl = String.format("%s/repos/%s/%s/issues?since=%s",
                githubProperties.getApiUrl(), projectDto.getOrganization(), projectDto.getRepository(), date.toString());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "token " + githubProperties.getToken());
        HttpEntity<?> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<GithubIssueResponse[]> response = restTemplate.exchange(issuesUrl, HttpMethod.GET, requestEntity,
                GithubIssueResponse[].class);

        return response.getBody();
    }
}
