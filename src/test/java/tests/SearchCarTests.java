package tests;

import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ResultsPage;
import utils.TestNGListener;

@Listeners(TestNGListener.class)

public class SearchCarTests extends ApplicationManager {

    HomePage homePage;

    @Test
    public void searchCarPositiveTest_WOCalendar() {
        homePage = new HomePage(getDriver());
        homePage.fillSearchCarFormWOCalendar("Haifa", "12/28/2024", "12/31/2024");
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

}
