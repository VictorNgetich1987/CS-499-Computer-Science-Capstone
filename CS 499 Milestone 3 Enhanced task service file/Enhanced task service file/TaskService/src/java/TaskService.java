package edu.cs320.task;

import java.util.HashMap;
import java.util.Map;

public class TaskService {

    // Using HashMap for efficient O(1) lookup, insertion, and deletion
    private final Map<String, Task> tasks = new HashMap<>();

    // Add a new task
    public void addTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }

        // Ensure task ID is unique
        if (tasks.containsKey(task.getTaskId())) {
            throw new IllegalArgumentException("Task ID must be unique");
        }

        tasks.put(task.getTaskId(), task);
    }

    // Delete a task by ID
    public void deleteTask(String taskId) {
        if (taskId == null || !tasks.containsKey(taskId)) {
            throw new IllegalArgumentException("Task ID not found");
        }

        tasks.remove(taskId);
    }

    // Update task name
    public void updateTaskName(String taskId, String newName) {
        if (taskId == null) {
            throw new IllegalArgumentException("Task ID cannot be null");
        }

        Task task = tasks.get(taskId);

        if (task == null) {
            throw new IllegalArgumentException("Task ID not found");
        }

        task.setName(newName);
    }

    // Update task description
    public void updateTaskDescription(String taskId, String newDescription) {
        if (taskId == null) {
            throw new IllegalArgumentException("Task ID cannot be null");
        }

        Task task = tasks.get(taskId);

        if (task == null) {
            throw new IllegalArgumentException("Task ID not found");
        }

        task.setDescription(newDescription);
    }

    // Retrieve a task (O(1) operation)
    public Task getTask(String taskId) {
        if (taskId == null) {
            throw new IllegalArgumentException("Task ID cannot be null");
        }

        return tasks.get(taskId);
    }
}