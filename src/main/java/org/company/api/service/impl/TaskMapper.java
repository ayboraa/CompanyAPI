package org.company.api.service.impl;

import org.company.api.common.DeadLine;
import org.company.api.common.TaskId;
import org.company.api.entity.TaskEntity;
import org.company.api.service.Mapper;
import org.company.api.service.Task;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskMapper implements Mapper<Task, TaskEntity> {
    @Override
    public TaskEntity toEntity(Task task) {
        return new TaskEntity(task.getId().uuid(), task.getTitle(), task.getDescription(), task.getDeadline().getDate());
    }

    @Override
    public Task toDTO(TaskEntity entity) {
        return new Task(new TaskId(entity.getId()), entity.getTitle(), entity.getDescription(), new DeadLine(entity.getDeadline()));
    }

    @Override
    public List<Task> toDTOList(List<TaskEntity> entities) {
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

    }
}
