package org.solowev.taskmanager.project.domain;

import lombok.Getter;
import lombok.Setter;
import org.solowev.taskmanager.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Entity(name = "project")
public class Project extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String title;

    @Column
    private String version;

    @Column
    private String description;

    @Column
    private Long ownerId;

    @Column
    private Long workspaceId;

    @OneToMany
    private List<Task> tasks;
}
