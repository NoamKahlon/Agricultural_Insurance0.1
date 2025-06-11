package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

/**
 * BasePage provides reusable utility methods for web UI interactions using Selenium WebDriver.
 * It supports scrolling, clicking, waiting, dropdowns, tab switching, and more.
 */
public class BasePage {

    private final WebDriver driver;
    private final int secondsToWait;

    // ========= LOCATORS =========
    private final By steps = By.cssSelector("div.step");
    private final By cookieCloseButton = By.id("acceptButton");

    // ========= CONSTANTS =========
    private final String baseUrl = "https://www.bth.co.il/";

    // ========= Constructor =========
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.secondsToWait = 5;
    }

    // ========= ACTIONS =========

    public void click(By locator) {
        WebElement element = waitForElementToBeClickable(locator);
        element.click();
    }

    public void forceClick(By locator) {
        WebElement element = waitForElementToBeClickable(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void clickWhenClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
        el.click();
    }

    public void clickCheckbox(By locator) {
        WebElement checkbox = waitForElementToBeClickable(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
    }

    public void sendKeys(By locator, CharSequence... keys) {
        WebElement element = waitForElementToAppear(locator);
        element.sendKeys(keys);
    }

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

    public void openMainPage() {
        driver.get(baseUrl);
        waitForPageToLoad();
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

    public void switchToIframe(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(secondsToWait));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

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

    public void scrollToElementForce(By locator) {
        WebElement element = waitForElementToAppear(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'auto', block: 'start'});", element);
    }

    // ========= VERIFICATIONS =========

    public String getTitle() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver -> !driver.getTitle().trim().isEmpty());
        return driver.getTitle().trim();
    }

    public String getText(By locator) {
        WebElement element = waitForElementToAppear(locator);
        return element.getText();
    }

    public boolean isDisplayed(By locator) {
        WebElement element = waitForElementToAppear(locator);
        return element.isDisplayed();
    }

    public String getStepBarColorValidationResult() {
        List<WebElement> steps = getElements(this.steps);
        boolean currentStepFound = false;
        boolean allValid = true;

        StringBuilder stepsSummary = new StringBuilder();

        for (WebElement step : steps) {
            String text = step.getText().trim();
            String classValue = step.getAttribute("class").trim();
            String backgroundColor = step.getCssValue("background-color").replace(" ", "");

            if (classValue.contains("current")) {
                currentStepFound = true;
                if (!backgroundColor.contains("31,47,102")) {
                    allValid = false;
                    stepsSummary.append("Current step: ").append(text).append(" color: WRONG (not blue)\n");
                } else {
                    stepsSummary.append("Current step: ").append(text).append(" color: blue\n");
                }
                break;
            } else {
                if (!backgroundColor.contains("96,187,112")) {
                    allValid = false;
                    stepsSummary.append("Completed step: ").append(text).append(" color: WRONG (not green)\n");
                } else {
                    stepsSummary.append("Completed step: ").append(text).append(" color: green\n");
                }
            }
        }

        if (!currentStepFound) {
            allValid = false;
            stepsSummary.append("ERROR: No current step found!\n");
        }

        return (allValid ? "PASS\n" : "FAIL\n") + stepsSummary.toString();
    }

    // ========= ELEMENT GETTERS =========

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

    // ========= WAITS =========

    public WebElement waitForElementToAppear(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(secondsToWait));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementToBeClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(secondsToWait));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForPageToLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
        );
    }

    // ========= MISC =========

    public void closeCookieBannerIfPresent() {
        try {
            waitForElementToAppear(cookieCloseButton);
            click(cookieCloseButton);
        } catch (TimeoutException | NoSuchElementException ignored) {
        }
    }
}
