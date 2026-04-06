package edu.cs320.task;

import java.util.Objects;

/**
 * Represents a Task with a unique ID, name, and description.
 * Validates input to ensure data integrity.
 */
public class Task {

    private final String taskId;
    private String name;
    private String description;

    /**
     * Constructs a Task.
     *
     * @param taskId      Unique identifier (max 10 characters, not null)
     * @param name        Task name (max 20 characters, not null)
     * @param description Task description (max 50 characters, not null)
     */
    public Task(String taskId, String name, String description) {
        validateTaskId(taskId);
        validateName(name);
        validateDescription(description);
        this.taskId = taskId;
        this.name = name;
        this.description = description;
    }

    // --- Getters ---
    public String getTaskId() {
        return taskId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    // --- Setters with validation ---
    public void setName(String name) {
        validateName(name);
        this.name = name;
    }

    public void setDescription(String description) {
        validateDescription(description);
        this.description = description;
    }

    // --- Validation Methods ---
    private void validateTaskId(String taskId) {
        if (taskId == null || taskId.isBlank() || taskId.length() > 10) {
            throw new IllegalArgumentException("Task ID must be 1-10 characters and cannot be null or blank.");
        }
    }

    private void validateName(String name) {
        if (name == null || name.isBlank() || name.length() > 20) {
            throw new IllegalArgumentException("Task name must be 1-20 characters and cannot be null or blank.");
        }
    }

    private void validateDescription(String description) {
        if (description == null || description.isBlank() || description.length() > 50) {
            throw new IllegalArgumentException("Task description must be 1-50 characters and cannot be null or blank.");
        }
    }

    // --- Override equals and hashCode ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return taskId.equals(task.taskId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId);
    }

    // --- Override toString ---
    @Override
    public String toString() {
        return "Task{" +
                "taskId='" + taskId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}