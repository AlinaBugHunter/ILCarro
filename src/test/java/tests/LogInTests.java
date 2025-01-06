package tests;

import dto.UserDTO;
import manager.ApplicationManager;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LogInPage;
import utils.TestNGListener;

import static utils.TakeScreenShot.takeScreenShot;

@Listeners(TestNGListener.class)

public class LogInTests extends ApplicationManager {

    LogInPage logInPage;

    @BeforeMethod
    public void clickLogInHeader() {
        new HomePage(getDriver()).clickBtnLogInHeader();
    }

    @Test
    public void logInPositiveTest() {
        UserDTO user = UserDTO.builder()
                .username("testemail@example.com")
                .password("Password123!")
                .build();
        logInPage = new LogInPage(getDriver());
        logInPage.typeLogInForm(user);
        Assert.assertTrue(logInPage.isPopUpMessagePresent("Logged in success"));
    }

    @Test
    public void logInNegativeTest_wrongEmail() {
        UserDTO user = UserDTO.builder()
                .username("testemailexample.com")
                .password("Password123!")
                .build();
        logInPage = new LogInPage(getDriver());
        logInPage.typeLogInForm(user);
        takeScreenShot((TakesScreenshot) getDriver());
        Assert.assertTrue(logInPage.validateErrorMessage("It'snot look like email"));
    }

    @Test
    public void logInNegativeTest_wrongPassword() {
        UserDTO user = UserDTO.builder()
                .username("testemail@example.com")
                .password("Password123")
                .build();
        logInPage = new LogInPage(getDriver());
        logInPage.typeLogInForm(user);
        Assert.assertTrue(logInPage.isPopUpMessagePresent("Login or Password incorrect"));
    }

    @Test
    public void logInNegativeTest_emptyEmail() {
        UserDTO user = UserDTO.builder()
                .username("")
                .password("Password123!")
                .build();
        logInPage = new LogInPage(getDriver());
        logInPage.typeLogInForm(user);
        Assert.assertTrue(logInPage.validateErrorMessage("Email is required"));
    }

    @Test
    public void logInNegativeTest_emptyPassword() {
        UserDTO user = UserDTO.builder()
                .username("testemail@example.com")
                .password("")
                .build();
        logInPage = new LogInPage(getDriver());
        logInPage.typeLogInForm(user);
        Assert.assertTrue(logInPage.validateErrorMessage("Password is required"));
    }

    @Test
    public void logInNegativeTest_emptyFields() {
        UserDTO user = UserDTO.builder()
                .username("")
                .password("")
                .build();
        logInPage = new LogInPage(getDriver());
        logInPage.typeLogInForm(user);
        Assert.assertTrue(logInPage.btnYallaDisabled());
    }

}
