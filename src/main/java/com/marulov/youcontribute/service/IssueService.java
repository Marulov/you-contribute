package com.marulov.youcontribute.service;

import com.marulov.youcontribute.converter.issue.IssueListDtoConverter;
import com.marulov.youcontribute.dto.issue.IssuesListDto;
import com.marulov.youcontribute.model.Issue;
import com.marulov.youcontribute.repository.IssueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IssueService {

    private final IssueRepository issueRepository;

    private final IssueListDtoConverter issueListDtoConverter;

    @Transactional
    public void saveAll(List<Issue> issues) {
        issueRepository.saveAll(issues);
    }

    public List<IssuesListDto> getIssuesByProjectId(Long projectId) {
        return issueListDtoConverter.convert(issueRepository.getIssueByProjectId(projectId));
    }
}