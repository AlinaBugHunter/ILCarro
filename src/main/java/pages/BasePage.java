package pages;

import org.openqa.selenium.WebDriver;

public class BasePage {

//    static WebDriver driver;
//
//    public static void setDriver(WebDriver wd) {
//        driver = wd;
//    }

    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }


    public void pause(int time) {
        try {
            Thread.sleep(time * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
