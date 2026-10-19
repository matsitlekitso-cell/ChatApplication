package TestClasses;

import com.mycompany.chatapplication.Login;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TestLogin {
    Login login = new Login();

    @Test
    public void testUsernameCorrectlyFormatted() {
        assertTrue(login.checkUsername("kyl_1"));
    }
    @Test
    public void testUsernameIncorrectlyFormatted() {
        assertFalse(login.checkUsername("kyle!!!!!!!"));
    }
    @Test
    public void testPasswordMeetsComplexity() {
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }
    @Test
    public void testPasswordDoesNotMeetComplexity() {
        assertFalse(login.checkPasswordComplexity("password"));
    }
    @Test
    public void testCellphoneCorrectlyFormatted() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }
    @Test
    public void testCellphoneIncorrectlyFormatted() {
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }
    @Test
    public void testSuccessfulRegistration() {
        String result = login.registerUser("Kit_1", "Kitso123!", "+27838968976");
        assertTrue(result.contains("successfully"));
    }
    @Test
    public void testRegistrationWithInvalidUsername() {
        String result = login.registerUser("Kitso", "Kitso123!", "+27838968976");
        assertTrue(result.contains("Username is not"));
    }
    @Test
    public void testRegistrationWithInvalidPassword() {
        String result = login.registerUser("Kit_1", "kitso", "+27838968976");
        assertTrue(result.contains("Password is not"));
    }
    @Test
    public void testSuccessfulLogin() {
        login.registerUser("Kit_1", "Kitso123!", "+27838968976");
        assertTrue(login.loginUser("Kit_1", "Kitso123!"));
    }
    @Test
    public void testLoginWithIncorrectUsername() {
        login.registerUser("Kit_1", "Kitso123!", "+27838968976");
        assertFalse(login.loginUser("Wrong_1", "Kitso123!"));
    }
}