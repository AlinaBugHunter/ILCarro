package tests;

import dto.UserDTO;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LogInPage;

public class LogInTests extends ApplicationManager {

    LogInPage logInPage;

    @Test
    public void logInPositiveTest() {
        UserDTO user = UserDTO.builder()
                .email("testemail@example.com")
                .password("Password123!")
                .build();
        new HomePage(getDriver()).clickBtnLogInHeader();
        logInPage = new LogInPage(getDriver());
        logInPage.typeLogInForm(user);
        logInPage.clickBtnYalla();
        Assert.assertTrue(logInPage.isPopUpMessagePresent());
    }

    @Test
    public void logInNegativeTest() {
        UserDTO user = UserDTO.builder()
                .email("testemail@example.com")
                .password("Password123")
                .build();
        new HomePage(getDriver()).clickBtnLogInHeader();
        logInPage = new LogInPage(getDriver());
        logInPage.typeLogInForm(user);
        logInPage.clickBtnYalla();
        Assert.assertFalse(logInPage.isPopUpMessagePresent());
    }

}
