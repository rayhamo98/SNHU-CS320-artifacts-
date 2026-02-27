/*
 * ContactService.java
 * 
 * Course:      CS320 - Software Testing, Automation, and Quality Assurance
 * (Southern New Hampshire University - SNHU)
 * Assignment:  Contact Service
 * 
 * Author:      Rimon Hamo
 * Date:        February 11, 2026
 * 
 * Description:
 * This class provides a simple in-memory contact management service using a HashMap.
 * It supports the following operations:
 * 
 * - Adding a new contact (unique contactID required)
 * - Deleting a contact by contactID
 * - Updating individual fields of an existing contact:
 *   • firstName
 *   • lastName
 *   • phone
 *   • address
 * - Retrieving a contact by contactID
 * 
 * All operations include appropriate validation and throw IllegalArgumentException
 * with descriptive messages when preconditions are not met.
 * 
 * Note: This is a basic implementation with no persistence — data is lost on restart.
 */
// src/main/java/com/example/ContactService.java
package com.example; // Adjust the package name as needed for your project structure

import java.util.HashMap; // Import HashMap for storing contacts
import java.util.Map; // Import Map interface for type declaration

public class ContactService { // Class definition for ContactService
    private final Map<String, Contact> contacts = new HashMap<>(); // HashMap to store contacts with contactID as key

    public void addContact(Contact contact) { // Method to add a new contact
        if (contact == null) { // Validate that the contact is not null
            throw new IllegalArgumentException("Contact cannot be null."); // Throw an exception if the contact is null
        }
        String id = contact.getContactID(); // Get the contactID from the contact object
        if (contacts.containsKey(id)) { // Check if a contact with the same contactID already exists
            throw new IllegalArgumentException("Contact ID already exists."); // Throw an exception if the contactID is not unique
        }
        contacts.put(id, contact); // Add the contact to the HashMap
    }

    public void deleteContact(String contactID) { // Method to delete a contact by contactID
        if (contactID != null) { // Validate that the contactID is not null
            contacts.remove(contactID); // Remove the contact from the HashMap (no exception thrown if contactID does not exist)
        }
    }

    public void updateFirstName(String contactID, String firstName) { // Method to update the first name of an existing contact
        Contact contact = getExistingContact(contactID); // Retrieve the existing contact using a helper method that validates the contactID
        contact.setFirstName(firstName); // Update the first name of the contact
    }

    public void updateLastName(String contactID, String lastName) { // Method to update the last name of an existing contact
        Contact contact = getExistingContact(contactID); // Retrieve the existing contact using a helper method that validates the contactID
        contact.setLastName(lastName); // Update the last name of the contact
    }

    public void updatePhone(String contactID, String phone) { // Method to update the phone number of an existing contact
        Contact contact = getExistingContact(contactID); // Retrieve the existing contact using a helper method that validates the contactID
        contact.setPhone(phone); // Update the phone number of the contact
    }

    public void updateAddress(String contactID, String address) { // Method to update the address of an existing contact
        Contact contact = getExistingContact(contactID); // Retrieve the existing contact using a helper method that validates the contactID
        contact.setAddress(address); // Update the address of the contact
    }

    private Contact getExistingContact(String contactID) { // Helper method to retrieve an existing contact and validate the contactID
        if (contactID == null) { // Validate that the contactID is not null
            throw new IllegalArgumentException("Contact ID cannot be null."); // Throw an exception if the contactID is null
        }
        Contact contact = contacts.get(contactID); // Retrieve the contact from the HashMap using the contactID
        if (contact == null) { // Check if the contact exists in the HashMap
            throw new IllegalArgumentException("Contact not found."); // Throw an exception if the contact does not exist
        }
        return contact; // Return the existing contact if found
    }

    public Contact getContact(String contactID) { // Method to retrieve a contact by contactID
        if (contactID == null) { // Validate that the contactID is not null
            return null; // Return null if the contactID is null (or you could choose to throw an exception based on your design preference)
        }
        return contacts.get(contactID); // Return the contact from the HashMap (returns null if contactID does not exist)
    }
}