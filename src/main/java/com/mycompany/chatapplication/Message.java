/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Student
 */
package com.mycompany.chatapplication;

import java.util.Random;

public class Message {
    private String messageID;
    private String recipient;
    private String message;
    private String messageHash;
    private static int totalMessages = 0;
    private int messageNumber;

    // Empty constructor - THIS FIXES YOUR RED LINES
    public Message() {
    }

    // Main constructor used by ChatApplication
    public Message(String messageNumber, String recipient, String message) {
        this.messageID = createMessageID();
        this.recipient = recipient;
        this.message = message;
        this.messageNumber = Integer.parseInt(messageNumber);
        totalMessages++;
    }

    // Constructor with all fields
    public Message(String messageID, String recipient, String message, String hash, int num) {
        this.messageID = messageID;
        this.recipient = recipient;
        this.message = message;
        this.messageHash = hash;
        this.messageNumber = num;
    }

    public String createMessageID() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }

    public String getMessageID() {
        if (messageID == null) {
            messageID = createMessageID();
        }
        return messageID;
    }

    public void setMessageID(String id) {
        this.messageID = id;
    }

    public String getMessage() {
        return message;
    }

    public String getRecipient() {
        return recipient;
    }

    public static int getTotalMessages() {
        return totalMessages;
    }

    public static void setTotalMessages(int total) {
        totalMessages = total;
    }

    // THIS IS THE METHOD YOUR TEST IS CALLING - MUST BE EXACT NAME
    public boolean checkRecipientCell(String cell) {
        if (cell == null) return false;
        if (!cell.startsWith("+")) return false;
        if (cell.length() > 13) return false; // +27 + 9 digits = 12 max, allow 13
        if (cell.length() < 11) return false;
        // Check if rest are digits
        String numberPart = cell.substring(1);
        return numberPart.matches("\\d+");
    }

    public String createMessageHash() {
        if (message == null || messageID == null) return "00:0:HASH";
        String[] words = message.split(" ");
        String firstWord = words.length > 0 ? words[0] : "HI";
        String lastWord = words.length > 1 ? words[words.length - 1] : firstWord;
        return messageID.substring(0, 2) + ":" + messageNumber + ":" + firstWord.toUpperCase() + lastWord.toUpperCase();
    }

    public String sentMessage(int option) {
        switch (option) {
            case 1:
                return "Message successfully sent.";
            case 2:
                return "Press 0 to delete message.";
            case 3:
                return "Message successfully stored.";
            default:
                return "Invalid option.";
        }
    }

    public String printMessages() {
        return "MessageID: " + messageID + "\nMessage Hash: " + messageHash + "\nRecipient: " + recipient + "\nMessage: " + message;
    }
}