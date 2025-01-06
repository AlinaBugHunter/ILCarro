package tests;

import data_providers.CarDP;
import dto.CarDTO;
import dto.UserDTO;
import manager.ApplicationManager;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LetTheCarWorkPage;
import pages.LogInPage;
import utils.Fuel;
import utils.RetryAnalyzer;
import utils.TestNGListener;

import java.util.Random;

import static utils.PropertiesReader.*;
import static utils.TakeScreenShot.*;

@Listeners(TestNGListener.class)

public class AddNewCarTests extends ApplicationManager {

    SoftAssert softAssert = new SoftAssert();
    LogInPage logInPage;
    LetTheCarWorkPage letTheCarWorkPage;

    @BeforeMethod
    public void login() {
        UserDTO user = UserDTO.builder()
                .username(getProperty("login.properties", "email"))
                .password(getProperty("login.properties", "password"))
                .build();
        new HomePage(getDriver()).clickBtnLogInHeader();
        logInPage = new LogInPage(getDriver());
        logInPage.typeLogInForm(user);
        if (logInPage.isPopUpMessagePresent("Logged in success")) {
            logInPage.clickBtnOK();
            logInPage.clickBtnLetCarWork();
        } else {
            System.out.println("Something went wrong");
        }
    }

    @Test
    public void addNewCarPositiveTest() {
        CarDTO car = CarDTO.builder()
                .city("Haifa")
                .manufacture("Mazda")
                .model("CX-90")
                .year("2022")
                .fuel(Fuel.HYBRID.getLocator()) // Returns a string with the locator value
                .seats(4)
                .carClass("A")
                .serialNumber(new Random().nextInt(1000) + "-055")
                .pricePerDay(123.99)
                .about("About my car")
                .build();
        letTheCarWorkPage = new LetTheCarWorkPage(getDriver());
        letTheCarWorkPage.typeLetCarWorkForm(car);
        Assert.assertTrue(letTheCarWorkPage
                .isPopUpMessagePresent(car.getManufacture() + " " + car.getModel() + " added successful"));
    }

    @Test(dataProvider = "CarDPFile_emptyFields", dataProviderClass = CarDP.class)
    public void addNewCarNegative_emptyFields(CarDTO car) {
        letTheCarWorkPage = new LetTheCarWorkPage(getDriver());
        letTheCarWorkPage.typeLetCarWorkForm(car);
        if (car.getCity().isEmpty()) {
            softAssert.assertTrue(letTheCarWorkPage.validateErrorMessage("Wrong address"));
        }
        if (car.getManufacture().isEmpty()) {
            softAssert.assertTrue(letTheCarWorkPage.isElementPresentDOM("//*[text()=' Make is required ']", 3));
        }
        if (car.getModel().isEmpty()) {
            softAssert.assertTrue(letTheCarWorkPage.validateErrorMessage("Model is required"));
        }
        if (car.getYear().isEmpty()) {
            softAssert.assertTrue(letTheCarWorkPage.validateErrorMessage("Year required"));
        }
        if (car.getCarClass().isEmpty()) {
            softAssert.assertTrue(letTheCarWorkPage.validateErrorMessage("Car class is required"));
        }
        if (car.getSerialNumber().isEmpty()) {
            softAssert.assertTrue(letTheCarWorkPage.validateErrorMessage("Car registration number is required"));
        }
        softAssert.assertAll();
    }

    @Test(dataProvider = "CarDPFile_spaceInFields", dataProviderClass = CarDP.class)
    public void addNewCarNegativeTest_spaceInFields(CarDTO car) {
        letTheCarWorkPage = new LetTheCarWorkPage(getDriver());
        letTheCarWorkPage.typeLetCarWorkForm(car);
        if (car.getCity().isBlank()) {
            softAssert.assertTrue(letTheCarWorkPage.validateErrorMessage("Wrong address"));
        }
        if (car.getManufacture().isBlank()) {
            softAssert.assertTrue(letTheCarWorkPage.isPopUpMessagePresent("{\"manufacture\":\"must not be blank\"}"));
        }
        if (car.getModel().isBlank()) {
            softAssert.assertTrue(letTheCarWorkPage.isPopUpMessagePresent("{\"model\":\"must not be blank\"}"));
        }
        if (car.getYear().isBlank()) {
            softAssert.assertTrue(letTheCarWorkPage.validateErrorMessage("Year required"));
        }
        if (car.getCarClass().isBlank()) {
            softAssert.assertTrue(letTheCarWorkPage.isPopUpMessagePresent("{\"carClass\":\"must not be blank\"}"));
        }
        if (car.getSerialNumber().isBlank()) {
            softAssert.assertTrue(letTheCarWorkPage.isPopUpMessagePresent("{\"serialNumber\":\"must not be blank\"}"));
        }
        softAssert.assertAll();
    }

    @Test(dataProvider = "CarDPFile_properties_invalidSeats", dataProviderClass = CarDP.class)
    public void addNewCarNegativeTest_invalidSeats(CarDTO car) {
        letTheCarWorkPage = new LetTheCarWorkPage(getDriver());
        letTheCarWorkPage.typeLetCarWorkForm(car);
        if (car.getSeats() < 2) {
            softAssert.assertTrue(letTheCarWorkPage.validateErrorMessage("Car must have min 2 seat"));
        }
        if (car.getSeats() > 20) {
            softAssert.assertTrue(letTheCarWorkPage.validateErrorMessage("To much seats"));
        }
        softAssert.assertAll();
    }

    // TODO: Create a Bug Report
    // The system doesn't validate price = 0 correctly.
    @Test(dataProvider = "CarDPFile_invalidPrice", dataProviderClass = CarDP.class)
    public void addNewCarNegativeTest_invalidPrice(CarDTO car) {
        letTheCarWorkPage = new LetTheCarWorkPage(getDriver());
        letTheCarWorkPage.typeLetCarWorkForm(car);
        if (car.getPricePerDay() <= 0) {
            softAssert.assertTrue(letTheCarWorkPage.validateErrorMessage("Price must be positive"));
        }
        if (car.getPricePerDay() > 1000) {
            softAssert.assertTrue(letTheCarWorkPage.validateErrorMessage("To much big price"));
        }
        softAssert.assertAll();
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void addNewCarNegativeTest_invalidCity() {
        CarDTO car = CarDTO.builder()
                .city("12351812")
                .manufacture("Mazda")
                .model("CX-90")
                .year("2022")
                .fuel(Fuel.HYBRID.getLocator())
                .seats(4)
                .carClass("A")
                .serialNumber(new Random().nextInt(1000) + "-055")
                .pricePerDay(123.99)
                .about("About my car")
                .build();
        letTheCarWorkPage = new LetTheCarWorkPage(getDriver());
        letTheCarWorkPage.typeLetCarWorkForm(car);
        takeScreenShot((TakesScreenshot) getDriver());
        Assert.assertTrue(letTheCarWorkPage.validateErrorMessage("Wrong address"));
    }

}
