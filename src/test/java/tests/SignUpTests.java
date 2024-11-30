package tests;

import manager.ApplicationManager;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignUpPage;

import java.util.Random;

public class SignUpTests extends ApplicationManager {

    @Test
    public void signUpPositiveTests() {
        int i = new Random().nextInt(1000);
        new HomePage(getDriver()).clickBtnSignUpHeader();
        new SignUpPage(getDriver()).typeRegistrationForm("Test", "Tester",
                "testemail" + i + "@example.com", "Password123!");
    }

}
