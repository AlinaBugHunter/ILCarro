package pages;

import dto.UserDTO;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LogInPage extends BasePage {

    public LogInPage(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    // LOGIN FORM

    @FindBy(id = "email")
    WebElement inputEmail;

    @FindBy(id = "password")
    WebElement inputPassword;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnYalla;

    public void typeLogInForm(UserDTO user) {
        inputEmail.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
        btnYalla.click();
    }

    public boolean btnYallaDisabled() {
        return !btnYalla.isEnabled();
    }

    // POPUP MESSAGE

    public boolean isPopUpMessagePresent(String text) {
        return isTextInElementPresent(popUpMessage, text);
    }

    @FindBy(xpath = "//button[@type='button']")
    WebElement btnOK;

    public void clickBtnOK() {
        clickWait(btnOK, 5);
    }

    // ERROR MESSAGE

    @FindBy(xpath = "//div[@class='error']")
    WebElement errorMessage;

    public boolean validateErrorMessage(String text) {
        pause(2);
        return isTextInElementPresent(errorMessage, text);
    }

    // LET THE CAR WORK PAGE

    @FindBy(xpath = "//a[@id='1']")
    WebElement btnLetCarWork;

    public void clickBtnLetCarWork() {
        pause(3);
        btnLetCarWork.click();
//        clickWait(btnLetCarWork, 3);
    }

}
