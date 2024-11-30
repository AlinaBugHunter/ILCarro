package tests;

import dto.UserDTO;
import manager.ApplicationManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignUpPage;

import java.util.Random;

public class SignUpTests extends ApplicationManager {

    @BeforeMethod
    public UserDTO userCreation() {
        int randomInt = new Random().nextInt(1000);
        return new UserDTO("Test", "Tester",
                "testemail" + randomInt + "@example.com", "Password123!");
    }

    @Test
    public void signUpPositiveTests() {
        new HomePage(getDriver()).clickBtnSignUpHeader();
        new SignUpPage(getDriver()).typeRegistrationForm(userCreation());
    }

}
