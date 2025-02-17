package pages;

import dto.UserDTO;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class SignUpPage extends BasePage {

    public SignUpPage(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    // SIGNUP FORM

    @FindBy(id = "name")
    WebElement inputName;

    @FindBy(id = "lastName")
    WebElement inputLastName;

    @FindBy(id = "email")
    WebElement inputEmail;

    @FindBy(id = "password")
    WebElement inputPassword;

    public void typeRegistrationForm(UserDTO user) {
        inputName.sendKeys(user.getFirstName());
        inputLastName.sendKeys(user.getLastName());
        inputEmail.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
    }

    @FindBy(xpath = "//label[@for='terms-of-use']")
    WebElement checkbox;

    public void clickCheckbox() {
        int widthCheckbox = checkbox.getRect().getWidth();
        int heightCheckbox = checkbox.getRect().getHeight();
        Actions actions = new Actions(driver);
        actions.moveToElement(checkbox, -widthCheckbox / 3, -heightCheckbox / 4).click().perform();
    }

    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnYalla;

    public void clickBtnYalla() {
        btnYalla.click();
    }

    public boolean btnYallaDisabled() {
        return !btnYalla.isEnabled();
    }

    // POPUP & ERROR MESSAGE

    @FindBy(xpath = "//h2[@class='message']")
    WebElement popUpMessage;

    public boolean isPopUpMessagePresent(String text) {
        return isTextInElementPresent(popUpMessage, text);
    }

    @FindBy(xpath = "//div[@class='error']")
    WebElement errorMessage;

    public boolean validateErrorMessage(String text) {
        return isTextInElementPresent(errorMessage, text);
    }

}
