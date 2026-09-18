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
            
            
            // USERNAME VALIDATION
            
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
            
            
            // PASSWORD VALIDATION
            
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
            
            
            // CELLPHONE VALIDATION
            
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
            
            
            // REGISTER USER
            
            String registrationMessage =
                    userlogin.registerUser(username, password);
            
            System.out.println(registrationMessage);
            
            
            // LOGIN
            
            System.out.println("\n=== LOGIN ===");
            
            System.out.println("Enter username:");
            String loginUsername = input.nextLine();
            
            System.out.println("Enter password:");
            String loginPassword = input.nextLine();
            
            boolean loginResults =
                    userlogin.loginUser(loginUsername, loginPassword);
            
            // Display login status
            System.out.println(
                    userlogin.returnLoginStatus(loginResults)
            );
        }
    }
}
