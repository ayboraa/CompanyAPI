package org.company.api;


import org.company.api.common.DeadLine;
import org.company.api.common.Email;
import org.company.api.service.Task;
import org.company.api.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@AutoConfigureTestDatabase
@SpringBootTest
public class TaskServiceTest {

    @Autowired
    private TaskService taskService;

    @Test
    public void testSingleCRUD() {

        // Test create.
        Task task = new Task(null, "Refactor", "Optimize", new DeadLine(Date.from(Instant.now().plusSeconds(3600 * 24 * 5))));
        Task savedTask = taskService.saveTask(task);
        assertThat(savedTask).isNotNull();
        assertThat(savedTask.getTitle()).isEqualTo("Refactor");
        assertThat(savedTask.getDeadline().isTimePassed()).isFalse();

        // Test read.
        Task foundTask = taskService.findTaskById(savedTask.getId());
        assertThat(foundTask).isNotNull();
        assertThat(foundTask.getTitle()).isEqualTo("Refactor");
        assertThat(foundTask.getDeadline().isTimePassed()).isFalse();

        // Test update.
        Task toUpdate = new Task(foundTask.getId(), task.getTitle(), task.getDescription(), task.getDeadline());
        Task updatedTask = taskService.updateTask(savedTask.getId(), toUpdate);
        assertThat(updatedTask).isNotNull();
        assertThat(updatedTask.getDescription()).isEqualTo("Optimize");
        // Ensure update is correct.
        updatedTask = taskService.findTaskById(updatedTask.getId());
        assertThat(updatedTask).isNotNull();
        assertThat(updatedTask.getDescription()).isEqualTo("Optimize");

        // Test read.
        List<Task> taskList = taskService.getAllTasks();
        assertThat(taskList).isNotNull();
        assertThat(taskList.size()).isEqualTo(1);

        // Test delete.
        taskService.deleteTask(taskList.getFirst().getId());
        taskList = taskService.getAllTasks();
        assertThat(taskList).isNotNull();
        assertThat(taskList.size()).isEqualTo(0);

    }
}
