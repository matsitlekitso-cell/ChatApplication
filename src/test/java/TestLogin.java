/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

package TestClasses;

import com.mycompany.chatapplication.Login;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TestLogin {
    Login login = new Login();

    // Test 1: Username correctly formatted - should contain _ and <=5 chars
    @Test
    public void testUsernameCorrectlyFormatted() {
        assertTrue(login.checkUsername("kyl_1"));
    }

    // Test 2: Username incorrectly formatted
    @Test
    public void testUsernameIncorrectlyFormatted() {
        assertFalse(login.checkUsername("kyle!!!!!!!"));
    }

    // Test 3: Password meets complexity - 8 chars, capital, number, special
    @Test
    public void testPasswordMeetsComplexity() {
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    // Test 4: Password does NOT meet complexity
    @Test
    public void testPasswordDoesNotMeetComplexity() {
        assertFalse(login.checkPasswordComplexity("password"));
    }

    // Test 5: Cellphone correctly formatted - + international code
    @Test
    public void testCellphoneCorrectlyFormatted() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    // Test 6: Cellphone incorrectly formatted
    @Test
    public void testCellphoneIncorrectlyFormatted() {
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }

    // Test 7: Successful Registration
    @Test
    public void testSuccessfulRegistration() {
        String result = login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(result.toLowerCase().contains("successfully") || result.toLowerCase().contains("captured"));
    }

    // Test 8: Registration fails on username
    @Test
    public void testRegistrationUsernameFails() {
        String result = login.registerUser("kyle!!!!!!!", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(result.contains("Username is not correctly formatted"));
    }

    // Test 9: Login successful
    @Test
    public void testLoginSuccessful() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    // Test 10: Login failed
    @Test
    public void testLoginFailed() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(login.loginUser("kyl_1", "wrongPass"));
    }

    // Test 11: Return Login Status - Success message
    @Test
    public void testReturnLoginStatusSuccess() {
        Login loginWithName = new Login("Kyle", "Smith");
        loginWithName.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        boolean loggedIn = loginWithName.loginUser("kyl_1", "Ch&&sec@ke99!");
        String status = loginWithName.returnLoginStatus(loggedIn);
        assertTrue(status.contains("Welcome"));
    }

    // Test 12: Return Login Status - Failed message
    @Test
    public void testReturnLoginStatusFailed() {
        String status = login.returnLoginStatus(false);
        assertTrue(status.contains("incorrect") || status.contains("try again"));
    }
}