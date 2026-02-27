package src.main.java;

/**
 * CS 320 Module Four Milestone
 * Task Class
 * 
 * Author: Rimon Hamo
 * Date: January 28, 2026
 * 
 * This class represents a single task in the mobile application.
 * It enforces the following requirements:
 * - Unique task ID: String, max 10 characters, not null, not updatable
 * - Name: String, max 20 characters, not null
 * - Description: String, max 50 characters, not null
 * 
 * All validations throw IllegalArgumentException with descriptive messages
 * to help identify issues during development and testing.
 */
public class Task {
    
    // taskId is final to prevent any updates after construction (meets "not updatable" requirement)
    private final String taskId;
    
    private String name;
    private String description;

    /**
     * Constructor - Creates a new Task object with full validation.
     * 
     * @param taskId        Unique identifier (required, max 10 characters, cannot be changed later)
     * @param name          Task name (required, max 20 characters)
     * @param description   Task description (required, max 50 characters)
     * @throws IllegalArgumentException if any field violates the requirements
     */
    public Task(String taskId, String name, String description) {
        // Validate taskId first since it's immutable and required
        if (taskId == null || taskId.trim().isEmpty()) {
            throw new IllegalArgumentException("Task ID cannot be null or empty");
        }
        if (taskId.length() > 10) {
            throw new IllegalArgumentException("Task ID cannot exceed 10 characters");
        }
        // Store the original value (no trimming here to preserve exact ID)
        this.taskId = taskId;

        // Delegate name and description validation to setters
        // This reuses the same logic and avoids code duplication
        setName(name);
        setDescription(description);
    }

    /**
     * Returns the unique, immutable task ID.
     * 
     * @return the task ID (guaranteed to be non-null and ≤10 characters)
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * Returns the current task name.
     * 
     * @return the name (guaranteed to be non-null and ≤20 characters)
     */
    public String getName() {
        return name;
    }

    /**
     * Updates the task name with strict validation.
     * 
     * @param name the new name value
     * @throws IllegalArgumentException if name is null, empty, or exceeds 20 characters
     */
    public void setName(String name) {
        // Check for null or blank (using trim() to reject "   " as invalid)
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        // Use trimmed length to ensure actual content doesn't exceed limit
        if (name.trim().length() > 20) {
            throw new IllegalArgumentException("Name cannot exceed 20 characters");
        }
        // Store the original input (not trimmed) to preserve whitespace if desired
        this.name = name;
    }

    /**
     * Returns the current task description.
     * 
     * @return the description (guaranteed to be non-null and ≤50 characters)
     */
    public String getDescription() {
        return description;
    }

    /**
     * Updates the task description with strict validation.
     * 
     * @param description the new description value
     * @throws IllegalArgumentException if description is null, empty, or exceeds 50 characters
     */
    public void setDescription(String description) {
        // Same null/empty check as name
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        // Check length using trimmed value to enforce meaningful content
        if (description.trim().length() > 50) {
            throw new IllegalArgumentException("Description cannot exceed 50 characters");
        }
        // Store original input
        this.description = description;
    }
}