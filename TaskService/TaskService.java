package src.main.java;

/**
 * CS 320 Module Four Milestone
 * TaskService Class
 * 
 * Author: Rimon Hamo
 * Date: January 28, 2026
 * 
 * This class manages a collection of Task objects in memory using a HashMap.
 * It enforces the following requirements:
 * - Add tasks with a unique task ID (rejects duplicates)
 * - Delete tasks by task ID (silent fail if not found)
 * - Update task name and/or description by task ID (only updatable fields)
 * 
 * Uses HashMap for O(1) average-case lookup and insertion performance.
 * The map is final to prevent accidental replacement of the data structure.
 */

import java.util.HashMap;
import java.util.Map;

public class TaskService {
    
    // In-memory storage: maps taskId (String) to Task object
    // Using HashMap for fast lookup by ID and to enforce uniqueness
    // Declared final so the map itself cannot be reassigned
    private final Map<String, Task> tasks = new HashMap<>();

    /**
     * Adds a new task to the service.
     * Ensures the task ID is unique (no duplicates allowed).
     * 
     * @param task the Task object to add (must not be null)
     * @throws IllegalArgumentException if task is null or its ID already exists
     */
    public void addTask(Task task) {
        // Null check to prevent adding invalid tasks
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }
        
        // Check for duplicate ID (core requirement for uniqueness)
        String id = task.getTaskId();
        if (tasks.containsKey(id)) {
            throw new IllegalArgumentException("Task ID already exists: " + id);
        }
        
        // Store the task using its ID as the key
        tasks.put(id, task);
    }

    /**
     * Deletes a task by its unique ID.
     * If the task does not exist, the operation is silent (no exception thrown).
     * 
     * @param taskId the ID of the task to delete
     * @return true if a task was found and removed, false if no task existed with that ID
     */
    public boolean deleteTask(String taskId) {
        // Remove returns the removed value (or null if not present)
        // We return true/false to indicate success for testing clarity
        return tasks.remove(taskId) != null;
    }

    /**
     * Updates the name and/or description of an existing task by its ID.
     * Only the updatable fields (name and description) can be changed.
     * Null parameters are ignored (no change for that field).
     * 
     * @param taskId         ID of the task to update
     * @param newName        new name value (null = no change)
     * @param newDescription new description value (null = no change)
     * @throws IllegalArgumentException if task not found or update values are invalid
     */
    public void updateTask(String taskId, String newName, String newDescription) {
        // Retrieve the task by ID
        Task task = tasks.get(taskId);
        
        // If task doesn't exist, throw exception (fail fast for invalid operations)
        if (task == null) {
            throw new IllegalArgumentException("Task not found with ID: " + taskId);
        }

        // Update name only if a new value is provided
        if (newName != null) {
            task.setName(newName);  // Setter handles validation (null/empty/length)
        }

        // Update description only if a new value is provided
        if (newDescription != null) {
            task.setDescription(newDescription);  // Setter handles validation
        }
    }

    /**
     * Helper method to retrieve a task by ID (for testing purposes only).
     * Not part of the required TaskService interface, but useful for verification.
     * 
     * @param taskId the ID of the task to retrieve
     * @return the Task object, or null if not found
     */
    public Task getTask(String taskId) {
        return tasks.get(taskId);
    }
}