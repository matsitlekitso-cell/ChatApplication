/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package TestClasses;

import com.mycompany.chatapplication.Login;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestLogin {

    private Login login;

    @BeforeEach
    public void setUp() {
        login = new Login();
    }

   
    // USERNAME TESTS
   

    @Test
    public void testUsernameCorrectlyFormatted() {
        assertTrue(login.checkUsername("Kit_1"));
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {
        assertFalse(login.checkUsername("Kitso"));
    }

    @Test
    public void testUsernameTooLong() {
        assertFalse(login.checkUsername("Kitso_123"));
    }

    
    // PASSWORD TESTS
   

    @Test
    public void testPasswordCorrectlyFormatted() {
        assertTrue(login.checkPasswordComplexity("Kitso123!"));
    }

    @Test
    public void testPasswordTooShort() {
        assertFalse(login.checkPasswordComplexity("Kit1!"));
    }

    @Test
    public void testPasswordWithoutCapitalLetter() {
        assertFalse(login.checkPasswordComplexity("kitso123!"));
    }

    @Test
    public void testPasswordWithoutNumber() {
        assertFalse(login.checkPasswordComplexity("KitsoPass!"));
    }

    @Test
    public void testPasswordWithoutSpecialCharacter() {
        assertFalse(login.checkPasswordComplexity("Kitso1234"));
    }

   
    // CELLPHONE TESTS
   

    @Test
    public void testCellPhoneCorrectlyFormatted() {
        assertTrue(login.checkCellPhoneNumber("+27638512121"));
    }

    @Test
    public void testCellPhoneWithoutPlus() {
        assertFalse(login.checkCellPhoneNumber("0638512121"));
    }

    @Test
    public void testCellPhoneTooShort() {
        assertFalse(login.checkCellPhoneNumber("+276385"));
    }

    @Test
    public void testCellPhoneTooLong() {
        assertFalse(login.checkCellPhoneNumber("+276385121212345678"));
    }

    @Test
    public void testCellPhoneContainsLetters() {
        assertFalse(login.checkCellPhoneNumber("+27638abc121"));
    }

    
    // REGISTRATION TESTS
   

    @Test
    public void testSuccessfulRegistration() {

        String result =
                login.registerUser("Kit_1", "Kitso123!");

        assertEquals(
                "User has been registered successfully",
                result
        );
    }

    @Test
    public void testRegistrationWithInvalidUsername() {

        String result =
                login.registerUser("Kitso", "Kitso123!");

        assertEquals(
                "Username is not correctly formatted",
                result
        );
    }

    @Test
    public void testRegistrationWithInvalidPassword() {

        String result =
                login.registerUser("Kit_1", "kitso");

        assertEquals(
                "Password does not meet complexity requirements",
                result
        );
    }

   
    // LOGIN TESTS
   

    @Test
    public void testSuccessfulLogin() {

        login.registerUser("Kit_1", "Kitso123!");

        assertTrue(
                login.loginUser("Kit_1", "Kitso123!")
        );
    }

    @Test
    public void testLoginWithIncorrectUsername() {

        login.registerUser("Kit_1", "Kitso123!");

        assertFalse(
                login.loginUser("Wrong_1", "Kitso123!")
        );
    }

    @Test
    public void testLoginWithIncorrectPassword() {

        login.registerUser("Kit_1", "Kitso123!");

        assertFalse(
                login.loginUser("Kit_1", "Wrong123!")
        );
    }

    
    // LOGIN STATUS TESTS
    

    @Test
    public void testSuccessfulLoginStatus() {

        assertEquals(
                "Login successful",
                login.returnLoginStatus(true)
        );
    }

    @Test
    public void testFailedLoginStatus() {

        assertEquals(
                "Login failed",
                login.returnLoginStatus(false)
        );
    }
}

