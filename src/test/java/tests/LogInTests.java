package tests;

import dto.UserDTO;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LogInPage;

public class LogInTests extends ApplicationManager {

    LogInPage logInPage;

    @BeforeMethod
    public void clickLogInHeader() {
        new HomePage(getDriver()).clickBtnLogInHeader();
    }

    @Test
    public void logInPositiveTest() {
        UserDTO user = UserDTO.builder()
                .email("testemail@example.com")
                .password("Password123!")
                .build();
        logInPage = new LogInPage(getDriver());
        logInPage.typeLogInForm(user);
        logInPage.clickBtnYalla();
        Assert.assertTrue(logInPage.isPopUpMessagePresent("Logged in success"));
    }

    @Test
    public void logInNegativeTest_wrongEmail() {
        UserDTO user = UserDTO.builder()
                .email("testemailexample.com")
                .password("Password123!")
                .build();
        logInPage = new LogInPage(getDriver());
        logInPage.typeLogInForm(user);
        logInPage.clickBtnYalla();
        Assert.assertTrue(logInPage.validateErrorMessage("It'snot look like email"));
    }

    @Test
    public void logInNegativeTest_wrongPassword() {
        UserDTO user = UserDTO.builder()
                .email("testemail@example.com")
                .password("Password123")
                .build();
        logInPage = new LogInPage(getDriver());
        logInPage.typeLogInForm(user);
        logInPage.clickBtnYalla();
        Assert.assertTrue(logInPage.isPopUpMessagePresent("Login or Password incorrect"));
    }

    @Test
    public void logInNegativeTest_emptyPassword() {
        UserDTO user = UserDTO.builder()
                .email("testemail@example.com")
                .password("")
                .build();
        logInPage = new LogInPage(getDriver());
        logInPage.typeLogInForm(user);
        logInPage.clickBtnYalla();
        Assert.assertTrue(logInPage.validateErrorMessage("Password is required"));
    }

}
