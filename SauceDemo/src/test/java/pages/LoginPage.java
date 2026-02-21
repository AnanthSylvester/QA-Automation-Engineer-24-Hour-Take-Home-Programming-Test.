package pages;

import org.openqa.selenium.By;
import support.DriverFactory;
import support.WaitUtils;

public class LoginPage {

    private By username = By.id("user-name");
    private By password = By.id("password");
    private By loginBtn = By.id("login-button");
    private By errorMsg = By.cssSelector("[data-test='error']");

    public void login(String user, String pass) {
        WaitUtils.waitForVisibility(username).sendKeys(user);
        DriverFactory.getDriver().findElement(password).sendKeys(pass);
        DriverFactory.getDriver().findElement(loginBtn).click();
    }

    public boolean isErrorDisplayed() {
        return WaitUtils.waitForVisibility(errorMsg).isDisplayed();
    }
}