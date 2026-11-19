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
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MessageStorage {

    private ArrayList<Message> messages = new ArrayList<>();
    private ArrayList<Message> sentMessages = new ArrayList<>();
    private ArrayList<Message> disregardedMessages = new ArrayList<>();
    private ArrayList<Message> storedMessages = new ArrayList<>();

    private ArrayList<String> messageHashes = new ArrayList<>();
    private ArrayList<String> messageIDs = new ArrayList<>();

    private final String FILE_NAME = "messages.json";

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // Add a message to the correct array
    public void addMessage(Message message) {

        if (message == null) {
            return;
        }

        messages.add(message);

        messageHashes.add(message.getMessageHash());
        messageIDs.add(message.getMessageID());

        switch (message.getStatus()) {

            case "Sent":
                sentMessages.add(message);
                break;

            case "Disregarded":
                disregardedMessages.add(message);
                break;

            case "Stored":
                storedMessages.add(message);
                break;

            default:
                break;
        }
    }

    // Load stored messages from the JSON file
    public void loadMessages() {

        storedMessages.clear();

        try (BufferedReader reader =
                new BufferedReader(new FileReader(FILE_NAME))) {

            String line;

            while ((line = reader.readLine()) != null) {

                if (!line.trim().isEmpty()) {

                    Message message = gson.fromJson(line, Message.class);

                    if (message != null) {
                        storedMessages.add(message);
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("No stored messages file found yet.");
        }
    }

    // Display sender and recipient of stored messages
    public void displaySendersAndRecipients() {

        if (storedMessages.isEmpty()) {
            System.out.println("No stored messages found.");
            return;
        }

        for (Message message : storedMessages) {

            System.out.println("Sender: " + message.getSender());
            System.out.println("Recipient: " + message.getRecipient());
            System.out.println("-------------------------");
        }
    }

    // Display the longest stored message
    public void displayLongestMessage() {

        if (storedMessages.isEmpty()) {
            System.out.println("No stored messages found.");
            return;
        }

        Message longest = storedMessages.get(0);

        for (Message message : storedMessages) {

            if (message.getMessage().length()
                    > longest.getMessage().length()) {

                longest = message;
            }
        }

        System.out.println("Longest stored message:");
        System.out.println(longest.getMessage());
    }

    // Search for a message using its ID
    public Message searchByMessageID(String id) {

        for (Message message : messages) {

            if (message.getMessageID().equals(id)) {
                return message;
            }
        }

        for (Message message : storedMessages) {

            if (message.getMessageID().equals(id)) {
                return message;
            }
        }

        return null;
    }

    // Search for messages using recipient number
    public ArrayList<Message> searchByRecipient(String recipient) {

        ArrayList<Message> results = new ArrayList<>();

        for (Message message : messages) {

            if (message.getRecipient().equals(recipient)
                    && !results.contains(message)) {

                results.add(message);
            }
        }

        for (Message message : storedMessages) {

            if (message.getRecipient().equals(recipient)
                    && !results.contains(message)) {

                results.add(message);
            }
        }

        return results;
    }

    // Delete a stored message using its hash
    public boolean deleteMessage(String hash) {

        boolean deleted = false;

        for (int i = 0; i < storedMessages.size(); i++) {

            Message message = storedMessages.get(i);

            if (message.getMessageHash().equals(hash)) {

                storedMessages.remove(i);
                messages.remove(message);
                messageHashes.remove(hash);
                messageIDs.remove(message.getMessageID());

                deleted = true;
                break;
            }
        }

        if (deleted) {
            saveStoredMessages();
        }

        return deleted;
    }

    // Rewrite the JSON file after deleting a message
    public boolean saveStoredMessages() {

    try (FileWriter writer = new FileWriter(FILE_NAME)) {

        for (Message message : storedMessages) {

            writer.write(new Gson().toJson(message));
            writer.write(System.lineSeparator());
        }

        return true;

    } catch (IOException e) {

        System.out.println("Error saving messages: " + e.getMessage());
        return false;
    }
}

    // Display a full report of stored messages
    public void displayReport() {

        if (storedMessages.isEmpty()) {
            System.out.println("No stored messages found.");
            return;
        }

        System.out.println("\n========== STORED MESSAGE REPORT ==========");

        for (Message message : storedMessages) {

            System.out.println(message.printMessages());
            System.out.println("-------------------------------------------");
        }
    }

    // Display sent messages
    public void displayMessages() {

        for (Message message : sentMessages) {
            System.out.println(message.printMessages());
        }
    }

    // Getters
    public ArrayList<Message> getMessages() {
        return messages;
    }

    public ArrayList<Message> getSentMessages() {
        return sentMessages;
    }

    public ArrayList<Message> getDisregardedMessages() {
        return disregardedMessages;
    }

    public ArrayList<Message> getStoredMessages() {
        return storedMessages;
    }

    public ArrayList<String> getMessageHashes() {
        return messageHashes;
    }

    public ArrayList<String> getMessageIDs() {
        return messageIDs;
    }

    // Find a message by ID
    public Message searchByID(String id) {
        return searchByMessageID(id);
    }

    // Find the longest stored message
    public Message getLongestMessage() {

        if (storedMessages.isEmpty()) {
            return null;
        }

        Message longest = storedMessages.get(0);

        for (Message message : storedMessages) {

            if (message.getMessage().length()
                    > longest.getMessage().length()) {

                longest = message;
            }
        }

        return longest;
    }
}
