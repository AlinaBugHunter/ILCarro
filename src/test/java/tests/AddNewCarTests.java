package tests;

import data_providers.CarDP;
import dto.CarDTO;
import dto.UserDTO;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LetTheCarWorkPage;
import pages.LogInPage;
import utils.Fuel;
import utils.RetryAnalyzer;
import utils.TestNGListener;

import java.lang.reflect.Method;
import java.util.Random;

@Listeners(TestNGListener.class)

public class AddNewCarTests extends ApplicationManager {

    LogInPage logInPage;
    LetTheCarWorkPage letTheCarWorkPage;

    @BeforeMethod
    public void login() {
        UserDTO user = UserDTO.builder()
                .email("testemail@example.com")
                .password("Password123!")
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
                .serialNumber(new Random().nextInt(1000) + "-055")
                .city("Haifa")
                .manufacture("Mazda")
                .model("CX-90")
                .year("2022")
                .fuel(Fuel.HYBRID.getLocator()) // Returns a string with the locator value
                .seats(4)
                .carClass("A")
                .pricePerDay(123.99)
                .about("About my car")
                .build();
        letTheCarWorkPage = new LetTheCarWorkPage(getDriver());
        letTheCarWorkPage.typeLetCarWorkForm(car);
        Assert.assertTrue(letTheCarWorkPage
                .isPopUpMessagePresent(car.getManufacture() + " " + car.getModel() + " added successful"));
    }

    @Test(dataProvider = "CarDPFile", dataProviderClass = CarDP.class)
    public void addNewCarPositiveTestDP(CarDTO car, Method method) {
        logger.info(method.getName() + " Start with data -> " + car.toString());
        letTheCarWorkPage = new LetTheCarWorkPage(getDriver());
        letTheCarWorkPage.typeLetCarWorkForm(car);
        Assert.assertTrue(letTheCarWorkPage
                .isPopUpMessagePresent(car.getManufacture() + " " + car.getModel() + " added successful"));
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void addNewCarNegativeTest_emptyManufacture() {
        CarDTO car = CarDTO.builder()
                .serialNumber(new Random().nextInt(1000) + "-055")
                .city("Haifa")
                .manufacture("")
                .model("CX-90")
                .year("2022")
                .fuel(Fuel.HYBRID.getLocator())
                .seats(4)
                .carClass("A")
                .pricePerDay(123.99)
                .about("About my car")
                .build();
        letTheCarWorkPage = new LetTheCarWorkPage(getDriver());
        letTheCarWorkPage.typeLetCarWorkForm(car);
        Assert.assertTrue(letTheCarWorkPage.isElementPresentDOM("//*[text()=' Make is required ']", 3));
    }

}
