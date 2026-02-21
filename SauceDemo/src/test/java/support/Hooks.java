package support;

import config.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class Hooks {

    @Before
    public void setUp() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("base_url"));
    }

    @After
    public void tearDown(Scenario scenario) {

        WebDriver driver = DriverFactory.getDriver();

        if (scenario.isFailed() && driver != null) {

            try {

                // ✅ Attach screenshot to Cucumber report
                byte[] screenshot = ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.BYTES);

                scenario.attach(
                        screenshot,
                        "image/png",
                        "Failure Screenshot"
                );

                // ✅ Save screenshot as file in target/screenshots
                File src = ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.FILE);

                File dest = new File(
                        "target/screenshots/" +
                                scenario.getName().replaceAll(" ", "_") +
                                ".png"
                );

                FileUtils.copyFile(src, dest);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        DriverFactory.quitDriver();
    }
}