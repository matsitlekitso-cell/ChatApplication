/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapplication;

public class Login {

    private String storedUsername;
    private String storedPassword;
    private String storedCell;
    private String firstName;
    private String lastName;

    public Login(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public Login() {
    }

    public boolean checkUsername(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        if (password.length() < 8) {
            return false;
        }
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) hasCapital = true;
            if (Character.isDigit(ch)) hasNumber = true;
            if (!Character.isLetterOrDigit(ch)) hasSpecial = true;
        }
        return hasCapital && hasNumber && hasSpecial;
    }

    public boolean checkCellPhoneNumber(String phoneNumber) {
        // Attribution: https://www.w3schools.com/java/java_regex.asp
        if (!phoneNumber.startsWith("+")) return false;
        String digitsOnly = phoneNumber.substring(1);
        if (!digitsOnly.matches("\\d+")) return false;
        return digitsOnly.length() >= 10 && digitsOnly.length() <= 15;
    }

    public boolean loginUser(String username, String password) {
        return username.equals(storedUsername) && password.equals(storedPassword);
    }

    public String returnLoginStatus(boolean loginSuccess) {
        if (loginSuccess) {
            if (firstName != null) {
                return "Welcome " + firstName + " " + lastName + " it is great to see you again.";
            } else {
                return "Welcome it is great to see you again.";
            }
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    // THIS WAS THE BROKEN ONE - FIXED NOW
    public String registerUser(String username, String password, String phoneNumber) {
        if (!checkUsername(username)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(phoneNumber)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        storedUsername = username;
        storedPassword = password;
        storedCell = phoneNumber;
        return "Username successfully captured.\nPassword successfully captured.\nCell phone number successfully added.\nUser has been registered successfully";
    }
}