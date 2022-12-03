package com.marulov.youcontribute.repository;

import com.marulov.youcontribute.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findProjectByOrganizationAndRepository(String organization, String repository);
}
