package com.marulov.youcontribute.client;

import com.marulov.youcontribute.configuration.GithubProperties;
import com.marulov.youcontribute.dto.githubClient.GithubIssueResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class GithubClient {

    private final RestTemplate restTemplate;

    private final GithubProperties githubProperties;

    public GithubIssueResponse[] getAllIssues(String owner, String repository) {

        String issuesUrl = String.format("%s/repos/%s/%s/issues", githubProperties.getApiUrl(), owner, repository);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "token " + githubProperties.getToken());
        HttpEntity<?> request = new HttpEntity<>(httpHeaders);

        ResponseEntity<GithubIssueResponse[]> response = restTemplate.exchange(issuesUrl, HttpMethod.GET, request,
                GithubIssueResponse[].class);

        return response.getBody();
    }
}
