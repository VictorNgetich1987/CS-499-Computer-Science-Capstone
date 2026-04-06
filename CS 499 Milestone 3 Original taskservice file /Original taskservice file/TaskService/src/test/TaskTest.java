package edu.cs320.task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    void validTaskIsCreated() {
        Task task = new Task("T1", "Groceries", "Buy milk and eggs");
        assertEquals("T1", task.getTaskId());
    }

    @Test
    void invalidTaskNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Task("T1", null, "Description"));
        assertThrows(IllegalArgumentException.class, () -> new Task("T1", "x".repeat(21), "Description"));
    }

    @Test
    void settersWork() {
        Task task = new Task("T1", "Read", "Read a book");
        task.setName("Write");
        task.setDescription("Write an essay");
        assertEquals("Write", task.getName());
    }
}
