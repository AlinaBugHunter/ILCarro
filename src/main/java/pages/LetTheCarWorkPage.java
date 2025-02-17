package pages;

import dto.CarDTO;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LetTheCarWorkPage extends BasePage {

    public LetTheCarWorkPage(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    // LET THE CAR WORK FORM

    @FindBy(id = "pickUpPlace")
    WebElement inputLocation;

    @FindBy(xpath = "//div[@class='pac-item']")
    WebElement locationSubmit;

    @FindBy(id = "make")
    WebElement inputManufacture;

    @FindBy(id = "model")
    WebElement inputModel;

    @FindBy(id = "year")
    WebElement inputYear;

    @FindBy(id = "fuel")
    WebElement inputFuel;

    @FindBy(id = "seats")
    WebElement inputSeats;

    @FindBy(id = "class")
    WebElement inputClass;

    @FindBy(id = "serialNumber")
    WebElement inputSerialNumber;

    @FindBy(id = "price")
    WebElement inputPrice;

    @FindBy(id = "about")
    WebElement inputAbout;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnSubmit;

    public void typeLetCarWorkForm(CarDTO car) {

        inputLocation.sendKeys(car.getCity());
        clickWait(locationSubmit, 5); // Waiting for the dropdown list of items to appear and selecting the first one

        inputManufacture.sendKeys(car.getManufacture());
        inputModel.sendKeys(car.getModel());
        inputYear.sendKeys(car.getYear());

        inputFuel.click();
        clickWait(driver.findElement(By.xpath(car.getFuel())), 5);
        inputFuel.sendKeys(Keys.ESCAPE);

        inputSeats.sendKeys(Integer.toString(car.getSeats()));
        inputClass.sendKeys(car.getCarClass());
        inputSerialNumber.sendKeys(car.getSerialNumber());
        inputPrice.sendKeys(Double.toString(car.getPricePerDay()));
        inputAbout.sendKeys(car.getAbout());
        clickWait(btnSubmit, 5);

    }

    // POPUP MESSAGE

    public boolean isPopUpMessagePresent(String text) {
        return isTextInElementPresent(popUpMessage, text);
    }

    // ERROR MESSAGE

    @FindBy(xpath = "//div[@class='error ng-star-inserted']")
    WebElement errorMessage;

    public boolean validateErrorMessage(String text) {
        pause(2);
        return isTextInElementPresent(errorMessage, text);
    }

}
