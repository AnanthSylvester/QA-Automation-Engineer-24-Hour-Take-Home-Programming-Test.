package pages;

import org.openqa.selenium.By;
import support.DriverFactory;
import support.WaitUtils;

public class CheckoutPage {

    private By firstName = By.id("first-name");
    private By lastName = By.id("last-name");
    private By postalCode = By.id("postal-code");
    private By continueBtn = By.id("continue");
    private By finishBtn = By.id("finish");
    private By confirmationHeader = By.className("complete-header");

    public void enterCustomerDetails(String fName, String lName, String zip) {
        WaitUtils.waitForVisibility(firstName).sendKeys(fName);
        DriverFactory.getDriver().findElement(lastName).sendKeys(lName);
        DriverFactory.getDriver().findElement(postalCode).sendKeys(zip);
        DriverFactory.getDriver().findElement(continueBtn).click();
    }

    public void finishOrder() {
        WaitUtils.waitForVisibility(finishBtn).click();
    }

    public boolean isOrderCompleteVisible() {
        return WaitUtils.waitForVisibility(confirmationHeader).isDisplayed();
    }
}