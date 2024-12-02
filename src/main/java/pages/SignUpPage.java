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

    @FindBy(id="name")
    WebElement inputName;

    @FindBy(id="lastName")
    WebElement inputLastName;

    @FindBy(id="email")
    WebElement inputEmail;

    @FindBy(id="password")
    WebElement inputPassword;

    public void typeRegistrationForm(UserDTO user) {
        inputName.sendKeys(user.getName());
        inputLastName.sendKeys(user.getLastName());
        inputEmail.sendKeys(user.getEmail());
        inputPassword.sendKeys(user.getPassword());
    }

    @FindBy(xpath = "//label[@for='terms-of-use']")
    WebElement checkbox;

    public void clickCheckbox() {
        int widthCheckbox = checkbox.getRect().getWidth();
        int heightCheckbox = checkbox.getRect().getHeight();
        Actions actions = new Actions(driver);
        actions.moveToElement(checkbox, - widthCheckbox / 3, - heightCheckbox / 4).click().perform();
    }

    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnYalla;

    public void clickBtnYalla() {
        btnYalla.click();
    }

    @FindBy(xpath = "//h2[@class='message']")
    WebElement popUpMessage;

    public boolean isPopUpMessagePresent() {
        return isTextInElementPresent(popUpMessage, "You are logged in success");
    }

}
