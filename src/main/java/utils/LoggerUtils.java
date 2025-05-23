package utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.ByteArrayInputStream;
import java.time.Duration;

public class LoggerUtils {


    private final WebDriver driver;
    public final SoftAssert softAssert;


    public LoggerUtils(WebDriver driver, SoftAssert softAssert) {
        this.driver = driver;
        this.softAssert = softAssert;
    }


    public enum LogMode {
        SOFT, HARD
    }


    public void log(String message, String screenshotName, boolean isPass, boolean hard) {
        Allure.step(message, () -> {
            if (!screenshotName.isEmpty()) {
                // wait for element to load
//                new WebDriverWait(driver, Duration.ofSeconds(10)).until(
//                        webDriver -> ((JavascriptExecutor) webDriver)
//                                .executeScript("return document.readyState").equals("complete"));
                attachScreenshot(screenshotName);
            }

            if (!isPass) {
                if (hard) {
                    throw new AssertionError(message);
                } else {
                    softAssert.fail(message);
                }
            }
        });
    }

    // יש לקרוא לזה בסוף כל טסט שמשתמש ב־LogMode.SOFT
//    public void assertAllSoft() {
//        softAssert.assertAll();
//    }

    void assertTextEquals(String actual, String expected, String stepName) {
        boolean equals = actual.trim().equals(expected.trim());
        log(
                (equals ? "✅ Passed: " : "❌ Failed: ") + stepName +
                        " (Expected: '" + expected + "'  Actual: '" + actual + "')", "Assert_" + stepName, equals, true);

        Assert.assertEquals(actual.trim(), expected.trim(), stepName);
    }


    public void assertWithLog(String message, boolean condition, boolean isHardAssert) {
        if (condition) {
            log("✅ " + message, "", true, false);
        } else {
            log("❌ " + message, "", false, false);

            if (isHardAssert) {
                Assert.fail("❌ " + message);
            } else {
                softAssert.assertTrue(false, "❌ " + message);
            }
        }
    }



    public void attachScreenshot(String name) {
        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(name, new ByteArrayInputStream(screenshot));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
