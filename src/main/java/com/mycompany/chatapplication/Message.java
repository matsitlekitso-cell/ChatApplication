/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Student
 */
package com.mycompany.chatapplication;

public class Message {
    private String messageId;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    private static int totalMessages = 0;
    private static int totalSent = 0;

    public Message(int messageNumber, String recipient, String messageText) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageId = generateMessageId();
        this.messageHash = createMessageHash();
    }

    // Page 12: 10-digit random Message ID
    public String generateMessageId() {
        java.util.Random rand = new java.util.Random();
        long id = 1000000000L + (long)(rand.nextDouble() * 9000000000L);
        return String.valueOf(id);
    }

    // Page 12: Recipient check - contains + and 10-13 chars
    public boolean checkRecipientCell() {
        if (recipient == null) return false;
        return recipient.startsWith("+") && recipient.length() >= 10 && recipient.length() <= 13;
    }

    // Page 12: Message length check
    public String checkMessageLength() {
        if (messageText.length() > 250) {
            return "Please enter a message of less than 250 characters.";
        } else {
            totalMessages++;
            return "Message sent";
        }
    }

    // Page 12: Hash = 00:0:HITHANKS - first 2 of ID : number : FIRST+LAST CAPS
    public String createMessageHash() {
        String firstTwo = messageId.substring(0, 2);
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words[0].replaceAll("[^A-Za-z]", "");
        String lastWord = words[words.length - 1].replaceAll("[^A-Za-z]", "");
        String hash = firstTwo + ":" + messageNumber + ":" + firstWord + lastWord;
        return hash.toUpperCase();
    }

    public String printMessages() {
        return "MessageID: " + messageId + "\nMessageHash: " + messageHash + 
               "\nRecipient: " + recipient + "\nMessage: " + messageText;
    }

    // Store JSON - Attribution: https://www.w3schools.com/java/java_files_create.asp
    public void storeMessage() {
        try {
            java.io.FileWriter writer = new java.io.FileWriter("messages.json", true);
            String json = "{\"messageId\":\"" + messageId + "\",\"messageHash\":\"" + messageHash + 
                          "\",\"recipient\":\"" + recipient + "\",\"message\":\"" + messageText + "\"}\n";
            writer.write(json);
            writer.close();
            totalSent++;
        } catch (java.io.IOException e) {
            System.out.println("Error storing message");
        }
    }

    public static int returnTotalMessages() { return totalMessages; }
    public String getMessageId() { return messageId; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient() { return recipient; }
    public String getMessage() { return messageText; }
}