package utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


import java.io.ByteArrayInputStream;

/**
 * Logger utility to centralize Allure logging and screenshot capturing.
 * Includes support for soft assertions and conditional error throwing.
 */
public class LoggerUtils {

    private final WebDriver driver;

    /**
     * Constructor for LoggerUtils
     * @param driver WebDriver instance for taking screenshots
     */
    public LoggerUtils(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Logs a step to Allure with optional screenshot and pass/fail status.
     * If the step fails, throws AssertionError to mark the test failed.
     *
     * @param message descriptive step message
     * @param screenshotName screenshot label (empty string to skip)
     * @param isPass whether this step should be considered successful
     */
    public void log(String message, String screenshotName, boolean isPass) {
        Allure.step(message, () -> {
            if (!screenshotName.isEmpty()) {
                attachScreenshot(screenshotName);
            }
            if (!isPass) {
                throw new AssertionError(message);
            }
        });
    }

    /**
     * Attaches a screenshot to the Allure report.
     *
     * @param name label for the screenshot in the report
     */
    public void attachScreenshot(String name) {
        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(name, new ByteArrayInputStream(screenshot));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}