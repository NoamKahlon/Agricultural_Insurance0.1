package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class BasePage {

    private final WebDriver driver;
    private final int secondsToWait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.secondsToWait = 5;
    }

    // ==============================================
    //                    ACTIONS
    // ==============================================

    public void click(By locator) {
        WebElement element = waitForElementToBeClickable(locator);
        element.click();
    }

    public void forceClick(By locator) {
        WebElement element = waitForElementToBeClickable(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void sendKeys(By locator, String keys) {
        WebElement element = waitForElementToAppear(locator);
        element.sendKeys(keys);
    }

    public void clickCheckbox(By locator) {
        WebElement checkbox = getElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
    }

    // ==============================================
    //                    SCROLL
    // ==============================================

    public boolean scrollToElement(By locator) {
        WebElement element = waitForElementToAppear(locator);
        Number initialScrollY = (Number) ((JavascriptExecutor) driver).executeScript("return window.scrollY;");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        return wait.until(d -> {
            Number currentScrollY = (Number) ((JavascriptExecutor) d).executeScript("return window.scrollY;");
            return currentScrollY != null && !currentScrollY.equals(initialScrollY);
        });
    }

    public boolean scrollToElementSafe(By locator) {
        WebElement element = waitForElementToAppear(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
        try {
            Thread.sleep(200);
        } catch (InterruptedException ignored) {}
        return true;
    }

    public void scrollToElementForce(By locator) {
        WebElement element = waitForElementToAppear(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'auto', block: 'start'});", element);
    }

    // ==============================================
    //                    FORM
    // ==============================================

    public void pickDate(By locator, String date) {
        waitForElementToAppear(locator);
        WebElement input = getElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('readonly'); arguments[0].value = arguments[1];", input, date);
    }

    public void chooseValueFromDropDownMenu(By locator, int index) {
        waitForElementToAppear(locator);
        WebElement dropDown = getElement(locator);
        new Select(dropDown).selectByIndex(index);
    }

    public void chooseValueFromDropDownMenuSafe(By locator, String visibleText) {
        waitForElementToAppear(locator);
        WebElement dropDown = getElement(locator);
        new Select(dropDown).selectByVisibleText(visibleText);
    }

    // ==============================================
    //                    GET
    // ==============================================

    public String getTitle() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(secondsToWait));
        wait.until(d -> {
            String title = d.getTitle();
            return title != null && title.length() > 5;
        });
        return driver.getTitle();
    }

    public String getText(By locator) {
        WebElement element = waitForElementToAppear(locator);
        return element.getText();
    }

    public WebElement getElement(By locator) {
        return waitForElementToAppear(locator);
    }

    public List<WebElement> getElements(By locator) {
        waitForElementToAppear(locator);
        return driver.findElements(locator);
    }

    public List<WebElement> getElementsSafe(By locator) {
        try {
            waitForElementToAppear(locator);
            return driver.findElements(locator);
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    // ==============================================
    //                ASSERTIONS / CHECKS
    // ==============================================

    public boolean isDisplayed(By locator) {
        WebElement element = waitForElementToAppear(locator);
        return element.isDisplayed();
    }

    // ==============================================
    //                   UTILITIES
    // ==============================================

    public void openMainPage() {
        driver.get("https://www.bth.co.il/");
        waitForPageToLoad();
    }

    public void waitForPageToLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
        );
    }

    public WebElement waitForElementToAppear(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(secondsToWait));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementToBeClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(secondsToWait));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public boolean switchToNewTab() {
        String originalWindow = driver.getWindowHandle();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(secondsToWait));
        wait.until(d -> driver.getWindowHandles().size() > 1);
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                return true;
            }
        }
        return false;
    }



}
