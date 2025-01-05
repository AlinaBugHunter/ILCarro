package tests;

import manager.ApplicationManager;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ResultsPage;
import utils.TestNGListener;

import static utils.TakeScreenShot.takeScreenShot;

@Listeners(TestNGListener.class)

public class SearchCarTests extends ApplicationManager {

    HomePage homePage;

    @Test
    public void searchCarPositiveTest_WOCalendar() {
        homePage = new HomePage(getDriver());
        homePage.fillSearchCarFormWOCalendar("Haifa", "02/27/2025", "02/28/2025");
        takeScreenShot((TakesScreenshot) getDriver());
        Assert.assertTrue(new ResultsPage(getDriver()).isURLResultsPresent());
    }

    @Test
    public void searchCarPositiveTest_WithCalendar() {
        logger.info("Start test searchCarPositiveTestWithCalendar() with data -> " + "Haifa" + " 02/27/2025 " + "03/15/2025");
        homePage = new HomePage(getDriver());
        homePage.fillSearchCarFormWithCalendar("Haifa", "02/27/2025", "03/15/2025");
        Assert.assertTrue(new ResultsPage(getDriver()).isURLResultsPresent());
    }

    @Test
    public void searchCarNegativeTest_emptyCity() {
        homePage = new HomePage(getDriver());
        homePage.fillSearchCarFormWOCalendar("", "02/27/2025", "03/15/2025");
        Assert.assertTrue(homePage.isElementPresentDOM("//*[text()=' City is required ']", 3));
    }

    @Test
    public void searchCarNegativeTest_emptyDate() {
        homePage = new HomePage(getDriver());
        homePage.fillSearchCarFormWOCalendar("Haifa", "", "");
        Assert.assertTrue(homePage.isElementPresentDOM("//*[text()=' Dates are required ']", 3));
    }

    @Test
    public void searchCarNegativeTest_invalidDate() {
        homePage = new HomePage(getDriver());
        homePage.fillSearchCarFormWOCalendar("Haifa", "02/27/2025", "02/27/2025");
        Assert.assertTrue(homePage.isElementPresentDOM("//div[@class='ng-star-inserted']", 3));
    }

}
