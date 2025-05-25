package pages;

import org.openqa.selenium.By;
import pages.BasePage;

public class TravelInsurancePreviousQuestionPage {

    private final BasePage basePage;

    public TravelInsurancePreviousQuestionPage(BasePage basePage) {
        this.basePage = basePage;
    }

    //////////// LOCATORS ////////////
    private final By questionHeader = By.xpath("//h1[contains(text(),'רכשת ביטוח')]");
    private final By firstTimeOption = By.cssSelector("div[data-f='first-time-no']");
    private final By alreadyPurchasedOption = By.cssSelector("div[data-f='already-purchased']");

    //////////// ACTIONS ////////////

    public void clickFirstTimeOption() {
        basePage.click(firstTimeOption);
    }

    public void clickAlreadyPurchasedOption() {
        basePage.click(alreadyPurchasedOption);
    }

    //////////// GETTERS ////////////

    public String getQuestionText() {
        return basePage.getText(questionHeader);
    }

    //////////// VALIDATIONS ////////////

    public boolean isFirstTimeOptionVisible() {
        return basePage.isDisplayed(firstTimeOption);
    }

    public boolean isAlreadyPurchasedOptionVisible() {
        return basePage.isDisplayed(alreadyPurchasedOption);
    }
}
