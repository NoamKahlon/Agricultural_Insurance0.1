package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class T09_TravelInsuranceCoveragesForYouPage {

    private final BasePage basePage;

    //////////// Locators ////////////
    ///
    private final By coverageTitles = By.cssSelector("div.title");
    private final By finalPriceField = By.className("finalPriceValue");
    private final By backButton = By.cssSelector("div[data-f='back']");
    private final By continueButton = By.cssSelector("div[data-f='continue']");
    private final By noThanksButton = By.cssSelector("div[data-f='no-thanks']");
    private final By medicalCoverageOption = By.xpath("//div[@class='title' and text()='הרחבה לביטול וקיצור נסיעה מסיבות רפואיות']/ancestor::button");
    private final By noPopUpOption = By.xpath("//label[text()='לא']");
    private final By confirmButton = By.xpath("//button[.//div[text()='אישור']]");

    public T09_TravelInsuranceCoveragesForYouPage(BasePage basePage) {
        this.basePage = basePage;
    }

    //////////// ACTIONS ////////////

    public List<WebElement> getCoverageTitles() {
        return basePage.getElements(coverageTitles);
    }

    public void clickContinue() {
        basePage.forceClick(continueButton);
        basePage.forceClick(noThanksButton);
        basePage.forceClick(continueButton);
    }

    public void chooseMedicalCoverage() {
        basePage.click(medicalCoverageOption);
        basePage.click(noPopUpOption);
        basePage.click(confirmButton);
    }

    //////////// Validations ////////////

    public boolean isBackButtonVisible() {
        return basePage.isDisplayed(backButton);
    }

    public boolean isContinueButtonVisible() {
        return basePage.isDisplayed(continueButton);
    }

    public boolean isFinalPriceVisible() {
        return basePage.isDisplayed(finalPriceField);
    }

    //////////// Utilities ////////////
    ///
    public String getFinalPriceText() {
        return isFinalPriceVisible() ? basePage.getText(finalPriceField).trim() : "";
    }

    public double getFinalPriceValue() {
        String text = getFinalPriceText();
        Matcher matcher = Pattern.compile("\\d+(\\.\\d+)?").matcher(text);
        if (matcher.find()) {
            try {
                return Double.parseDouble(matcher.group());
            } catch (NumberFormatException e) {
                return -1.0;
            }
        }
        return -1.0;
    }
}
