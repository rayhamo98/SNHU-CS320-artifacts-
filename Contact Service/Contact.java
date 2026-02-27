/*
 * Contact.java
 * 
 * Course:      CS320 - Software Testing, Automation, and Quality Assurance
 *              Southern New Hampshire University (SNHU)
 * Assignment:  Contact Service
 * 
 * Author:      Rimon Hamo
 * Date:        February 11, 2026
 * 
 * Description:
 * This class represents a simple Contact object with the following fields:
 * 
 * - contactID   (immutable, unique identifier, 1–10 characters)
 * - firstName   (1–10 characters)
 * - lastName    (1–10 characters)
 * - phone       (exactly 10 digits, no formatting characters)
 * - address     (1–30 characters)
 * 
 * All fields are validated during object construction and when using setters.
 * Validation failures throw IllegalArgumentException with descriptive messages.
 * The contactID field is final and cannot be modified after creation.
 */
// src/main/java/com/example/Contact.java
package com.example; // Adjust the package name as needed

public class Contact { // Class representing a contact with validation for each field
    private final String contactID; // Unique identifier for the contact, immutable after creation
    private String firstName; // First name of the contact, must be 1-10 characters
    private String lastName; // Last name of the contact, must be 1-10 characters
    private String phone; // Phone number of the contact, must be exactly 10 digits
    private String address; // Address of the contact, must be 1-30 characters

    public Contact(String contactID, String firstName, String lastName, String phone, String address) { // Constructor to initialize a Contact object with validation
        validateContactID(contactID); // Validate contactID before assignment
        validateFirstName(firstName); // Validate firstName before assignment
        validateLastName(lastName); // Validate lastName before assignment
        validatePhone(phone); // Validate phone before assignment 
        validateAddress(address); // Validate address before assignment

        this.contactID = contactID; // Assign validated contactID to the final field
        this.firstName = firstName; // Assign validated firstName to the field
        this.lastName = lastName; // Assign validated lastName to the field
        this.phone = phone; // Assign validated phone to the field
        this.address = address; // Assign validated address to the field
    }

    private void validateContactID(String contactID) { // Validation method for contactID to ensure it meets the specified criteria
        if (contactID == null || contactID.isEmpty() || contactID.length() > 10) { // Check if contactID is null, empty, or exceeds 10 characters
            throw new IllegalArgumentException("Contact ID must be non-null, non-empty, and no longer than 10 characters."); // Throw an exception with a descriptive message if validation fails
        }
    }

    private void validateFirstName(String firstName) { // Validation method for firstName to ensure it meets the specified criteria
        if (firstName == null || firstName.isEmpty() || firstName.length() > 10) { // Check if firstName is null, empty, or exceeds 10 characters
            throw new IllegalArgumentException("First name must be non-null, non-empty, and no longer than 10 characters."); // Throw an exception with a descriptive message if validation fails
        }
    }

    private void validateLastName(String lastName) { // Validation method for lastName to ensure it meets the specified criteria
        if (lastName == null || lastName.isEmpty() || lastName.length() > 10) {  // Check if lastName is null, empty, or exceeds 10 characters
            throw new IllegalArgumentException("Last name must be non-null, non-empty, and no longer than 10 characters."); // Throw an exception with a descriptive message if validation fails
        }
    }

    private void validatePhone(String phone) { // Validation method for phone to ensure it meets the specified criteria
        if (phone == null || !phone.matches("\\d{10}")) { // Check if phone is null or does not match the regex for exactly 10 digits
            throw new IllegalArgumentException("Phone must be exactly 10 digits."); // Throw an exception with a descriptive message if validation fails
        }
    }

    private void validateAddress(String address) { // Validation method for address to ensure it meets the specified criteria
        if (address == null || address.isEmpty() || address.length() > 30) { // Check if address is null, empty, or exceeds 30 characters
            throw new IllegalArgumentException("Address must be non-null, non-empty, and no longer than 30 characters."); // Throw an exception with a descriptive message if validation fails
        }
    }

    public String getContactID() { // Getter method for contactID, returns the unique identifier of the contact
        return contactID; // Return the contactID, which is immutable and cannot be changed after creation
    }

    public String getFirstName() { // Getter method for firstName, returns the first name of the contact
        return firstName;// Return the firstName of the contact
    }

    public void setFirstName(String firstName) { // Setter method for firstName, allows updating the first name of the contact with validation
        validateFirstName(firstName); // Validate the new firstName before assignment
        this.firstName = firstName; // Assign the validated firstName to the field
    }

    public String getLastName() { // Getter method for lastName, returns the last name of the contact
        return lastName; // Return the lastName of the contact
    }

    public void setLastName(String lastName) { // Setter method for lastName, allows updating the last name of the contact with validation
        validateLastName(lastName); // Validate the new lastName before assignment
        this.lastName = lastName;   // Assign the validated lastName to the field
    }

    public String getPhone() {  // Getter method for phone, returns the phone number of the contact
        return phone;       // Return the phone number of the contact
    }

    public void setPhone(String phone) {    // Setter method for phone, allows updating the phone number of the contact with validation
        validatePhone(phone);   // Validate the new phone number before assignment
        this.phone = phone;     // Assign the validated phone number to the field
    }

    public String getAddress() {    // Getter method for address, returns the address of the contact
        return address; // Return the address of the contact
    }

    public void setAddress(String address) {   // Setter method for address, allows updating the address of the contact with validation
        validateAddress(address);   // Validate the new address before assignment
        this.address = address;  // Assign the validated address to the field
    }
}