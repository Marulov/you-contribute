package com.marulov.youcontribute.repository;

import com.marulov.youcontribute.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {
    List<Issue> getIssueByProjectId(Long projectId);
}