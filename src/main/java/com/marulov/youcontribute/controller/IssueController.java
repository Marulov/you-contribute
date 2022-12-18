package com.marulov.youcontribute.controller;

import com.marulov.youcontribute.dto.issue.IssuesListDto;
import com.marulov.youcontribute.service.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/issues")
@RequiredArgsConstructor
public class IssueController {

    private final IssueService issueService;

    @GetMapping("getIssuesByProjectId/{id}")
    public List<IssuesListDto> getIssuesByProjectId(@PathVariable("id") Long id) {
        return issueService.getIssuesByProjectId(id);
    }
}