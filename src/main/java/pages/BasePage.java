package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.*;

public class BasePage {

    private final WebDriver driver;
    private final int secondsToWait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        secondsToWait = 5;
    }

    // Unified click
    public boolean click(By locator, boolean hard) {
        try {
            WebElement element = waitForElementToBeClickable(locator);
            element.click();
            return true;
        } catch (Exception e) {
            if (hard) throw new RuntimeException("❗ Failed to click element: " + locator, e);
            return false;
        }
    }

    public boolean forceClick(By locator, boolean hard) {
        try {
            WebElement element = waitForElementToBeClickable(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            return true;
        } catch (Exception e) {
            if (hard) throw new RuntimeException("❗ Failed to force click element: " + locator, e);
            return false;
        }
    }

    public boolean scrollToElement(By locator) {
        try {
            WebElement element = waitForElementToAppear(locator);
            Number initialScrollY = (Number) ((JavascriptExecutor) driver).executeScript("return window.scrollY;");
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            return wait.until(d -> {
                Number currentScrollY = (Number) ((JavascriptExecutor) d).executeScript("return window.scrollY;");
                return currentScrollY != null && !currentScrollY.equals(initialScrollY);
            });
        } catch (Exception e) {
            return false;
        }
    }

    public boolean scrollToElementSafe(By locator) {
        try {
            WebElement element = waitForElementToAppear(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
            Thread.sleep(200);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void scrollToElementForce(By locator) throws Exception {
        try {
            WebElement element = waitForElementToAppear(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'auto', block: 'start'});", element);
        } catch (Exception e) {
            throw new Exception("❗ Failed to force-scroll to element: " + locator, e);
        }
    }

    public void pickDate(By locator, String date) {
        try {
            waitForElementToAppear(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('readonly'); arguments[0].value = arguments[1];", driver.findElement(locator), date);
        } catch (Exception e) {
            throw new RuntimeException("❗ Failed to pick date '" + date + "' for locator: " + locator, e);
        }
    }

    public void chooseValueFromDropDownMenu(By locator, int index) {
        try {
            waitForElementToAppear(locator);
            WebElement dropDownMenu = getElement(locator);
            new Select(dropDownMenu).selectByIndex(index);
        } catch (Exception e) {
            throw new RuntimeException("❗ Failed to select index " + index + " from dropdown: " + locator, e);
        }
    }

    public boolean chooseValueFromDropDownMenuSafe(By locator, String visibleText) {
        try {
            waitForElementToAppear(locator);
            WebElement dropDown = getElement(locator);
            new Select(dropDown).selectByVisibleText(visibleText);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickCheckbox(By locator) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("Europe")));
        } catch (Exception e) {
            throw new RuntimeException("❗ Failed to click Check box " + getElement(locator) + " from dropdown: " + locator, e);
        }
    }

    public boolean sendKeys(By locator, String keys, boolean hard) {
        try {
            WebElement element = waitForElementToAppear(locator);
            element.sendKeys(keys);
            return true;
        } catch (Exception e) {
            if (hard) throw new RuntimeException("❗ Failed to send keys to: " + locator, e);
            return false;
        }
    }

    public String getTitle() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(secondsToWait));
            wait.until(driver -> {
                String title = driver.getTitle();
                return title != null && title.length() > 5;
            });
            return driver.getTitle();
        } catch (Exception e) {
            return "";
        }
    }

    public String getText(By locator, boolean hard) {
        try {
            WebElement element = waitForElementToAppear(locator);
            return element.getText();
        } catch (Exception e) {
            if (hard) throw new RuntimeException("❗ Failed to get text from: " + locator, e);
            return "";
        }
    }

    public WebElement getElement(By locator) {
        return waitForElementToAppear(locator);
    }

    public List<WebElement> getElements(By locator) {
        try {
            waitForElementToAppear(locator);
            return driver.findElements(locator);
        } catch (Exception e) {
            throw new RuntimeException("❗ Failed to get elements: " + locator, e);
        }
    }

    public List<WebElement> getElementsSafe(By locator) {
        try {
            waitForElementToAppear(locator);
            return driver.findElements(locator);
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public void openMainPage() {
        try {
            driver.get("https://www.bth.co.il/");
            waitForPageToLoad();
        } catch (Exception e) {
            //throw new RuntimeException("❗ Failed to open Main Page", e);
        }
    }

    public void waitForPageToLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
        );
    }

    public WebElement waitForElementToAppear(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(secondsToWait));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            throw new RuntimeException("❗ Element did not appear: " + locator, e);
        }
    }

    public WebElement waitForElementToBeClickable(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(secondsToWait));
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            throw new RuntimeException("❗ Element not clickable: " + locator, e);
        }
    }

    public boolean isDisplayed(By locator, boolean hard) {
        try {
            WebElement element = waitForElementToAppear(locator);
            return element.isDisplayed();
        } catch (Exception e) {
            if (hard) throw new RuntimeException("❗ Failed to check display: " + locator, e);
            return false;
        }
    }

    public boolean switchToNewTab() {
        try {
            String originalWindow = driver.getWindowHandle();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(secondsToWait));
            wait.until(driver -> driver.getWindowHandles().size() > 1);
            for (String windowHandle : driver.getWindowHandles()) {
                if (!windowHandle.equals(originalWindow)) {
                    driver.switchTo().window(windowHandle);
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            System.err.println("❗ Failed to switch to new tab: " + e.getMessage());
            return false;
        }
    }

}
