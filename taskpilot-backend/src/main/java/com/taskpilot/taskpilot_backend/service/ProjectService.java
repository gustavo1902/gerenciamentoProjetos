package com.taskpilot.taskpilot_backend.service;

import com.taskpilot.taskpilot_backend.dto.ProjectDTO;
import com.taskpilot.taskpilot_backend.dto.TaskDTO;
import com.taskpilot.taskpilot_backend.model.Project;
import com.taskpilot.taskpilot_backend.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    // Mapeamento de Entidade para DTO
    private ProjectDTO convertToDto(Project project) {
        ProjectDTO dto = new ProjectDTO();
        dto.setId(project.getId());
        dto.setName(project.getName());
        dto.setDescription(project.getDescription());
        if (project.getTasks() != null) {
            dto.setTasks(project.getTasks().stream()
                    .map(task -> {
                        TaskDTO taskDto = new TaskDTO();
                        taskDto.setId(task.getId());
                        taskDto.setTitle(task.getTitle());
                        taskDto.setDescription(task.getDescription());
                        taskDto.setStatus(task.getStatus());
                        taskDto.setDueDate(task.getDueDate());
                        taskDto.setProjectId(task.getProject() != null ? task.getProject().getId() : null);
                        return taskDto;
                    })
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    // Mapeamento de DTO para Entidade
    private Project convertToEntity(ProjectDTO dto) {
        Project project = new Project();
        project.setId(dto.getId()); // Pode ser null para novos projetos
        project.setName(dto.getName());
        project.setDescription(dto.getDescription());
        // Tasks serão associadas separadamente ou em um serviço de Task
        return project;
    }

    public List<ProjectDTO> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public ProjectDTO getProjectById(Long id) {
        return projectRepository.findById(id)
                .map(this::convertToDto)
                .orElse(null);
    }

    public ProjectDTO createProject(ProjectDTO projectDTO) {
        Project project = convertToEntity(projectDTO);
        Project savedProject = projectRepository.save(project);
        return convertToDto(savedProject);
    }

    public ProjectDTO updateProject(Long id, ProjectDTO projectDTO) {
        return projectRepository.findById(id).map(existingProject -> {
            existingProject.setName(projectDTO.getName());
            existingProject.setDescription(projectDTO.getDescription());
            Project updatedProject = projectRepository.save(existingProject);
            return convertToDto(updatedProject);
        }).orElse(null);
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}
