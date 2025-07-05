package com.taskpilot.taskpilot_backend.dto;

import com.taskpilot.taskpilot_backend.model.Task;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private Long id;
    private String title;
    private String description;
    private Task.TaskStatus status;
    private LocalDate dueDate;
    private Long projectId; 
}