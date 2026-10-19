/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

package TestClasses;

import com.mycompany.chatapplication.Message;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TestMessage {

    @Test
    public void testMessageLengthSuccess() {
        Message msg = new Message(0, "+27834557896", "Hi there");
        String result = msg.checkMessageLength();
        assertEquals("Message sent", result);
    }

    @Test
    public void testMessageLengthFailure() {
        // Create 251 chars
        String longMsg = "a".repeat(251);
        Message msg = new Message(0, "+27834557896", longMsg);
        String result = msg.checkMessageLength();
        assertEquals("Please enter a message of less than 250 characters.", result);
    }

    @Test
    public void testRecipientCorrectlyFormatted() {
        Message msg = new Message(0, "+27834557896", "Test");
        assertTrue(msg.checkRecipientCell());
    }

    @Test
    public void testRecipientIncorrectlyFormatted() {
        Message msg = new Message(0, "0834567890", "Test");
        assertFalse(msg.checkRecipientCell());
    }

    @Test
    public void testMessageHashCreation() {
        // Test Hash format - using fixed ID for predictable test
        Message msg = new Message(0, "+27834557896", "Hi there");
        // Hash should be XX:0:HITHERE format
        String hash = msg.createMessageHash();
        assertTrue(hash.contains(":0:"));
        assertTrue(hash.endsWith("HITHERE"));
    }

    @Test
    public void testMessageIdLength() {
        Message msg = new Message(0, "+27834557896", "Test");
        String id = msg.getMessageId();
        assertEquals(10, id.length());
    }

    @Test
    public void testTotalMessages() {
        int before = Message.returnTotalMessages();
        Message msg = new Message(0, "+27834557896", "Test message");
        msg.checkMessageLength();
        assertEquals(before + 1, Message.returnTotalMessages());
    }
}