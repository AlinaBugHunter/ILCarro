package tests;

import manager.ApplicationManager;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LogInPage;

public class LogInTests extends ApplicationManager {

    @Test
    public void logInPositiveTests() {
        new HomePage(getDriver()).clickBtnLogInHeader();
        new LogInPage(getDriver()).typeLogInForm();
    }

}
