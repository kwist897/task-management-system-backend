package org.solowev.taskmanager.project.dto;

import lombok.Data;

@Data
public class ProjectDto {
    private Long id;
    private String title;
    private String version;
    private String description;
}
