package org.solowev.taskmanager.project.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.solowev.taskmanager.base.BaseMapper;
import org.solowev.taskmanager.project.domain.Task;
import org.solowev.taskmanager.project.dto.TaskDto;
import org.solowev.taskmanager.project.dto.TaskRequestDto;

@Mapper(componentModel = "spring")
public interface TaskMapper extends BaseMapper<Task, TaskDto> {
    Task toEntityFromRequest(TaskRequestDto requestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTask(TaskRequestDto requestDto, @MappingTarget Task task);
}
