package support;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    private static final int DEFAULT_TIMEOUT = 10;

    private static WebDriverWait getWait() {
        return new WebDriverWait(
                DriverFactory.getDriver(),
                Duration.ofSeconds(DEFAULT_TIMEOUT)
        );
    }

    // Wait for element to be visible
    public static WebElement waitForVisibility(By locator) {
        return getWait()
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Wait for element to be clickable
    public static WebElement waitForClickable(By locator) {
        return getWait()
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Wait for element to be present in DOM
    public static WebElement waitForPresence(By locator) {
        return getWait()
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // Wait for specific URL
    public static void waitForUrlContains(String partialUrl) {
        getWait().until(
                ExpectedConditions.urlContains(partialUrl)
        );
    }
}