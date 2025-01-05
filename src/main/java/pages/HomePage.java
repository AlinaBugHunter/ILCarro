package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.time.DateTimeException;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

import static utils.PropertiesReader.*;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        setDriver(driver);
        driver.get("https://ilcarro.web.app/search");
        //driver.get(getProperty("login.properties", "url"));
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    // REGISTRATION + LOGIN

    @FindBy(xpath = "//a[@ng-reflect-router-link='registration']")
    WebElement btnSignUp;

    public void clickBtnSignUpHeader() {
        btnSignUp.click();
    }

    @FindBy(xpath = "//a[@ng-reflect-router-link='login']")
    WebElement btnLogIn;

    public void clickBtnLogInHeader() {
        btnLogIn.click();
    }

    // SEARCH FORM WITHOUT CALENDAR

    @FindBy(id = "city")
    WebElement inputCity;

    @FindBy(id = "dates")
    WebElement inputDates;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnYalla;

    public void fillSearchCarFormWOCalendar(String city, String startDate, String endDate) {

        inputCity.click();
        inputCity.sendKeys(city);
        Actions actions = new Actions(driver);
        actions.moveToElement(inputCity, 0, 27).pause(2000).click().perform();

        inputDates.click();
        inputDates.sendKeys(startDate + " - " + endDate);
        inputDates.sendKeys(Keys.ENTER);

    }

    // SEARCH FORM WITH CALENDAR

    @FindBy(xpath = "//button[@aria-label='Choose month and year']")
    WebElement btnYearMonth;

    public void fillSearchCarFormWithCalendar(String city, String startDate, String endDate) {

        inputCity.click();
        inputCity.sendKeys(city);
        Actions actions = new Actions(driver);
        actions.moveToElement(inputCity, 0, 27).pause(2000).click().perform();

        inputDates.click();
        String[] startDateArray = startDate.split("/");
        String[] endDateArray = endDate.split("/");
        String startMonth = convertNumberToMonth(startDateArray[0]);
        String endMonth = convertNumberToMonth(endDateArray[0]);
        typeYearMonthDay(startDateArray[2], startMonth, startDateArray[1]);
        typeYearMonthDay(endDateArray[2], endMonth, endDateArray[1]);

    }

    private void typeYearMonthDay(String year, String month, String day) {
        btnYearMonth.click();
        driver.findElement(By.xpath("//div[contains(text(), '" + year + "')]")).click();
        driver.findElement(By.xpath("//div[contains(text(), '" + month + "')]")).click();
        driver.findElement(By.xpath("//div[contains(text(), '" + day + "')]")).click();
        clickWait(btnYalla, 5);
        //inputDates.sendKeys(Keys.ENTER);
    }

    public String convertNumberToMonth(String month) {
        try {
            int monthNumber = Integer.parseInt(month); // Convert the string to an integer
            Month monthEnum = Month.of(monthNumber); // Get the month from the Month class
            return monthEnum.getDisplayName(TextStyle.SHORT, Locale.ENGLISH).toUpperCase(); // Get the month abbreviation in English
        } catch (NumberFormatException | DateTimeException e) {
            return month;
        }
    }
}
