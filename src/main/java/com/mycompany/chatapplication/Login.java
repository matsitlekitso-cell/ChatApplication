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
    private boolean loginSuccess;

    public Login() {
    }

    public boolean checkUsername(String username) {
        return username != null
                && username.contains("_")
                && username.length() <= 5;
    }

    public boolean checkUserName(String username) {
        return checkUsername(username);
    }

    public boolean checkPasswordComplexity(String password) {

        if (password == null || password.length() < 8) {
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
        }

        return hasCapital && hasNumber && hasSpecial;
    }

    public boolean checkCellPhoneNumber(String phoneNumber) {
        return phoneNumber != null
                && phoneNumber.matches("^\\+27\\d{9}$");
    }

    public String registerUser(String username, String password,
                               String phoneNumber, String firstName,
                               String lastName) {

        if (!checkUsername(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }

        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        if (!checkCellPhoneNumber(phoneNumber)) {
            return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        }

        this.storedUsername = username;
        this.storedPassword = password;
        this.storedCell = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;

        return "Username successfully captured.\n"
                + "Password successfully captured.\n"
                + "Cell number successfully captured.\n"
                + "User has been registered successfully.";
    }

    public boolean loginUser(String username, String password) {

        loginSuccess = storedUsername != null
                && storedPassword != null
                && storedUsername.equals(username)
                && storedPassword.equals(password);

        return loginSuccess;
    }

    public String returnLoginStatus() {
        return returnLoginStatus(loginSuccess);
    }

    public String returnLoginStatus(boolean loginSuccess) {

        if (loginSuccess) {
            return "Welcome " + firstName + " " + lastName
                    + " it is great to see you again.";
        }

        return "Username or password incorrect, please try again.";
    }

    public String getStoredCell() {
        return storedCell;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
