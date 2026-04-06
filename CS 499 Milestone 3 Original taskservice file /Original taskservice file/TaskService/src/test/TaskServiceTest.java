package edu.cs320.task;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TaskServiceTest {

    private TaskService service;

    @BeforeEach
    void setup() {
        service = new TaskService();
    }

    @Test
    void addTaskSuccessfully() {
        Task task = new Task("T1", "Laundry", "Do the laundry");
        service.addTask(task);
        assertEquals(task, service.getTask("T1"));
    }

    @Test
    void updateTaskFields() {
        Task task = new Task("T1", "Groceries", "Buy food");
        service.addTask(task);
        service.updateTaskName("T1", "Shopping");
        service.updateTaskDescription("T1", "Buy clothes");
        assertEquals("Shopping", service.getTask("T1").getName());
    }

    @Test
    void deleteTask() {
        Task task = new Task("T1", "Call", "Call friend");
        service.addTask(task);
        service.deleteTask("T1");
        assertNull(service.getTask("T1"));
    }
}
