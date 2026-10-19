/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.chatapplication;
import java.util.Scanner;


/**
 *
 * @author Student
 */


public class ChatApplication {

    public static void main(String[] args) {

         try (Scanner input = new Scanner(System.in)) {
            Login userlogin = new Login();

            String username;
            String password;
            String phoneNumber;

            // ========== PART 1 - USERNAME VALIDATION ==========
            do {
                System.out.println("Please enter the username:");
                username = input.nextLine();

                if (!userlogin.checkUsername(username)) {
                    System.out.println(
                        "Username is not correctly formatted; "
                      + "please ensure that your username contains "
                      + "an underscore and is no more than five characters."
                    );
                }
            } while (!userlogin.checkUsername(username));

            System.out.println("Username successfully captured.");

            // ========== PART 1 - PASSWORD VALIDATION ==========
            do {
                System.out.println("Enter your password:");
                password = input.nextLine();

                if (!userlogin.checkPasswordComplexity(password)) {
                    System.out.println(
                        "Password is not correctly formatted; "
                      + "please ensure that your password contains at least "
                      + "8 characters, a capital letter, a number, "
                      + "and a special character."
                    );
                }
            } while (!userlogin.checkPasswordComplexity(password));

            System.out.println("Password successfully captured.");

            // ========== PART 1 - CELLPHONE VALIDATION ==========
            do {
                System.out.println("Enter cellphone number:");
                phoneNumber = input.nextLine();

                if (!userlogin.checkCellPhoneNumber(phoneNumber)) {
                    System.out.println(
                        "Cellphone number is not correctly formatted. "
                      + "Please enter a valid number starting with +."
                    );
                }
            } while (!userlogin.checkCellPhoneNumber(phoneNumber));

            System.out.println("Cellphone number successfully captured.");

            // REGISTER
            String registrationMessage = userlogin.registerUser(username, password, phoneNumber);
            System.out.println(registrationMessage);

            // ========== PART 1 - LOGIN ==========
            System.out.println("\n=== LOGIN ===");
            System.out.println("Enter username:");
            String loginUsername = input.nextLine();

            System.out.println("Enter password:");
            String loginPassword = input.nextLine();

            boolean loginResult = userlogin.loginUser(loginUsername, loginPassword);
            System.out.println(userlogin.returnLoginStatus(loginResult));

            // ========== PART 2 STARTS HERE - Only if login success ==========
            if (loginResult) {
                
                System.out.println("\nWelcome to QuickChat.");

                int menuChoice = 0;
                // While loop - Rubric Page 22: Application loop runs correctly and quits on correct input
                while (menuChoice != 3) {
                    System.out.println("\n--- QuickChat Menu ---");
                    System.out.println("1) Send Messages");
                    System.out.println("2) Show recently sent messages");
                    System.out.println("3) Quit");
                    System.out.print("Enter your choice: ");
                    
                    // 10th Edition way to avoid crash on letters
                    while (!input.hasNextInt()) {
                        System.out.print("Please enter a number (1-3): ");
                        input.next();
                    }
                    menuChoice = input.nextInt();
                    input.nextLine(); // clear enter

                    if (menuChoice == 1) {
                        System.out.print("How many messages do you wish to send? ");
                        int numToSend = input.nextInt();
                        input.nextLine();

                        // For loop - Rubric Page 23: A for loop is used to allow user to enter assigned number
                        for (int i = 0; i < numToSend; i++) {
                            System.out.println("\n--- Message " + (i + 1) + "/" + numToSend + " ---");
                            
                            System.out.print("Enter recipient cell (e.g. +27834557896): ");
                            String recipient = input.nextLine();
                            
                            System.out.print("Enter your message (max 250 chars): ");
                            String msgText = input.nextLine();

                            Message myMessage = new Message(i, recipient, msgText);

                            // Check recipient - Page 12
                            if (!myMessage.checkRecipientCell()) {
                                System.out.println("Cell phone number is incorrectly formatted or does not contain international code.");
                                i--; // retry same message
                                continue;
                            }

                            // Check length - Page 12
                            String lengthResult = myMessage.checkMessageLength();
                            System.out.println(lengthResult);
                            if (lengthResult.contains("Please enter")) {
                                i--;
                                continue;
                            }

                            System.out.println("Message Hash: " + myMessage.createMessageHash());

                            System.out.println("\nWhat would you like to do?");
                            System.out.println("1) Send Message - Message successfully sent");
                            System.out.println("2) Disregard Message - Press 0 to delete");
                            System.out.println("3) Store Message - Message successfully stored");
                            System.out.print("Choice: ");
                            int sendChoice = input.nextInt();
                            input.nextLine();

                            if (sendChoice == 1) {
                                System.out.println("Message successfully sent");
                                System.out.println(myMessage.printMessages());
                            } else if (sendChoice == 2 || sendChoice == 0) {
                                System.out.println("Press 0 to delete the message");
                                System.out.println("Message disregarded");
                            } else if (sendChoice == 3) {
                                myMessage.storeMessage();
                                System.out.println("Message successfully stored");
                            }
                        }
                        System.out.println("\nTotal messages sent: " + Message.returnTotalMessages());

                    } else if (menuChoice == 2) {
                        System.out.println("Coming Soon.");

                    } else if (menuChoice == 3) {
                        System.out.println("Quitting QuickChat. Total messages: " + Message.returnTotalMessages());
                        break;
                    } else {
                        System.out.println("Invalid choice, try again.");
                    }
                }
            }
        }
    }
}