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
    public void testMessageIDLength() {
        Message msg = new Message("1", "+27838968976", "Hi there");
        assertEquals(10, msg.getMessageID().length());
    }

    @Test
    public void testRecipientCellValid() {
        Message msg = new Message(); // Now works with empty constructor
        assertTrue(msg.checkRecipientCell("+27838968976"));
    }

    @Test
    public void testRecipientCellInvalid() {
        Message msg = new Message();
        assertFalse(msg.checkRecipientCell("0838968976"));
    }

    @Test
    public void testRecipientCellTooLong() {
        Message msg = new Message();
        assertFalse(msg.checkRecipientCell("+2783896897612345"));
    }

    @Test
    public void testCreateMessageHash() {
        Message msg = new Message("1", "+27838968976", "Hi Mike");
        msg.setMessageID("0012345678");
        String hash = msg.createMessageHash();
        assertNotNull(hash);
    }

    @Test
    public void testSentMessageOption1() {
        Message msg = new Message("1", "+27838968976", "Hello");
        assertEquals("Message successfully sent.", msg.sentMessage(1));
    }

    @Test
    public void testSentMessageOption2() {
        Message msg = new Message("1", "+27838968976", "Hello");
        assertEquals("Press 0 to delete message.", msg.sentMessage(2));
    }

    @Test
    public void testSentMessageOption3() {
        Message msg = new Message("1", "+27838968976", "Hello");
        assertEquals("Message successfully stored.", msg.sentMessage(3));
    }
}