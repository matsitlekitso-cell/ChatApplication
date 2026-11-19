/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */





package com.mycompany.chatapplication;

import java.util.ArrayList;
import java.util.Scanner;

public class ChatApplication {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Login login = new Login();
        MessageStorage storage = new MessageStorage();

        // =============== PART 1: REGISTRATION ===============

        System.out.println("========== REGISTRATION ==========");

        System.out.print("Enter your username: ");
        String username = input.nextLine();

        System.out.print("Enter your password: ");
        String password = input.nextLine();

        System.out.print("Enter your cellphone number (+27...): ");
        String cellNumber = input.nextLine();

        System.out.print("Enter your first name: ");
        String firstName = input.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = input.nextLine();

        String registrationResult = login.registerUser(
                username, password, cellNumber, firstName, lastName
        );

        System.out.println(registrationResult);

        if (!registrationResult.startsWith(
                "Username successfully captured")) {

            System.out.println("Registration unsuccessful.");
            input.close();
            return;
        }

        // =============== PART 1: LOGIN ===============

        System.out.println("\n========== LOGIN ==========");

        boolean loggedIn = false;

        while (!loggedIn) {

            System.out.print("Enter your username: ");
            String loginUsername = input.nextLine();

            System.out.print("Enter your password: ");
            String loginPassword = input.nextLine();

            loggedIn = login.loginUser(
                    loginUsername, loginPassword
            );

            System.out.println(login.returnLoginStatus());

            if (!loggedIn) {
                System.out.println("Please try again.\n");
            }
        }

        // =============== PART 3: LOAD STORED MESSAGES ===============

        storage.loadMessages();

        // =============== PART 2: QUICKCHAT ===============

        System.out.println("\nWelcome to QuickChat.");

        System.out.print("How many messages would you like to enter? ");

        int maximumMessages;

        try {
            maximumMessages = Integer.parseInt(input.nextLine());

            if (maximumMessages < 0) {
                System.out.println("Please enter a valid number.");
                input.close();
                return;
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid number entered.");
            input.close();
            return;
        }

        int messagesCreated = 0;
        int messageNumber = 0;
        boolean running = true;

        while (running) {

            System.out.println("\n========== QUICKCHAT MENU ==========");
            System.out.println("1. Send Messages");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Quit");
            System.out.println("4. Stored Messages");
            System.out.print("Choose an option: ");

            String choice = input.nextLine();

            switch (choice) {

                // =============== SEND MESSAGE ===============

                case "1":

                    if (messagesCreated >= maximumMessages) {
                        System.out.println(
                                "You have reached your message limit."
                        );
                        break;
                    }

                    System.out.print(
                            "Enter recipient cellphone number (+27...): "
                    );

                    String recipient = input.nextLine();

                    Message validator = new Message();

                    if (!validator.checkRecipientCell(recipient)) {
                        System.out.println(
                                "Cell number is incorrectly formatted "
                                + "or does not contain an international code."
                        );
                        break;
                    }

                    System.out.print("Enter your message: ");
                    String messageText = input.nextLine();

                    if (messageText.length() >= 250) {
                        System.out.println(
                                "Please enter a message of less than 250 characters."
                        );
                        break;
                    }

                    Message message = new Message(
                            String.valueOf(messageNumber),
                            recipient,
                            messageText
                    );

                    // Set the sender's name
                    message.setSender(firstName + " " + lastName);

                    System.out.println("\nChoose what to do:");
                    System.out.println("1. Send Message");
                    System.out.println("2. Disregard Message");
                    System.out.println("3. Store Message");
                    System.out.print("Enter your choice: ");

                    int messageChoice;

                    try {
                        messageChoice = Integer.parseInt(input.nextLine());

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid choice.");
                        break;
                    }

                    if (messageChoice < 1 || messageChoice > 3) {
                        System.out.println("Invalid choice.");
                        break;
                    }

                    message.sentMessage(messageChoice);

                    // Add message to arrays
                    storage.addMessage(message);

                    if (messageChoice == 1) {
                        System.out.println("Message successfully sent.");

                    } else if (messageChoice == 2) {
                        System.out.println("Press 0 to delete the message.");
                        System.out.print("Enter 0 to delete: ");

                        String deleteChoice = input.nextLine();

                        if (deleteChoice.equals("0")) {
                            storage.getDisregardedMessages().remove(message);
                            storage.getMessages().remove(message);
                            System.out.println("Message deleted.");
                        }

                    } else if (messageChoice == 3) {

                        if (storage.saveStoredMessages()) {
                            System.out.println(
                                    "Message successfully stored."
                            );
                        }
                    }

                    System.out.println("\n" + message.printMessages());

                    messagesCreated++;
                    messageNumber++;

                    System.out.println(
                            "\nTotal messages created: "
                            + Message.returnTotalMessages()
                    );

                    break;

                // =============== RECENTLY SENT MESSAGES ===============

                case "2":

                    System.out.println("Coming Soon.");
                    break;

                // =============== QUIT ===============

                case "3":

                    System.out.println(
                            "Thank you for using QuickChat."
                    );

                    running = false;
                    break;

                // =============== PART 3: STORED MESSAGES ===============

                case "4":

                    storedMessagesMenu(input, storage);
                    break;

                default:

                    System.out.println(
                            "Invalid option. Please try again."
                    );
            }
        }

        input.close();
    }

    // =============== STORED MESSAGES MENU ===============

    public static void storedMessagesMenu(
            Scanner input, MessageStorage storage) {

        boolean menuRunning = true;

        while (menuRunning) {

            System.out.println("\n========== STORED MESSAGES ==========");
            System.out.println("1. Display senders and recipients");
            System.out.println("2. Display longest stored message");
            System.out.println("3. Search for a message ID");
            System.out.println("4. Search messages by recipient");
            System.out.println("5. Delete a message using its hash");
            System.out.println("6. Display stored messages report");
            System.out.println("7. Return to main menu");
            System.out.print("Choose an option: ");

            String choice = input.nextLine();

            switch (choice) {

                // Display senders and recipients
                case "1":

                    storage.displaySendersAndRecipients();
                    break;

                // Display longest message
                case "2":

                    storage.displayLongestMessage();
                    break;

                // Search by message ID
                case "3":

                    System.out.print("Enter message ID: ");
                    String id = input.nextLine();

                    Message foundMessage =
                            storage.searchByMessageID(id);

                    if (foundMessage != null) {

                        System.out.println(
                                "Recipient: "
                                + foundMessage.getRecipient()
                        );

                        System.out.println(
                                "Message: "
                                + foundMessage.getMessage()
                        );

                    } else {
                        System.out.println("Message ID not found.");
                    }

                    break;

                // Search by recipient
                case "4":

                    System.out.print(
                            "Enter recipient cellphone number: "
                    );

                    String recipient = input.nextLine();

                    ArrayList<Message> results =
                            storage.searchByRecipient(recipient);

                    if (results.isEmpty()) {

                        System.out.println(
                                "No messages found for this recipient."
                        );

                    } else {

                        for (Message result : results) {

                            System.out.println(
                                    result.printMessages()
                            );

                            System.out.println("--------------------");
                        }
                    }

                    break;

                // Delete message using hash
                case "5":

                    System.out.print("Enter message hash: ");
                    String hash = input.nextLine();

                    if (storage.deleteMessage(hash)) {

                        System.out.println(
                                "Message successfully deleted."
                        );

                    } else {

                        System.out.println(
                                "Message hash not found."
                        );
                    }

                    break;

                // Display full report
                case "6":

                    storage.displayReport();
                    break;

                // Return to main menu
                case "7":

                    menuRunning = false;
                    break;

                default:

                    System.out.println(
                            "Invalid option. Please try again."
                    );
            }
        }
    }
}
