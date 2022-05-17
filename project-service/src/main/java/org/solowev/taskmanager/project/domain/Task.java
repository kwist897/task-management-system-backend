package org.solowev.taskmanager.project.domain;

import lombok.Getter;
import lombok.Setter;
import org.solowev.taskmanager.base.BaseEntity;
import org.solowev.taskmanager.project.enums.TaskStatusEnum;
import org.solowev.taskmanager.project.enums.TaskTypeEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity(name = "task")
public class Task extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private TaskTypeEnum type;

    @Column
    @Enumerated(EnumType.STRING)
    private TaskStatusEnum status;

    @Column
    private String subject;

    @Column
    private String text;

    @Column
    private Long interval;

    @ManyToOne
    private Project project;

    private Long ownerId;

    private Long executorId;
}
