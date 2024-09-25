package org.company.api.service;


import org.company.api.common.TaskId;
import org.company.api.entity.TaskEntity;
import org.company.api.repository.TaskRepository;
import org.company.api.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;


    public TaskEntity saveTask(Task task){
        TaskEntity entity = new TaskEntity(task.getId().uuid(), task.getTitle(), task.getDescription(), task.getDeadline().getDate());
        taskRepository.save(entity);
        return entity;
    }


    public Optional<TaskEntity> findTaskById(TaskId id) {
        return taskRepository.findById(id.uuid());
    }

}
