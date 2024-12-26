package tests;

import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ResultsPage;

public class SearchCarTests extends ApplicationManager {

    HomePage homePage;

    @Test
    public void searchCarPositiveTest() {
        homePage = new HomePage(getDriver());
        homePage.fillSearchCarFormWOCalendar("Haifa", "12/26/2024", "12/27/2024");
        Assert.assertTrue(new ResultsPage(getDriver()).isURLResultsPresent());
    }

}
