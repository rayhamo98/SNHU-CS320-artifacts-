package src.main.java; // Package declaration for main application classes

import java.util.HashMap; // Importing HashMap for in-memory storage of appointments
import java.util.Map; // Importing Map interface for type declaration of appointments storage

/**
 * CS 320 Module Five Milestone
 * Appointment Service Class
 * 
 * Author: Rimon Hamo
 * Date: February 6, 2026
 * 
 * This class provides in-memory management of Appointment objects.
 * It supports adding and deleting appointments with the following requirements:
 * - Add appointments with a unique appointment ID
 * - Delete appointments by appointment ID
 * 
 * Uses a HashMap for storage (no database required).
 * All operations throw IllegalArgumentException with descriptive messages
 * on invalid input or violations.
 */
public class AppointmentService { // Class declaration
    
    // In-memory storage: appointmentId → Appointment
    private final Map<String, Appointment> appointments = new HashMap<>(); // HashMap to store appointments by their unique ID

    /**
     * Adds a new appointment to the service.
     * The appointment's ID must be unique (not already in the service).
     * 
     * @param appointment the Appointment object to add
     * @throws IllegalArgumentException if appointment is null or ID already exists
     */
    public void addAppointment(Appointment appointment) { // Method to add a new appointment
        if (appointment == null) {
            throw new IllegalArgumentException("Appointment cannot be null"); // Check for null appointment and throw exception if invalid
        }
        
        String id = appointment.getAppointmentId(); // Get the appointment ID from the Appointment object
        if (appointments.containsKey(id)) { // Check if the appointment ID already exists in the service
            throw new IllegalArgumentException("Appointment ID already exists: " + id); //  Throw exception if the ID is not unique
        }
        
        appointments.put(id, appointment); // Add the appointment to the HashMap using its ID as the key
    }

    /**
     * Deletes an appointment by its unique ID.
     * 
     * @param appointmentId the ID of the appointment to delete
     * @throws IllegalArgumentException if ID is null or does not exist
     */
    public void deleteAppointment(String appointmentId) { // Method to delete an appointment by its ID
        if (appointmentId == null || appointmentId.trim().isEmpty()) { // Check if the appointment ID is null or empty and throw exception if invalid
            throw new IllegalArgumentException("Appointment ID cannot be null or empty"); // Validate that the appointment ID is not null or empty
        }
        
        if (!appointments.containsKey(appointmentId)) { // Check if the appointment ID exists in the service
            throw new IllegalArgumentException("Appointment ID not found: " + appointmentId); // Throw exception if the appointment ID does not exist in the service
        }
        
        appointments.remove(appointmentId); // Remove the appointment from the HashMap using its ID as the key
    }

    /**
     * Retrieves an appointment by its ID (for testing and verification purposes).
     * 
     * @param appointmentId the ID to look up
     * @return the Appointment object, or null if not found
     */
    public Appointment getAppointment(String appointmentId) { // Method to retrieve an appointment by its ID, primarily for testing and verification
        return appointments.get(appointmentId); // Return the appointment from the HashMap using its ID as the key, or null if not found
    }
}