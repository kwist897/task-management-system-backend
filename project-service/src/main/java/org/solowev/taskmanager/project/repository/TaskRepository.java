package org.solowev.taskmanager.project.repository;

import org.solowev.taskmanager.project.domain.Task;
import org.solowev.taskmanager.project.enums.TaskStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    void deleteByProject_Id(Long id);

    List<Task> findByExecutorIdAndStatusIn(Long id, Collection<TaskStatusEnum> statuses);

    List<Task> findAllByExecutorId(Long id);

    List<Task> findByProject_Id(Long projectId);
}
