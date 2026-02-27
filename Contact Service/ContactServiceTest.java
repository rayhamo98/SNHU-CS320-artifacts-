/*
 * ContactServiceTest.java
 * 
 * Course:      CS320 - Software Testing, Automation, and Quality Assurance
 *              Southern New Hampshire University (SNHU)
 * Assignment:  Contact Service - Unit Tests for ContactService Class
 * 
 * Author:      Rimon Hamo
 * Date:        February 11, 2026
 * Description:
 * JUnit 5 test class for the ContactService class.
 * This test suite verifies the core functionality of the contact management service:
 * 
 * - Adding a new contact successfully
 * - Preventing addition of duplicate contact IDs
 * - Deleting an existing contact
 * - Handling deletion of non-existent contacts (no exception expected)
 * - Updating all mutable fields (firstName, lastName, phone, address)
 * - Attempting to update non-existent contacts (throws exception)
 * - Handling invalid data during updates (throws exception from Contact validation)
 * - Managing multiple contacts correctly
 * 
 * Uses @BeforeEach to create a fresh ContactService instance for each test.
 * Relies on proper exception throwing and state verification via getters.
 */
// src/test/java/com/example/ContactServiceTest.java
package com.example; // Adjust the package name as needed

import org.junit.jupiter.api.BeforeEach; // Importing JUnit 5 annotations and assertions for testing
import org.junit.jupiter.api.Test; // Importing the Test annotation for marking test methods
import static org.junit.jupiter.api.Assertions.*; // Importing static assertions for easier test validation

public class ContactServiceTest {            // Test class for ContactService, contains unit tests to verify the functionality of the contact management service
    private ContactService service; // Instance of ContactService to be used in tests, initialized before each test method

    @BeforeEach // Method annotated with @BeforeEach to set up a new ContactService instance before each test, ensuring test isolation
    void setUp() { // Method to initialize the ContactService instance before each test
        service = new ContactService(); // Create a new instance of ContactService for each test to ensure a clean state
    }

    @Test
    void testAddContactSuccess() {   // Test method to verify that a contact can be added successfully and retrieved correctly
        Contact contact = new Contact("123", "John", "Doe", "1234567890", "123 Main St"); // Create a new Contact object with valid data
        service.addContact(contact);    // Add the contact to the service
        assertEquals(contact, service.getContact("123"));   // Assert that the contact retrieved by ID matches the contact that was added
    }

    @Test
    void testAddDuplicateContactID() {  // Test method to verify that adding a contact with a duplicate ID throws an IllegalArgumentException
        Contact contact1 = new Contact("123", "John", "Doe", "1234567890", "123 Main St"); // Create the first Contact object with a specific ID
        service.addContact(contact1);   // Add the first contact to the service
        Contact contact2 = new Contact("123", "Jane", "Smith", "0987654321", "456 Oak Ave"); // Create a second Contact object with the same ID but different data
        assertThrows(IllegalArgumentException.class, () -> service.addContact(contact2)); // Assert that adding the second contact with a duplicate ID throws an IllegalArgumentException
    }

    @Test
    void testDeleteContact() {  // Test method to verify that a contact can be deleted successfully and is no longer retrievable
        Contact contact = new Contact("123", "John", "Doe", "1234567890", "123 Main St");  // Create a new Contact object with valid data   
        service.addContact(contact);  // Add the contact to the service
        service.deleteContact("123"); // Delete the contact by ID
        assertNull(service.getContact("123")); // Assert that retrieving the contact by ID after deletion returns null, indicating it was successfully deleted
    }

    @Test
    void testDeleteNonExistentContact() { // Test method to verify that attempting to delete a non-existent contact does not throw an exception and does not affect existing contacts
        service.deleteContact("999"); // Attempt to delete a contact with an ID that does not exist in the service
        assertTrue(true); // If no exception is thrown, the test passes. This assertion is just a placeholder to indicate the test succeeded.
    }

    @Test
    void testUpdateAllFields() {    // Test method to verify that all mutable fields of a contact can be updated successfully and that the updates are reflected when retrieving the contact
        Contact contact = new Contact("123", "John", "Doe", "1234567890", "Old Address"); // Create a new Contact object with initial data
        service.addContact(contact); // Add the contact to the service

        service.updateFirstName("123", "Jane");         // Update the first name of the contact with ID "123" to "Jane"
        service.updateLastName("123", "Smith");        // Update the last name of the contact with ID "123" to "Smith"
        service.updatePhone("123", "0987654321"); // Update the phone number of the contact with ID "123" to "0987654321"
        service.updateAddress("123", "New Address"); // Update the address of the contact with ID "123" to "New Address"
 
        Contact updated = service.getContact("123"); // Retrieve the updated contact by ID to verify that the updates were applied correctly
        assertEquals("Jane", updated.getFirstName());       // Assert that the first name of the updated contact is "Jane"
        assertEquals("Smith", updated.getLastName());      // Assert that the last name of the updated contact is "Smith"
        assertEquals("0987654321", updated.getPhone());  // Assert that the phone number of the updated contact is "0987654321"
        assertEquals("New Address", updated.getAddress()); // Assert that the address of the updated contact is "New Address"
    }

    @Test
    void testUpdateNonExistentContact() { // Test method to verify that attempting to update a non-existent contact throws an IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> service.updateFirstName("999", "Test")); // Assert that updating the first name of a contact with a non-existent ID throws an IllegalArgumentException
    }

    @Test
    void testUpdateWithInvalidDataThrows() { // Test method to verify that attempting to update a contact with invalid data (e.g., a first name that exceeds the maximum length) throws an IllegalArgumentException from the Contact class validation
        Contact contact = new Contact("123", "John", "Doe", "1234567890", "Address"); // Create a new Contact object with valid data
        service.addContact(contact); // Add the contact to the service
 
        assertThrows(IllegalArgumentException.class, () -> service.updateFirstName("123", "WayTooLongName")); // Assert that updating the first name with a value that exceeds the maximum length throws an IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> service.updatePhone("123", "abc")); // Assert that updating the phone number with a non-numeric value throws an IllegalArgumentException
    }

    @Test
    void testMultipleContacts() { // Test method to verify that the ContactService can manage multiple contacts correctly, allowing for adding and retrieving multiple contacts without interference
        Contact c1 = new Contact("1", "John", "Doe", "1234567890", "Addr1"); // Create the first Contact object with a unique ID and valid data
        Contact c2 = new Contact("2", "Jane", "Smith", "0987654321", "Addr2"); // Create the second Contact object with a different unique ID and valid data
        service.addContact(c1); // Add the first contact to the service
        service.addContact(c2); // Add the second contact to the service

        assertEquals(c1, service.getContact("1")); // Assert that retrieving the first contact by its ID returns the correct contact object
        assertEquals(c2, service.getContact("2")); // Assert that retrieving the second contact by its ID returns the correct contact object
    }
}