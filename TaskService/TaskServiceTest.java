package src.test.java;

import src.main.java.Task;
import src.main.java.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * CS 320 Module Four Milestone - TaskServiceTest
 * 
 * Author: Rimon Hamo
 * Date: January 28, 2026
 * 
 * JUnit 5 tests for the TaskService class.
 * This test suite verifies all required functionality:
 * - Adding tasks with unique IDs (rejects duplicates)
 * - Deleting tasks by ID (returns success/failure)
 * - Updating name and/or description by ID (partial updates allowed)
 * - Proper handling of invalid or non-existent tasks
 * 
 * Uses @BeforeEach to create a fresh TaskService instance for each test,
 * ensuring test isolation and no state leakage.
 */
public class TaskServiceTest {

    private TaskService service;

    /**
     * Runs before each test to initialize a clean TaskService instance.
     * Prevents tests from affecting each other.
     */
    @BeforeEach
    void setUp() {
        service = new TaskService();
    }

    /**
     * Verifies successful addition of a task with a unique ID.
     * Checks that the task can be retrieved after addition.
     */
    @Test
    void testAddTaskSuccess() {
        Task task = new Task("T001", "Write Code", "Implement unit tests");
        service.addTask(task);
        assertEquals(task, service.getTask("T001"), "Added task should be retrievable by ID");
    }

    /**
     * Verifies that adding a task with a duplicate ID throws an exception.
     * Ensures uniqueness requirement is enforced.
     */
    @Test
    void testAddTaskDuplicateId() {
        Task task1 = new Task("T001", "Task One", "Desc 1");
        Task task2 = new Task("T001", "Task Two", "Desc 2");

        service.addTask(task1);  // First task succeeds

        // Second task with same ID should fail
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.addTask(task2);
        });
        assertEquals("Task ID already exists: T001", exception.getMessage(),
                "Exception message should indicate duplicate ID");
    }

    /**
     * Verifies that adding a null task throws an exception.
     * Prevents invalid data from entering the service.
     */
    @Test
    void testAddTaskNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.addTask(null);
        });
        assertEquals("Task cannot be null", exception.getMessage(),
                "Null task should not be accepted");
    }

    /**
     * Verifies successful deletion of an existing task.
     * Checks return value and confirms task is removed.
     */
    @Test
    void testDeleteTaskSuccess() {
        Task task = new Task("T002", "Delete Me", "To be removed");
        service.addTask(task);
        
        boolean deleted = service.deleteTask("T002");
        assertTrue(deleted, "deleteTask should return true when task is found and removed");
        assertNull(service.getTask("T002"), "Deleted task should no longer exist");
    }

    /**
     * Verifies that deleting a non-existent task returns false (silent fail).
     * Ensures no exception is thrown for missing tasks.
     */
    @Test
    void testDeleteTaskNonExistent() {
        boolean deleted = service.deleteTask("NonExistentID");
        assertFalse(deleted, "deleteTask should return false when task is not found");
        // No exception should be thrown
    }

    /**
     * Verifies full update of both name and description for an existing task.
     * Ensures updates are applied correctly.
     */
    @Test
    void testUpdateTaskSuccess() {
        Task task = new Task("T003", "Original Name", "Original Desc");
        service.addTask(task);

        service.updateTask("T003", "Updated Name", "Updated Desc");
        Task updated = service.getTask("T003");

        assertEquals("Updated Name", updated.getName(), "Name should be updated");
        assertEquals("Updated Desc", updated.getDescription(), "Description should be updated");
    }

    /**
     * Verifies partial update (only one field changed).
     * Ensures unchanged fields remain intact.
     */
    @Test
    void testUpdateTaskPartial() {
        Task task = new Task("T004", "Name", "Desc");
        service.addTask(task);

        service.updateTask("T004", "New Name", null);  // Only update name
        Task updated = service.getTask("T004");

        assertEquals("New Name", updated.getName(), "Name should be updated");
        assertEquals("Desc", updated.getDescription(), "Description should remain unchanged");
    }

    /**
     * Verifies that updating a non-existent task throws an exception.
     * Ensures updates fail safely for invalid IDs.
     */
    @Test
    void testUpdateTaskNonExistent() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.updateTask("FakeID", "New Name", "New Desc");
        });
        assertEquals("Task not found with ID: FakeID", exception.getMessage(),
                "Exception message should indicate task not found");
    }
}