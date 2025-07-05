package com.taskpilot.taskpilot_backend.repository;
import com.taskpilot.taskpilot_backend.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
