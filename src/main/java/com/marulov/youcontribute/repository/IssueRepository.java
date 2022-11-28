package com.marulov.youcontribute.repository;

import com.marulov.youcontribute.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue, Long> {
}