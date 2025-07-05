package com.taskpilot.taskpilot_backend.service;

import com.taskpilot.taskpilot_backend.dto.TaskDTO;
import com.taskpilot.taskpilot_backend.model.Project;
import com.taskpilot.taskpilot_backend.model.Task;
import com.taskpilot.taskpilot_backend.repository.ProjectRepository;
import com.taskpilot.taskpilot_backend.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    // Mapeamento de Entidade para DTO
    private TaskDTO convertToDto(Task task) {
        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());
        dto.setDueDate(task.getDueDate());
        dto.setProjectId(task.getProject() != null ? task.getProject().getId() : null);
        return dto;
    }

    // Mapeamento de DTO para Entidade
    private Task convertToEntity(TaskDTO dto) {
        Task task = new Task();
        task.setId(dto.getId()); // Pode ser null para novas tarefas
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        task.setDueDate(dto.getDueDate());
        if (dto.getProjectId() != null) {
            Project project = projectRepository.findById(dto.getProjectId())
                    .orElseThrow(() -> new RuntimeException("Project not found with ID: " + dto.getProjectId()));
            task.setProject(project);
        }
        return task;
    }

    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public TaskDTO getTaskById(Long id) {
        return taskRepository.findById(id)
                .map(this::convertToDto)
                .orElse(null);
    }

    public List<TaskDTO> getTasksByProjectId(Long projectId) {
        return taskRepository.findByProjectId(projectId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public TaskDTO createTask(TaskDTO taskDTO) {
        Task task = convertToEntity(taskDTO);
        Task savedTask = taskRepository.save(task);
        return convertToDto(savedTask);
    }

    public TaskDTO updateTask(Long id, TaskDTO taskDTO) {
        return taskRepository.findById(id).map(existingTask -> {
            existingTask.setTitle(taskDTO.getTitle());
            existingTask.setDescription(taskDTO.getDescription());
            existingTask.setStatus(taskDTO.getStatus());
            existingTask.setDueDate(taskDTO.getDueDate());
            if (taskDTO.getProjectId() != null) {
                Project project = projectRepository.findById(taskDTO.getProjectId())
                        .orElseThrow(() -> new RuntimeException("Project not found with ID: " + taskDTO.getProjectId()));
                existingTask.setProject(project);
            }
            Task updatedTask = taskRepository.save(existingTask);
            return convertToDto(updatedTask);
        }).orElse(null);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}