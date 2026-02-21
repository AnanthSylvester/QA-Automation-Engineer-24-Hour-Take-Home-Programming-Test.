package pages;

import org.openqa.selenium.By;
import support.WaitUtils;

public class CartPage {

    private By checkoutBtn =
            By.cssSelector("[data-test='checkout']");

    public void clickCheckout() {
        WaitUtils.waitForVisibility(checkoutBtn).click();
    }
}