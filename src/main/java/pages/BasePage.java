package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    static WebDriver driver;

    public static void setDriver(WebDriver wd) {
        driver = wd;
    }

    // LOCATORS

    @FindBy(xpath = "//h2[@class='message']")
    WebElement popUpMessage;

    // METHODS

    public void pause(int time) {
        try {
            Thread.sleep(time * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void clickWait(WebElement element, int time) {
        try {
            new WebDriverWait(driver, time)
                    .until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception e) {
            System.out.println("clickWait() Created Exception");
            System.out.println(e.getMessage());
        }
    }

    public boolean validateURL(String url, int time){
        return new WebDriverWait(driver, time).until(ExpectedConditions.urlContains(url));
    }

    public boolean isTextInElementPresent(WebElement element, String text) {
        return element.getText().contains(text);
    }

    public boolean isElementPresentDOM(String locator, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
            return true;
        } catch (Exception e) {
            System.out.println("isElementPresentDOM() -> Created Exception");
            System.out.println(e.getMessage());
            return false;
        }
    }

}
