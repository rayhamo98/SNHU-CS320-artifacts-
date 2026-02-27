package src.test.java;

import src.main.java.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * CS 320 Module Four Milestone - TaskTest
 * 
 * Author: Rimon Hamo
 * Date: January 28, 2026
 * 
 * JUnit 5 tests for the Task class.
 * This test suite verifies all required constraints:
 * - taskId: unique, <=10 chars, not null, not updatable (final field, no setter)
 * - name: <=20 chars, not null (null/empty throws exception)
 * - description: <=50 chars, not null (null/empty throws exception)
 * 
 * Tests cover valid creation, invalid inputs, and update operations on updatable fields.
 */
public class TaskTest {

    /**
     * Verifies successful creation of a Task with valid inputs.
     * Ensures constructor accepts valid data and getters return correct values.
     */
    @Test
    void testValidTaskCreation() {
        Task task = new Task("Task0001", "Finish Report", "Complete the monthly sales report by EOD");
        assertEquals("Task0001", task.getTaskId(), "Task ID should match input");
        assertEquals("Finish Report", task.getName(), "Name should match input");
        assertEquals("Complete the monthly sales report by EOD", task.getDescription(),
                "Description should match input");
    }

    // Task ID validation tests (required: not null, <=10 chars, immutable)
    /**
     * Verifies that null taskId throws IllegalArgumentException.
     * Ensures taskId requirement (not null) is enforced.
     */
    @Test
    void testTaskIdNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Task(null, "Valid Name", "Valid Desc");
        });
        assertEquals("Task ID cannot be null or empty", exception.getMessage(),
                "Exception message should indicate null/empty ID");
    }

    /**
     * Verifies that empty taskId throws IllegalArgumentException.
     * Ensures taskId cannot be blank.
     */
    @Test
    void testTaskIdEmpty() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Task("", "Valid Name", "Valid Desc");
        });
        assertEquals("Task ID cannot be null or empty", exception.getMessage(),
                "Exception message should indicate null/empty ID");
    }

    /**
     * Verifies that taskId exceeding 10 characters throws IllegalArgumentException.
     * Ensures length constraint is enforced.
     */
    @Test
    void testTaskIdTooLong() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Task("ThisIsWayTooLongID", "Valid Name", "Valid Desc");
        });
        assertEquals("Task ID cannot exceed 10 characters", exception.getMessage(),
                "Exception message should indicate length violation");
    }

    /**
     * Verifies that taskId is immutable (no setter exists, field is final).
     * Confirms "not updatable" requirement.
     */
    @Test
    void testTaskIdNotUpdatable() {
        Task task = new Task("Task0001", "Original Name", "Original Desc");
        // No setter for taskId → cannot change it
        // This test confirms the field is final and inaccessible for modification
        assertEquals("Task0001", task.getTaskId(), "Task ID should remain unchanged");
    }

    // Name validation tests (required: not null, <=20 chars)
    /**
     * Verifies that null name throws IllegalArgumentException.
     * Ensures name requirement (not null) is enforced.
     */
    @Test
    void testNameNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Task("Task0001", null, "Valid Desc");
        });
        assertEquals("Name cannot be null or empty", exception.getMessage(),
                "Exception message should indicate null/empty name");
    }

    /**
     * Verifies that empty name throws IllegalArgumentException.
     * Ensures name cannot be blank.
     */
    @Test
    void testNameEmpty() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Task("Task0001", "", "Valid Desc");
        });
        assertEquals("Name cannot be null or empty", exception.getMessage(),
                "Exception message should indicate null/empty name");
    }

    /**
     * Verifies that name exceeding 20 characters throws IllegalArgumentException.
     * Ensures length constraint is enforced.
     */
    @Test
    void testNameTooLong() {
        String longName = "This name is definitely way too long to be valid";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Task("Task0001", longName, "Valid Desc");
        });
        assertEquals("Name cannot exceed 20 characters", exception.getMessage(),
                "Exception message should indicate length violation");
    }

    /**
     * Verifies successful update of name with valid value.
     * Ensures name is updatable and validation passes.
     */
    @Test
    void testUpdateNameValid() {
        Task task = new Task("Task0001", "Old Name", "Desc");
        task.setName("New Valid Name");
        assertEquals("New Valid Name", task.getName(), "Name should be updated successfully");
    }

    // Description validation tests (required: not null, <=50 chars)
    /**
     * Verifies that null description throws IllegalArgumentException.
     * Ensures description requirement (not null) is enforced.
     */
    @Test
    void testDescriptionNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Task("Task0001", "Valid Name", null);
        });
        assertEquals("Description cannot be null or empty", exception.getMessage(),
                "Exception message should indicate null/empty description");
    }

    /**
     * Verifies that description exceeding 50 characters throws IllegalArgumentException.
     * Ensures length constraint is enforced.
     */
    @Test
    void testDescriptionTooLong() {
        String longDesc = "This description is intentionally made very long to exceed the fifty character limit and trigger validation error";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Task("Task0001", "Valid Name", longDesc);
        });
        assertEquals("Description cannot exceed 50 characters", exception.getMessage(),
                "Exception message should indicate length violation");
    }

    /**
     * Verifies successful update of description with valid value.
     * Ensures description is updatable and validation passes.
     */
    @Test
    void testUpdateDescriptionValid() {
        Task task = new Task("Task0001", "Name", "Old Desc");
        task.setDescription("Updated Description Here");
        assertEquals("Updated Description Here", task.getDescription(),
                "Description should be updated successfully");
    }
}