/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapplication;

/**
 *
 * @author Student
 */
public class Login {

    private String storedUsername;
    private String storedPassword;

    // Check username
    public boolean checkUsername(String username) {

        return username.contains("_") && username.length() <= 5;
    }

    // Check password complexity
    public boolean checkPasswordComplexity(String password) {

        if (password.length() < 8) {
            return false;
        }

        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (char ch : password.toCharArray()) {

            if (Character.isUpperCase(ch)) {
                hasCapital = true;
            }

            if (Character.isDigit(ch)) {
                hasNumber = true;
            }

            if (!Character.isLetterOrDigit(ch)) {
                hasSpecial = true;
            }

            if (hasCapital && hasNumber && hasSpecial) {
                return true;
            }
        }

        return false;
    }

    // Check cellphone number
    public boolean checkCellPhoneNumber(String phoneNumber) {

        if (!phoneNumber.startsWith("+")) {
            return false;
        }

        String digitsOnly = phoneNumber.substring(1);

        if (!digitsOnly.matches("\\d+")) {
            return false;
        }

        return digitsOnly.length() >= 10
                && digitsOnly.length() <= 15;
    }

    // Register user
    public String registerUser(String username, String password) {

        if (!checkUsername(username)) {
            return "Username is not correctly formatted";
        }

        if (!checkPasswordComplexity(password)) {
            return "Password does not meet complexity requirements";
        }

        storedUsername = username;
        storedPassword = password;

        return "User has been registered successfully";
    }

    // Login user
    public boolean loginUser(String username, String password) {

        return username.equals(storedUsername)
                && password.equals(storedPassword);
    }

    // Return login status
    public String returnLoginStatus(boolean loginSuccess) {

        if (loginSuccess) {
            return "Login successful";
        } else {
            return "Login failed";
        }
    }
}
