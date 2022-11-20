package com.marulov.youcontribute.repository;

import com.marulov.youcontribute.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
