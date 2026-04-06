package edu.cs320.task;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskServiceTest {

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
    void addTaskDuplicateIdThrowsException() {
        Task task1 = new Task("T1", "Laundry", "Do the laundry");
        Task task2 = new Task("T1", "Shopping", "Buy food");
        service.addTask(task1);
        assertThrows(IllegalArgumentException.class, () -> service.addTask(task2));
    }

    @Test
    void addTaskNullThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> service.addTask(null));
    }

    @Test
    void updateTaskFieldsSuccessfully() {
        Task task = new Task("T1", "Groceries", "Buy food");
        service.addTask(task);
        service.updateTaskName("T1", "Shopping");
        service.updateTaskDescription("T1", "Buy clothes");
        assertEquals("Shopping", service.getTask("T1").getName());
        assertEquals("Buy clothes", service.getTask("T1").getDescription());
    }

    @Test
    void updateNonExistentTaskThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> service.updateTaskName("T99", "Name"));
        assertThrows(IllegalArgumentException.class, () -> service.updateTaskDescription("T99", "Description"));
    }

    @Test
    void deleteTaskSuccessfully() {
        Task task = new Task("T1", "Call", "Call friend");
        service.addTask(task);
        service.deleteTask("T1");
        assertNull(service.getTask("T1"));
    }

    @Test
    void deleteNonExistentTaskThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> service.deleteTask("T99"));
    }

    @Test
    void getTaskNullIdThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> service.getTask(null));
    }
}