/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Student
 */




package com.mycompany.chatapplication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Message {

    private String messageID;
    private String recipient;
    private String message;
    private String messageHash;
    private String sender;
    private String status;

    private static int totalMessages = 0;
    private int messageNumber;

    // Default constructor
    public Message() {
    }

    // Constructor used when creating a new message
    public Message(String messageNumber, String recipient, String message) {
        this.messageNumber = Integer.parseInt(messageNumber);
        this.recipient = recipient;
        this.message = message;
        this.messageID = createMessageID();
        this.messageHash = createMessageHash();
        this.status = "Pending";

        totalMessages++;
    }

    // Constructor for loading an existing message
    public Message(String messageID, String recipient, String message,
                   String messageHash, int messageNumber) {
        this.messageID = messageID;
        this.recipient = recipient;
        this.message = message;
        this.messageHash = messageHash;
        this.messageNumber = messageNumber;
        this.status = "Stored";
    }

    // Generate a random 10-digit message ID
    public String createMessageID() {
        Random random = new Random();
        long number = 1000000000L
                + (long) (random.nextDouble() * 9000000000L);

        return String.valueOf(number);
    }

    // Check that the recipient number is valid
    public boolean checkRecipientCell(String recipient) {

        if (recipient == null) {
            return false;
        }

        return recipient.matches("^\\+27\\d{9}$");
    }

    // Check the message length
    public boolean checkMessageLength() {
        return message != null && message.length() <= 250;
    }

    // Create the message hash
    public String createMessageHash() {

        if (messageID == null || message == null) {
            return "";
        }

        String cleanedMessage = message.replaceAll("[^a-zA-Z0-9\\s]", "").trim();

        String[] words = cleanedMessage.split("\\s+");

        String firstWord = "";
        String lastWord = "";

        if (words.length > 0 && !words[0].isEmpty()) {
            firstWord = words[0];
            lastWord = words[words.length - 1];
        }

        return messageID.substring(0, 2)
                + ":" + messageNumber
                + ":" + firstWord.toUpperCase()
                + lastWord.toUpperCase();
    }

    // Set the sender
    public void setSender(String sender) {
        this.sender = sender;
    }

    // Get the sender
    public String getSender() {
        return sender;
    }

    // Set message status based on user's choice
    public boolean sentMessage(int choice) {

        switch (choice) {

            case 1:
                status = "Sent";
                return true;

            case 2:
                status = "Disregarded";
                return true;

            case 3:
                status = "Stored";
                return true;

            default:
                status = "Pending";
                return false;
        }
    }

    // Store message in a JSON file
    public boolean storeMessage() {

        try (FileWriter writer = new FileWriter("messages.json", true)) {

            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            writer.write(gson.toJson(this));
            writer.write(System.lineSeparator());

            status = "Stored";

            return true;

        } catch (IOException e) {

            System.out.println("Error storing message: " + e.getMessage());
            return false;
        }
    }

    // Print message details
    public String printMessages() {

        return "Message ID: " + messageID
                + "\nMessage Hash: " + messageHash
                + "\nRecipient: " + recipient
                + "\nMessage: " + message;
    }

    // Getters
    public String getMessageID() {
        return messageID;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessage() {
        return message;
    }

    public String getMessageHash() {
        return messageHash;
    }

    public String getStatus() {
        return status;
    }

    public int getMessageNumber() {
        return messageNumber;
    }

    public static int returnTotalMessages() {
        return totalMessages;
    }

    public static int getTotalMessages() {
        return totalMessages;
    }
}
