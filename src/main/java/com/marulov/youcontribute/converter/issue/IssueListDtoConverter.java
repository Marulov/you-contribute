package com.marulov.youcontribute.converter.issue;

import com.marulov.youcontribute.dto.issue.IssuesListDto;
import com.marulov.youcontribute.model.Issue;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component

public class IssueListDtoConverter {

    public IssuesListDto convert(Issue issue) {
        return IssuesListDto.builder()
                .id(issue.getId())
                .title(issue.getTitle())
                .body(issue.getBody())
                .htmlUrl(issue.getHtmlUrl())
                .build();
    }

    public List<IssuesListDto> convert(List<Issue> issues) {
        return issues.stream().map(this::convert).collect(Collectors.toList());
    }
}