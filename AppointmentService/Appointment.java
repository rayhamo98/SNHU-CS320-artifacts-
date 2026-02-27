package src.main.java; // Package declaration for the main application code

import java.util.Date; // Importing Date class for appointmentDate field and validation 

/**
 * CS 320 Module Five Milestone
 * Appointment Class
 * 
 * Author: Rimon Hamo
 * Date: February 6, 2026
 * 
 * This class represents a single appointment in the mobile application.
 * It enforces the following requirements:
 * - Unique appointment ID: String, max 10 characters, not null, not updatable
 * - Appointment Date: Date, cannot be in the past, not null
 * - Description: String, max 50 characters, not null
 * 
 * All validations throw IllegalArgumentException with descriptive messages
 * to help identify issues during development and testing.
 */
public class Appointment {
    
    // appointmentId is final to prevent any updates after construction (meets "not updatable" requirement)
    private final String appointmentId; // Unique identifier for the appointment (immutable)
    
    private Date appointmentDate; // Date of the appointment (must be in the future or present, can be updated with validation)
    private String description; // Description of the appointment (max 50 characters, can be updated with validation)

    /**
     * Constructor - Creates a new Appointment object with full validation.
     * 
     * @param appointmentId    Unique identifier (required, max 10 characters, cannot be changed later)
     * @param appointmentDate  Date of the appointment (required, must be in the future or present)
     * @param description      Description of the appointment (required, max 50 characters)
     * @throws IllegalArgumentException if any field violates the requirements
     */
    public Appointment(String appointmentId, Date appointmentDate, String description) { // Constructor with all fields, validates each according to the requirements
        // Validate appointmentId first since it's immutable and required
        if (appointmentId == null || appointmentId.trim().isEmpty()) { // Check for null or blank (using trim() to reject "   " as invalid)
            throw new IllegalArgumentException("Appointment ID cannot be null or empty"); // Clear message indicating the issue with appointmentId
        }
        if (appointmentId.length() > 10) { // Check length without trimming to allow for exact 10-character IDs, but still reject longer ones
            throw new IllegalArgumentException("Appointment ID cannot exceed 10 characters"); // Clear message indicating the issue with appointmentId length
        }
        // Store the original value (no trimming here to preserve exact ID) // This also ensures that the ID is exactly as provided, which can be important for uniqueness and matching in the system
        this.appointmentId = appointmentId; // Set the appointmentId after validation (immutable, so no setter method provided)

        // Validate and set appointmentDate
        setAppointmentDate(appointmentDate); // Use setter to leverage existing validation logic for appointmentDate
        
        // Validate and set description
        setDescription(description); // Use setter to leverage existing validation logic for description
    }

    /**
     * Returns the unique, immutable appointment ID.
     * 
     * @return the appointment ID (guaranteed to be non-null and ≤10 characters)
     */
    public String getAppointmentId() { // Getter for appointmentId, which is immutable and validated in the constructor
        return appointmentId; // Return the appointmentId (guaranteed to be non-null and ≤10 characters due to constructor validation)
    }

    /**
     * Returns the current appointment date.
     * 
     * @return the appointment date (guaranteed to be non-null and not in the past)
     */
    public Date getAppointmentDate() { //   Getter for appointmentDate, which can be updated with validation
        return appointmentDate; // Return the appointmentDate (guaranteed to be non-null and not in the past due to constructor and setter validation)
    }

    /**
     * Updates the appointment date with strict validation.
     * 
     * @param appointmentDate the new date value
     * @throws IllegalArgumentException if date is null or in the past
     */
    public void setAppointmentDate(Date appointmentDate) { // Setter for appointmentDate with validation to ensure it cannot be set to a past date or null
        if (appointmentDate == null) { // Check for null value
            throw new IllegalArgumentException("Appointment date cannot be null"); // Clear message indicating the issue with appointmentDate being null
        }
        if (appointmentDate.before(new Date())) { // Check if the date is in the past (compares to current date and time)
            throw new IllegalArgumentException("Appointment date cannot be in the past"); // Clear message indicating the issue with appointmentDate being in the past, helps developers understand that the date must be current or future
        }
        this.appointmentDate = appointmentDate; // Set the appointmentDate after validation (allows updates, but only to valid future or present dates)
    }

    /**
     * Returns the current appointment description.
     * 
     * @return the description (guaranteed to be non-null and ≤50 characters)
     */
    public String getDescription() { // Getter for description, which can be updated with validation
        return description; // Return the description (guaranteed to be non-null and ≤50 characters due to constructor and setter validation)
    }

    /**
     * Updates the appointment description with strict validation.
     * 
     * @param description the new description value
     * @throws IllegalArgumentException if description is null, empty, or exceeds 50 characters
     */
    public void setDescription(String description) { // Setter for description with validation to ensure it cannot be null, empty, or exceed 50 characters
        // Check for null or blank (using trim() to reject "   " as invalid)
        if (description == null || description.trim().isEmpty()) { // Check for null or blank (using trim() to reject "   " as invalid)
            throw new IllegalArgumentException("Description cannot be null or empty"); // Clear message indicating the issue with description being null or empty, helps developers understand that a meaningful description is required
        }
        // Check length using trimmed value to enforce meaningful content
        if (description.trim().length() > 50) { // Check length of trimmed description to ensure it does not exceed 50 characters, while still allowing for leading/trailing spaces if needed
            throw new IllegalArgumentException("Description cannot exceed 50 characters"); // Clear message indicating the issue with description length, helps developers understand that the description must be concise and within the specified limit
        }
        // Store original input
        this.description = description; // Set the description after validation (allows updates, but only to valid non-null, non-empty, and ≤50 character strings)
    }
}