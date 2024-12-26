package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        setDriver(driver);
        driver.get("https://ilcarro.web.app/search");
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

    // SEARCH FORM

    @FindBy(id = "city")
    WebElement inputCity;

    @FindBy(id = "dates")
    WebElement inputDates;

    public void fillSearchCarFormWOCalendar(String city, String startDate, String endDate) {

        inputCity.click();
        inputCity.sendKeys(city);
        Actions actions = new Actions(driver);
        actions.moveToElement(inputCity, 0, 27).pause(2000).click().perform();

        inputDates.click();
        inputDates.sendKeys(startDate + " - " + endDate);
        inputDates.sendKeys(Keys.ENTER);

    }

}
