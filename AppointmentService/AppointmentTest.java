package src.test.java; // Define the package for this test class, which is typically organized under a test directory structure to separate it from the main application code.

import org.junit.jupiter.api.Test; // Import the JUnit 5 Test annotation to mark methods as test cases in this test class

import src.main.java.Appointment;   // Import the Appointment class from the main package to be tested in this test class
import static org.junit.jupiter.api.Assertions.*; // Import necessary classes for writing JUnit tests and assertions to validate the behavior of the Appointment class in various scenarios.

import java.util.Date; // Import necessary classes for handling dates in the tests
import java.util.Calendar; // Import necessary classes for handling dates and calendar operations in the tests

/**
 * CS 320 Module Five Milestone
 * Appointment Class Unit Tests
 * 
 * Author: Rimon Hamo
 * Date: February 6, 2026
 * 
 * JUnit 5 tests to verify all Appointment class requirements and validations.
 */
class AppointmentTest { // This class contains unit tests for the Appointment class, covering all specified requirements and validation rules to ensure the class behaves as expected under various conditions.
 
    // Helper method to create a valid future date
    private Date getFutureDate() { // This method generates a date that is guaranteed to be in the future, which is necessary for testing the appointment date validation logic.
        Calendar cal = Calendar.getInstance(); // Get the current date and time
        cal.add(Calendar.DAY_OF_YEAR, 1); // Move the calendar forward by one day to create a future date
        return cal.getTime(); // Return a date that is one day in the future
    }

    @Test
    void testValidAppointmentCreation() { // This test verifies that a valid appointment can be created successfully with a proper ID, future date, and description.
        Appointment appt = new Appointment("APPT12345", getFutureDate(), "Routine checkup with Dr. Smith"); // Create a valid appointment with a proper ID, future date, and description
        assertEquals("APPT12345", appt.getAppointmentId()); // Verify that the appointment ID is set correctly
        assertNotNull(appt.getAppointmentDate()); // Verify that the appointment date is not null
        assertEquals("Routine checkup with Dr. Smith", appt.getDescription()); // Verify that a valid appointment is created successfully with the correct ID, date, and description
    }

    @Test
    void testAppointmentIdNull() { // This test verifies that an exception is thrown when attempting to create an appointment with a null ID.
        Exception ex = assertThrows(IllegalArgumentException.class, () -> // Attempt to create an appointment with a null ID
            new Appointment(null, getFutureDate(), "Valid description")); // Attempt to create an appointment with a null ID
        assertEquals("Appointment ID cannot be null or empty", ex.getMessage());  // Verify the correct exception message is thrown when the appointment ID is null
    }

    @Test
    void testAppointmentIdEmpty() { // This test verifies that an exception is thrown when attempting to create an appointment with an empty ID (only whitespace).
        Exception ex = assertThrows(IllegalArgumentException.class, () ->   // Attempt to create an appointment with an empty ID (only whitespace)
            new Appointment("   ", getFutureDate(), "Valid description")); // Attempt to create an appointment with an empty ID (only whitespace) 
        assertEquals("Appointment ID cannot be null or empty", ex.getMessage()); // Verify the correct exception message is thrown when the appointment ID is empty or only whitespace
    }

    @Test
    void testAppointmentIdTooLong() { // This test verifies that an exception is thrown when attempting to create an appointment with an ID that exceeds the 10 character limit.
        Exception ex = assertThrows(IllegalArgumentException.class, () -> // Attempt to create an appointment with an ID that exceeds 10 characters
            new Appointment("12345678901", getFutureDate(), "Valid description")); // Attempt to create an appointment with an ID that exceeds 10 characters
        assertEquals("Appointment ID cannot exceed 10 characters", ex.getMessage()); // Verify the correct exception message is thrown when the appointment ID exceeds the character limit
    }

    @Test
    void testAppointmentDateNull() { // This test verifies that an exception is thrown when attempting to create an appointment with a null date.
        Exception ex = assertThrows(IllegalArgumentException.class, () -> // Attempt to create an appointment with a null date
            new Appointment("APPT001", null, "Valid description")); // Attempt to create an appointment with a null date
        assertEquals("Appointment date cannot be null", ex.getMessage()); // Verify the correct exception message is thrown when the appointment date is null
    }

    @Test
    void testAppointmentDateInPast() { // This test verifies that an exception is thrown when attempting to create an appointment with a date that is in the past.
        Calendar cal = Calendar.getInstance(); // Get the current date and time
        cal.add(Calendar.DAY_OF_YEAR, -1); // Move the calendar back by one day to create a past date
        Date pastDate = cal.getTime(); // Create a date that is in the past

        Exception ex = assertThrows(IllegalArgumentException.class, () -> // Attempt to create an appointment with a past date
            new Appointment("APPT001", pastDate, "Valid description")); // Attempt to create an appointment with a past date
        assertEquals("Appointment date cannot be in the past", ex.getMessage()); // Verify the correct exception message is thrown when the appointment date is in the past
    }

    @Test
    void testDescriptionNull() { // This test verifies that an exception is thrown when attempting to create an appointment with a null description.
        Exception ex = assertThrows(IllegalArgumentException.class, () -> // Attempt to create an appointment with a null description
            new Appointment("APPT001", getFutureDate(), null)); // Attempt to create an appointment with a null description
        assertEquals("Description cannot be null or empty", ex.getMessage()); // Verify the correct exception message is thrown when the description is null
    }

    @Test
    void testDescriptionEmpty() { // This test verifies that an exception is thrown when attempting to create an appointment with an empty description (only whitespace).
        Exception ex = assertThrows(IllegalArgumentException.class, () -> // Attempt to create an appointment with an empty description (only whitespace)
            new Appointment("APPT001", getFutureDate(), "   ")); // Attempt to create an appointment with an empty description (only whitespace)
        assertEquals("Description cannot be null or empty", ex.getMessage()); // Verify the correct exception message is thrown when the description is empty or only whitespace
    }

    @Test
    void testDescriptionTooLong() { // This test verifies that an exception is thrown when attempting to create an appointment with a description that exceeds the 50 character limit.
        String longDesc = "This description is intentionally made very long to exceed the fifty character limit set by the requirement specification document."; // Create a description that exceeds 50 characters
        Exception ex = assertThrows(IllegalArgumentException.class, () -> // Attempt to create an appointment with a description that exceeds the character limit
            new Appointment("APPT001", getFutureDate(), longDesc)); // Attempt to create an appointment with a description that exceeds the character limit
        assertEquals("Description cannot exceed 50 characters", ex.getMessage()); // Verify the correct exception message is thrown when the description exceeds the character limit
    }

    @Test
    void testSetAppointmentDateValid() { // This test verifies that the setAppointmentDate method correctly updates the appointment date when given a valid future date.
        Appointment appt = new Appointment("APPT001", getFutureDate(), "Original desc"); // Create a valid appointment
        Date newDate = getFutureDate(); // another future date
        appt.setAppointmentDate(newDate); // Update the appointment date with a valid future date
        assertEquals(newDate, appt.getAppointmentDate()); // Verify the appointment date was updated correctly
    }

    @Test
    void testSetDescriptionValid() { // This test verifies that the setDescription method correctly updates the description when given a valid string.
        Appointment appt = new Appointment("APPT001", getFutureDate(), "Original desc"); // Create a valid appointment
        appt.setDescription("Updated description here"); // Update the description with a valid string
        assertEquals("Updated description here", appt.getDescription()); // Verify the description was updated correctly
    }
}