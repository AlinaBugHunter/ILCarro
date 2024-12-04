package pages;

import dto.UserDTO;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LogInPage extends BasePage {

    String GOODRESPONSE = "Logged in success";

    public LogInPage(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(id="email")
    WebElement inputEmail;

    @FindBy(id="password")
    WebElement inputPassword;

    public void typeLogInForm(UserDTO user) {
        inputEmail.sendKeys(user.getEmail());
        inputPassword.sendKeys(user.getPassword());
    }

    @FindBy(xpath = "//button[text()='Y’alla!']")
    WebElement btnYalla;

    public void clickBtnYalla() {
        btnYalla.click();
    }

    @FindBy(xpath = "//h2[@class='message']")
    WebElement popUpMessage;

    public boolean isPopUpMessagePresent() {
        return popUpMessage.getText().equals(GOODRESPONSE);
    }

}
