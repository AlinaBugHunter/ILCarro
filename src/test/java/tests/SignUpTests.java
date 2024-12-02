package tests;

import dto.UserDTO;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignUpPage;

import java.util.Random;

public class SignUpTests extends ApplicationManager {

    SignUpPage signUpPage;

    @Test
    public void signUpPositiveTests() {
        int randomInt = new Random().nextInt(1000) + 1000;
        UserDTO user = UserDTO.builder()
                .name("Test")
                .lastName("Test")
                .email("test" + randomInt + "@example.com")
                .password("Test999!")
                .build();
        new HomePage(getDriver()).clickBtnSignUpHeader();
        signUpPage = new SignUpPage(getDriver());
        signUpPage.typeRegistrationForm(user);
        signUpPage.clickCheckbox();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.isPopUpMessagePresent());
    }

}
