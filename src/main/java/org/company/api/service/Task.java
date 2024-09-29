package org.company.api.service;

import jakarta.annotation.Nullable;
import org.company.api.common.DeadLine;
import org.company.api.common.TaskId;
import org.springframework.util.Assert;


public class Task {
    private TaskId id;
    private String title;
    private String description;
    private DeadLine deadLine;

    public Task(@Nullable TaskId taskId, String title, String description, DeadLine deadLine) {
        Assert.notNull(title, "Title cannot be null");
        Assert.notNull(description, "Description cannot be null");
        Assert.notNull(deadLine, "Deadline cannot be null");

        this.id = (taskId == null) ? new TaskId() : taskId;
        this.title = title;
        this.description = description;
        this.deadLine = deadLine;
    }



    public TaskId getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    public DeadLine getDeadline(){
        return deadLine;
    }



}
