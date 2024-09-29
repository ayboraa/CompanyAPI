package org.company.api.service;


import jakarta.transaction.Transactional;
import org.company.api.common.TaskId;
import org.company.api.controller.exception.ResourceNotFoundException;
import org.company.api.entity.TaskEntity;
import org.company.api.repository.TaskRepository;
import org.company.api.service.impl.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;


    private final TaskMapper _mapper = new TaskMapper();


    public Task saveTask(Task task){
        TaskEntity entity = new TaskEntity(task.getId().uuid(), task.getTitle(), task.getDescription(), task.getDeadline().getDate());
        taskRepository.save(entity);
        return _mapper.toDTO(entity);
    }


    public Task findTaskById(TaskId id) {

        Optional<TaskEntity> opt = taskRepository.findById(id.uuid());
        if(opt.isEmpty())
            throw new ResourceNotFoundException("Task with ID " + id.uuid() + " not found.");
        else
            return _mapper.toDTO(opt.get());

    }

    public void deleteTask(TaskId id) {

        if (!taskRepository.existsById(id.uuid())) {
            throw new ResourceNotFoundException("Task not found with id: " + id.uuid());
        }

        taskRepository.deleteById(id.uuid());

    }

    public List<Task> getAllTasks() {
        List<TaskEntity> entities = taskRepository.findAll();
        return _mapper.toDTOList(entities);
    }

    @Transactional
    public Task updateTask(TaskId id, Task newTask) {
        return taskRepository.findById(id.uuid())
                .map(taskEntity -> {
                    taskEntity.setTitle(newTask.getTitle());
                    taskEntity.setDescription(newTask.getDescription());
                    taskEntity.setDeadline(newTask.getDeadline().getDate());
                    return _mapper.toDTO(taskRepository.save(taskEntity));
                })
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with ID: " + id.uuid()));
    }


}
