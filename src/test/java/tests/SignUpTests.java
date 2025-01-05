package tests;

import dto.UserDTO;
import manager.ApplicationManager;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignUpPage;
import utils.TestNGListener;
import java.util.Random;
import static utils.TakeScreenShot.*;

@Listeners(TestNGListener.class)

public class SignUpTests extends ApplicationManager {

    SignUpPage signUpPage;

    int randomInt = new Random().nextInt(1000) + 1000;

    @BeforeMethod
    public void clickBtnSisnUpHeader() {
        new HomePage(getDriver()).clickBtnSignUpHeader();
    }

    @Test
    public void signUpPositiveTest() {
        UserDTO user = UserDTO.builder()
                .name("Test")
                .lastName("Test")
                .email("test" + randomInt + "@example.com")
                .password("Test999!")
                .build();
        signUpPage = new SignUpPage(getDriver());
        signUpPage.typeRegistrationForm(user);
        signUpPage.clickCheckbox();
        signUpPage.clickBtnYalla();
        takeScreenShot((TakesScreenshot) getDriver());
        Assert.assertTrue(signUpPage.isPopUpMessagePresent("You are logged in success"));
    }

    @Test
    public void signUpNegativeTest_emptyName() {
        UserDTO user = UserDTO.builder()
                .name("")
                .lastName("Test")
                .email("test" + randomInt + "@example.com")
                .password("Test999!")
                .build();
        signUpPage = new SignUpPage(getDriver());
        signUpPage.typeRegistrationForm(user);
        signUpPage.clickCheckbox();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.validateErrorMessage("Name is required"));
    }

    @Test
    public void signUpNegativeTest_emptyLastName() {
        UserDTO user = UserDTO.builder()
                .name("Test")
                .lastName("")
                .email("test" + randomInt + "@example.com")
                .password("Test999!")
                .build();
        signUpPage = new SignUpPage(getDriver());
        signUpPage.typeRegistrationForm(user);
        signUpPage.clickCheckbox();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.validateErrorMessage("Last name is required"));
    }

    @Test
    public void signUpNegativeTest_emptyEmail() {
        UserDTO user = UserDTO.builder()
                .name("Test")
                .lastName("Test")
                .email("")
                .password("Test999!")
                .build();
        signUpPage = new SignUpPage(getDriver());
        signUpPage.typeRegistrationForm(user);
        signUpPage.clickCheckbox();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.validateErrorMessage("Email is required"));
    }

    @Test
    public void signUpNegativeTest_emptyPassword() {
        UserDTO user = UserDTO.builder()
                .name("Test")
                .lastName("Test")
                .email("test" + randomInt + "@example.com")
                .password("")
                .build();
        signUpPage = new SignUpPage(getDriver());
        signUpPage.typeRegistrationForm(user);
        signUpPage.clickCheckbox();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.validateErrorMessage("Password is required"));
    }

    @Test
    public void signUpNegativeTest_emptyCheckbox() {
        UserDTO user = UserDTO.builder()
                .name("Test")
                .lastName("Test")
                .email("test" + randomInt + "@example.com")
                .password("Test999!")
                .build();
        signUpPage = new SignUpPage(getDriver());
        signUpPage.typeRegistrationForm(user);
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.btnYallaDisabled());
    }

    // TODO: Create Bug Report
    // It is possible to create a user with a non-existent domain in the email.
    @Test
    public void signUpNegativeTest_invalidEmail_nonExistentDomain() {
        UserDTO user = UserDTO.builder()
                .name("Test")
                .lastName("Test")
                .email("test" + randomInt + "@lorem.ipsum")
                .password("Test999!")
                .build();
        signUpPage = new SignUpPage(getDriver());
        signUpPage.typeRegistrationForm(user);
        signUpPage.clickCheckbox();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.validateErrorMessage("Wrong email format"));
    }

    @Test
    public void signUpNegativeTest_invalidEmail_missingAtSymbol() {
        UserDTO user = UserDTO.builder()
                .name("Test")
                .lastName("Test")
                .email("test" + randomInt + "example.com")
                .password("Test999!")
                .build();
        signUpPage = new SignUpPage(getDriver());
        signUpPage.typeRegistrationForm(user);
        signUpPage.clickCheckbox();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.validateErrorMessage("Wrong email format"));
    }

    @Test
    public void signUpNegativeTest_invalidEmail_missingFirstPart() {
        UserDTO user = UserDTO.builder()
                .name("Test")
                .lastName("Test")
                .email("@example.com")
                .password("Test999!")
                .build();
        signUpPage = new SignUpPage(getDriver());
        signUpPage.typeRegistrationForm(user);
        signUpPage.clickCheckbox();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.validateErrorMessage("Wrong email format"));
    }

    @Test
    public void signUpNegativeTest_emailAlreadyExists() {
        UserDTO user = UserDTO.builder()
                .name("Test")
                .lastName("Test")
                .email("testemail@example.com")
                .password("Test999!")
                .build();
        signUpPage = new SignUpPage(getDriver());
        signUpPage.typeRegistrationForm(user);
        signUpPage.clickCheckbox();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.isPopUpMessagePresent("User already exists"));
    }

    @Test
    public void signUpNegativeTest_invalidPassword_short() {
        UserDTO user = UserDTO.builder()
                .name("Test")
                .lastName("Test")
                .email("test" + randomInt + "@example.com")
                .password("Test999")
                .build();
        signUpPage = new SignUpPage(getDriver());
        signUpPage.typeRegistrationForm(user);
        signUpPage.clickCheckbox();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.validateErrorMessage("Password must contain minimum 8 symbols"));
    }

    @Test
    public void signUpNegativeTest_invalidPassword_noUpperCase() {
        UserDTO user = UserDTO.builder()
                .name("Test")
                .lastName("Test")
                .email("test" + randomInt + "@example.com")
                .password("test999@")
                .build();
        signUpPage = new SignUpPage(getDriver());
        signUpPage.typeRegistrationForm(user);
        signUpPage.clickCheckbox();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.validateErrorMessage("Password must contain 1 uppercase letter, " +
                "1 lowercase letter, 1 number and one special symbol of [@$#^&*!]"));
    }

    @Test
    public void signUpNegativeTest_invalidPassword_noLowerCase() {
        UserDTO user = UserDTO.builder()
                .name("Test")
                .lastName("Test")
                .email("test" + randomInt + "@example.com")
                .password("TEST999@")
                .build();
        signUpPage = new SignUpPage(getDriver());
        signUpPage.typeRegistrationForm(user);
        signUpPage.clickCheckbox();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.validateErrorMessage("Password must contain 1 uppercase letter, " +
                "1 lowercase letter, 1 number and one special symbol of [@$#^&*!]"));
    }

    @Test
    public void signUpNegativeTest_invalidPassword_noNumber() {
        UserDTO user = UserDTO.builder()
                .name("Test")
                .lastName("Test")
                .email("test" + randomInt + "@example.com")
                .password("Testtest@")
                .build();
        signUpPage = new SignUpPage(getDriver());
        signUpPage.typeRegistrationForm(user);
        signUpPage.clickCheckbox();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.validateErrorMessage("Password must contain 1 uppercase letter, " +
                "1 lowercase letter, 1 number and one special symbol of [@$#^&*!]"));
    }

    @Test
    public void signUpNegativeTest_invalidPassword_noSpecialSymbol() {
        UserDTO user = UserDTO.builder()
                .name("Test")
                .lastName("Test")
                .email("test" + randomInt + "@example.com")
                .password("Test9999")
                .build();
        signUpPage = new SignUpPage(getDriver());
        signUpPage.typeRegistrationForm(user);
        signUpPage.clickCheckbox();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.validateErrorMessage("Password must contain 1 uppercase letter, " +
                "1 lowercase letter, 1 number and one special symbol of [@$#^&*!]"));
    }

    @Test
    public void signUpNegativeTest_nameSpace() {
        UserDTO user = UserDTO.builder()
                .name(" ")
                .lastName("Test")
                .email("test" + randomInt + "@example.com")
                .password("Test999!")
                .build();
        signUpPage = new SignUpPage(getDriver());
        signUpPage.typeRegistrationForm(user);
        signUpPage.clickCheckbox();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.isPopUpMessagePresent("must not be blank"));
    }

    @Test
    public void signUpNegativeTest_lastNameSpace() {
        UserDTO user = UserDTO.builder()
                .name("Test")
                .lastName(" ")
                .email("test" + randomInt + "@example.com")
                .password("Test999!")
                .build();
        signUpPage = new SignUpPage(getDriver());
        signUpPage.typeRegistrationForm(user);
        signUpPage.clickCheckbox();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.isPopUpMessagePresent("must not be blank"));
    }

    @Test
    public void signUpNegativeTest_emailSpace() {
        UserDTO user = UserDTO.builder()
                .name("Test")
                .lastName("Test")
                .email(" ")
                .password("Test999!")
                .build();
        signUpPage = new SignUpPage(getDriver());
        signUpPage.typeRegistrationForm(user);
        signUpPage.clickCheckbox();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.validateErrorMessage("Email is required"));
    }

    @Test
    public void signUpNegativeTest_passwordSpace() {
        UserDTO user = UserDTO.builder()
                .name("Test")
                .lastName("Test")
                .email("test" + randomInt + "@example.com")
                .password(" ")
                .build();
        signUpPage = new SignUpPage(getDriver());
        signUpPage.typeRegistrationForm(user);
        signUpPage.clickCheckbox();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.validateErrorMessage("Password must contain minimum 8 symbols"));
    }

}
