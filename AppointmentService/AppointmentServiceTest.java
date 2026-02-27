package src.test.java; // Note: The package declaration may need to be adjusted based on your project structure.

import org.junit.jupiter.api.Test; // JUnit 5 import

import src.main.java.Appointment; // Importing the Appointment class to create test instances
import src.main.java.AppointmentService; // Importing the AppointmentService class to test its functionality

import static org.junit.jupiter.api.Assertions.*; // Importing static assertions for cleaner test code

import java.util.Date; // Importing Date class for handling appointment dates
import java.util.Calendar; // Importing Calendar class to manipulate dates for testing purposes

/**
 * CS 320 Module Five Milestone
 * Appointment Service Class Unit Tests
 * 
 * Author: Rimon Hamo
 * Date: February 6, 2026
 * 
 * JUnit 5 tests to verify AppointmentService add and delete functionality.
 */
class AppointmentServiceTest { // Test class for AppointmentService, containing unit tests for adding and deleting appointments.

    // Helper method to create a valid future date
    private Date getFutureDate() { // This method generates a Date object that is set to one day in the future, ensuring that it meets the requirement for appointment dates.
        Calendar cal = Calendar.getInstance(); // Get the current date and time
        cal.add(Calendar.DAY_OF_YEAR, 1); // Add one day to the current date to ensure it's in the future
        return cal.getTime(); // Return the future date as a Date object
    }

    @Test
    void testAddAppointmentSuccess() { // Test case to verify that a valid appointment can be added successfully to the AppointmentService.
        AppointmentService service = new AppointmentService(); // Create a new instance of AppointmentService to test its functionality.
        Appointment appt = new Appointment("A001", getFutureDate(), "Team meeting"); // Create a new Appointment object with a valid ID, future date, and description.
        service.addAppointment(appt);// Add the appointment to the service.
        assertNotNull(service.getAppointment("A001")); // Assert that the appointment can be retrieved successfully using its ID, confirming that it was added to the service.
        assertEquals("Team meeting", service.getAppointment("A001").getDescription()); // Assert that the description of the retrieved appointment matches the expected value, confirming that the correct appointment was added.
    }

    @Test
    void testAddDuplicateAppointmentId() { // Test case to verify that adding an appointment with a duplicate ID throws an exception, ensuring that the service enforces unique appointment IDs.
        AppointmentService service = new AppointmentService(); // Create a new instance of AppointmentService to test its functionality.
        Appointment appt1 = new Appointment("A001", getFutureDate(), "Meeting 1"); // Create the first Appointment object with a specific ID, future date, and description.
        Appointment appt2 = new Appointment("A001", getFutureDate(), "Meeting 2"); // Create a second Appointment object with the same ID as the first one, but with a different description to test the duplicate ID scenario.

        service.addAppointment(appt1); // Add the first appointment to the service, which should succeed without any issues.

        Exception ex = assertThrows(IllegalArgumentException.class, () -> // Attempt to add the second appointment with the duplicate ID, which should throw an IllegalArgumentException.
            service.addAppointment(appt2)); // Capture the exception thrown when trying to add the duplicate appointment.
        assertEquals("Appointment ID already exists: A001", ex.getMessage()); // Assert that the exception message matches the expected message, confirming that the service correctly identifies and handles duplicate appointment IDs.
    }

    @Test
    void testAddNullAppointment() { // Test case to verify that adding a null appointment throws an exception, ensuring that the service does not accept null values for appointments.
        AppointmentService service = new AppointmentService(); // Create a new instance of AppointmentService to test its functionality.
        Exception ex = assertThrows(IllegalArgumentException.class, () -> // Attempt to add a null appointment, which should throw an IllegalArgumentException.
            service.addAppointment(null)); // Capture the exception thrown when trying to add a null appointment.
        assertEquals("Appointment cannot be null", ex.getMessage()); // Assert that the exception message matches the expected message, confirming that the service correctly identifies and handles null appointment inputs.
    }

    @Test
    void testDeleteAppointmentSuccess() { // Test case to verify that an existing appointment can be deleted successfully from the AppointmentService.
        AppointmentService service = new AppointmentService(); // Create a new instance of AppointmentService to test its functionality.
        Appointment appt = new Appointment("A002", getFutureDate(), "Dentist visit"); // Create a new Appointment object with a valid ID, future date, and description.
        service.addAppointment(appt); // Add the appointment to the service to set up the test scenario for deletion.

        service.deleteAppointment("A002"); // Delete the appointment using its ID, which should succeed without any issues.
        assertNull(service.getAppointment("A002")); // Assert that the appointment can no longer be retrieved using its ID, confirming that it was successfully deleted from the service.
    }

    @Test
    void testDeleteNonExistentAppointment() { // Test case to verify that attempting to delete an appointment that does not exist throws an exception, ensuring that the service correctly handles deletion of non-existent appointments.
        AppointmentService service = new AppointmentService(); // Create a new instance of AppointmentService to test its functionality.
        Exception ex = assertThrows(IllegalArgumentException.class, () -> // Attempt to delete an appointment with an ID that does not exist in the service, which should throw an IllegalArgumentException.
            service.deleteAppointment("NONEXIST")); // Capture the exception thrown when trying to delete a non-existent appointment.
        assertEquals("Appointment ID not found: NONEXIST", ex.getMessage()); // Assert that the exception message matches the expected message, confirming that the service correctly identifies and handles attempts to delete non-existent appointments.
    }

    @Test
    void testDeleteNullAppointmentId() { // Test case to verify that attempting to delete an appointment with a null ID throws an exception, ensuring that the service does not accept null values for appointment IDs during deletion.
        AppointmentService service = new AppointmentService(); // Create a new instance of AppointmentService to test its functionality. 
        Exception ex = assertThrows(IllegalArgumentException.class, () -> // Attempt to delete an appointment using a null ID, which should throw an IllegalArgumentException.
            service.deleteAppointment(null)); // Capture the exception thrown when trying to delete an appointment with a null ID.
        assertEquals("Appointment ID cannot be null or empty", ex.getMessage()); // Assert that the exception message matches the expected message, confirming that the service correctly identifies and handles null appointment ID inputs during deletion.
    }

    @Test
    void testDeleteEmptyAppointmentId() { // Test case to verify that attempting to delete an appointment with an empty ID throws an exception, ensuring that the service does not accept empty strings for appointment IDs during deletion.
        AppointmentService service = new AppointmentService(); // Create a new instance of AppointmentService to test its functionality.
        Exception ex = assertThrows(IllegalArgumentException.class, () -> // Attempt to delete an appointment using an empty ID, which should throw an IllegalArgumentException.
            service.deleteAppointment("   ")); // Capture the exception thrown when trying to delete an appointment with an empty ID (consisting of whitespace).
        assertEquals("Appointment ID cannot be null or empty", ex.getMessage()); // Assert that the exception message matches the expected message, confirming that the service correctly identifies and handles empty appointment ID inputs during deletion.
    }
}