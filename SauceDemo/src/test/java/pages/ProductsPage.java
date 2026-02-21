package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import support.DriverFactory;
import support.WaitUtils;

public class ProductsPage {

    private By productsTitle = By.className("title");

    private By inventoryContainer =
            By.className("inventory_list");

    private By backpackAddToCart =
            By.cssSelector("[data-test='add-to-cart-sauce-labs-backpack']");

    private By cartBadge =
            By.cssSelector("[data-test='shopping-cart-badge']");

    private By cartIcon =
            By.cssSelector("[data-test='shopping-cart-link']");

    public boolean isProductsPageVisible() {

        // Wait until inventory container is visible (ensures page fully loaded)
        WaitUtils.waitForVisibility(inventoryContainer);

        return WaitUtils.waitForVisibility(productsTitle).isDisplayed();
    }

    public void addBackpackToCart() {

        // Ensure page loaded before clicking
        WaitUtils.waitForVisibility(inventoryContainer);

        // Wait until button is clickable instead of just visible
        WebElement button =
                WaitUtils.waitForClickable(backpackAddToCart);

        button.click();
    }

    public boolean isCartBadgeUpdated() {

        WebElement badge =
                WaitUtils.waitForVisibility(cartBadge);

        return badge.getText().equals("1");
    }

    public void openCart() {

        WebElement cart =
                WaitUtils.waitForClickable(cartIcon);

        ((JavascriptExecutor) DriverFactory.getDriver())
                .executeScript("arguments[0].click();", cart);
    }
}