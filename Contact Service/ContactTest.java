/*
 * ContactTest.java
 * 
 * Course:      CS320 - Software Testing, Automation, and Quality Assurance
 *              Southern New Hampshire University (SNHU)
 * Assignment:  Contact Service - Unit Tests for Contact Class
 * 
 * Author:      Rimon Hamo
 * Date:        February 11, 2026
 * 
 * Description:
 * JUnit 5 test class for the Contact class.
 * This test suite verifies:
 * 
 * - Successful creation of a Contact with valid inputs
 * - Proper rejection (IllegalArgumentException) for invalid inputs during construction:
 *   • contactID: null, empty, or >10 characters
 *   • firstName: null, empty, or >10 characters
 *   • lastName: null, empty, or >10 characters
 *   • phone: not exactly 10 digits, non-numeric characters
 *   • address: null, empty, or >30 characters
 * 
 * - Successful updates via setters with valid values
 * - Proper rejection via setters with invalid values
 * 
 * All tests use assertEquals for getters and assertThrows for expected exceptions.
 */
// src/test/java/com/example/ContactTest.java
package com.example; // Adjust the package name as needed

import org.junit.jupiter.api.Test; // JUnit 5 annotation for test methods
import static org.junit.jupiter.api.Assertions.*; // Static import for assertion methods

public class ContactTest { // Test class for the Contact class

    @Test // Test method for valid contact creation
    void testValidContactCreation() { // Test that a Contact can be created with valid inputs
        Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Main St New Haven"); // Create a Contact with valid parameters
        assertEquals("12345", contact.getContactID()); // Verify that the contactID is set correctly
        assertEquals("John", contact.getFirstName()); // Verify that the firstName is set correctly
        assertEquals("Doe", contact.getLastName()); // Verify that the lastName is set correctly
        assertEquals("1234567890", contact.getPhone()); // Verify that the phone number is set correctly
        assertEquals("123 Main St New Haven", contact.getAddress()); // Verify that the address is set correctly
    }

    @Test
    void testContactIDTooLong() { // Test that an exception is thrown when contactID is longer than 10 characters
        assertThrows(IllegalArgumentException.class, () -> // Expect an IllegalArgumentException when creating a Contact with a contactID longer than 10 characters
            new Contact("12345678901", "John", "Doe", "1234567890", "123 Main St")); // Attempt to create a Contact with an invalid contactID
    }

    @Test
    void testContactIDNull() { // Test that an exception is thrown when contactID is null
        assertThrows(IllegalArgumentException.class, () -> // Expect an IllegalArgumentException when creating a Contact with a null contactID
            new Contact(null, "John", "Doe", "1234567890", "123 Main St")); // Attempt to create a Contact with a null contactID
    }

    @Test
    void testContactIDEmpty() { // Test that an exception is thrown when contactID is empty
        assertThrows(IllegalArgumentException.class, () -> // Expect an IllegalArgumentException when creating a Contact with an empty contactID
            new Contact("", "John", "Doe", "1234567890", "123 Main St")); // Attempt to create a Contact with an empty contactID
    }

    @Test
    void testFirstNameTooLong() { // Test that an exception is thrown when firstName is longer than 10 characters
        assertThrows(IllegalArgumentException.class, () -> // Expect an IllegalArgumentException when creating a Contact with a firstName longer than 10 characters
            new Contact("1", "Johnnnnnnnnn", "Doe", "1234567890", "123 Main St")); // Attempt to create a Contact with an invalid firstName
    }

    @Test
    void testFirstNameNullOrEmpty() { // Test that an exception is thrown when firstName is null or empty
        assertThrows(IllegalArgumentException.class, () -> // Expect an IllegalArgumentException when creating a Contact with a null firstName
            new Contact("1", null, "Doe", "1234567890", "123 Main St")); // Attempt to create a Contact with a null firstName
        assertThrows(IllegalArgumentException.class, () -> // Expect an IllegalArgumentException when creating a Contact with an empty firstName
            new Contact("1", "", "Doe", "1234567890", "123 Main St")); // Attempt to create a Contact with an empty firstName
    }

    @Test
    void testLastNameTooLong() { // Test that an exception is thrown when lastName is longer than 10 characters
        assertThrows(IllegalArgumentException.class, () -> //   Expect an IllegalArgumentException when creating a Contact with a lastName longer than 10 characters
            new Contact("1", "John", "Doeeeeeeeee", "1234567890", "123 Main St")); // Attempt to create a Contact with an invalid lastName
    }

    @Test
    void testPhoneWrongLength() { // Test that an exception is thrown when phone number is not exactly 10 digits
        assertThrows(IllegalArgumentException.class, () -> // Expect an IllegalArgumentException when creating a Contact with a phone number that is not exactly 10 digits
            new Contact("1", "John", "Doe", "123456789", "123 Main St")); // Attempt to create a Contact with a phone number that is too short
        assertThrows(IllegalArgumentException.class, () -> // Expect an IllegalArgumentException when creating a Contact with a phone number that is too long
            new Contact("1", "John", "Doe", "12345678901", "123 Main St")); // Attempt to create a Contact with a phone number that is too long
    }

    @Test
    void testPhoneNonDigits() { // Test that an exception is thrown when phone number contains non-digit characters
        assertThrows(IllegalArgumentException.class, () -> // Expect an IllegalArgumentException when creating a Contact with a phone number that contains non-digit characters
            new Contact("1", "John", "Doe", "12345abcde", "123 Main St")); // Attempt to create a Contact with a phone number that contains non-digit characters
    }

    @Test
    void testAddressTooLong() { // Test that an exception is thrown when address is longer than 30 characters
        String longAddress = "This address is definitely longer than thirty characters"; // Create a string that is longer than 30 characters
        assertThrows(IllegalArgumentException.class, () -> // Expect an IllegalArgumentException when creating a Contact with an address longer than 30 characters
            new Contact("1", "John", "Doe", "1234567890", longAddress)); // Attempt to create a Contact with an invalid address
    }

    @Test
    void testSettersValid() { // Test that setters correctly update the fields with valid values
        Contact contact = new Contact("1", "John", "Doe", "1234567890", "Old Address"); // Create a Contact with valid parameters
        contact.setFirstName("Jane"); //    Update the firstName using the setter
        contact.setLastName("Smith"); // Update the lastName using the setter
        contact.setPhone("0987654321"); // Update the phone number using the setter
        contact.setAddress("New Address"); // Update the address using the setter
        assertEquals("Jane", contact.getFirstName()); // Verify that the firstName was updated correctly
        assertEquals("Smith", contact.getLastName()); // Verify that the lastName was updated correctly
        assertEquals("0987654321", contact.getPhone()); // Verify that the phone number was updated correctly
        assertEquals("New Address", contact.getAddress()); // Verify that the address was updated correctly
    }

    @Test
    void testSettersInvalid() { // Test that setters throw exceptions when given invalid values
        Contact contact = new Contact("1", "John", "Doe", "1234567890", "Address"); // Create a Contact with valid parameters
        assertThrows(IllegalArgumentException.class, () -> contact.setFirstName("TooLongName")); // Expect an IllegalArgumentException when setting a firstName that is too long
        assertThrows(IllegalArgumentException.class, () -> contact.setPhone("123")); // Expect an IllegalArgumentException when setting a phone number that is too short
        assertThrows(IllegalArgumentException.class, () -> contact.setAddress(null)); // Expect an IllegalArgumentException when setting a null address
    }
}