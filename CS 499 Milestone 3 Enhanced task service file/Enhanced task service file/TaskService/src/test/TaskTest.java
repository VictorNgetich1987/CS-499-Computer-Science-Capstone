package edu.cs320.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void validTaskIsCreated() {
        Task task = new Task("T1", "Groceries", "Buy milk and eggs");
        assertEquals("T1", task.getTaskId());
        assertEquals("Groceries", task.getName());
        assertEquals("Buy milk and eggs", task.getDescription());
    }

    @Test
    void invalidTaskIdThrowsException() {
        // Null or blank task ID
        assertThrows(IllegalArgumentException.class, () -> new Task(null, "Name", "Description"));
        assertThrows(IllegalArgumentException.class, () -> new Task("", "Name", "Description"));
        // Too long task ID
        assertThrows(IllegalArgumentException.class, () -> new Task("T1234567890", "Name", "Description"));
    }

    @Test
    void invalidTaskNameThrowsException() {
        // Null or blank
        assertThrows(IllegalArgumentException.class, () -> new Task("T1", null, "Description"));
        assertThrows(IllegalArgumentException.class, () -> new Task("T1", "", "Description"));
        // Too long name
        assertThrows(IllegalArgumentException.class, () -> new Task("T1", "x".repeat(21), "Description"));
    }

    @Test
    void invalidTaskDescriptionThrowsException() {
        // Null or blank
        assertThrows(IllegalArgumentException.class, () -> new Task("T1", "Name", null));
        assertThrows(IllegalArgumentException.class, () -> new Task("T1", "Name", ""));
        // Too long description
        assertThrows(IllegalArgumentException.class, () -> new Task("T1", "Name", "x".repeat(51)));
    }

    @Test
    void settersWork() {
        Task task = new Task("T1", "Read", "Read a book");
        task.setName("Write");
        task.setDescription("Write an essay");
        assertEquals("Write", task.getName());
        assertEquals("Write an essay", task.getDescription());
    }

    @Test
    void settersThrowExceptionForInvalidValues() {
        Task task = new Task("T1", "Read", "Read a book");
        assertThrows(IllegalArgumentException.class, () -> task.setName(null));
        assertThrows(IllegalArgumentException.class, () -> task.setName("x".repeat(21)));
        assertThrows(IllegalArgumentException.class, () -> task.setDescription(null));
        assertThrows(IllegalArgumentException.class, () -> task.setDescription("x".repeat(51)));
    }

    @Test
    void equalsAndHashCodeWork() {
        Task task1 = new Task("T1", "Read", "Read a book");
        Task task2 = new Task("T1", "Write", "Write an essay");
        Task task3 = new Task("T2", "Read", "Read a book");

        assertEquals(task1, task2); // same ID
        assertNotEquals(task1, task3); // different ID
        assertEquals(task1.hashCode(), task2.hashCode());
    }

    @Test
    void toStringContainsAllFields() {
        Task task = new Task("T1", "Read", "Read a book");
        String str = task.toString();
        assertTrue(str.contains("T1"));
        assertTrue(str.contains("Read"));
        assertTrue(str.contains("Read a book"));
    }
}