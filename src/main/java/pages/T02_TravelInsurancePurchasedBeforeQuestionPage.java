package pages;

import org.openqa.selenium.By;

public class T02_TravelInsurancePurchasedBeforeQuestionPage {

    private final BasePage basePage;

    //////////// LOCATORS ////////////
    private final By questionHeader = By.xpath("//h1[contains(text(),'רכשת ביטוח')]");
    private final By firstTimeOption = By.cssSelector("div[data-f='first-time-no']");
    private final By alreadyPurchasedOption = By.cssSelector("div[data-f='already-purchased']");
    private final By continueButton = By.cssSelector("button.procceed");
    private final By backToQuestion = By.xpath("//h1[contains(text(),'רכשת ביטוח')]");

    //////////// CONSTRUCTOR ////////////
    public T02_TravelInsurancePurchasedBeforeQuestionPage(BasePage basePage) {
        this.basePage = basePage;
    }

    //////////// ACTIONS ////////////
    public void clickFirstTimeOption() {
        basePage.click(firstTimeOption);
    }

    public void clickAlreadyPurchasedOption() {
        basePage.click(alreadyPurchasedOption);
    }

    public void clickOnContinueButton() {
        basePage.forceClick(continueButton);
    }

    public void scrollToContinueButton() {
        basePage.scrollToElement(continueButton);
    }

    //////////// GETTERS ////////////
    public String getQuestionText() {
        return basePage.getText(questionHeader);
    }

    public String getBackToQuestionText() {
        return basePage.getText(backToQuestion);
    }

    //////////// VALIDATIONS ////////////
    public boolean isFirstTimeOptionVisible() {
        return basePage.isDisplayed(firstTimeOption);
    }

    public boolean isAlreadyPurchasedOptionVisible() {
        return basePage.isDisplayed(alreadyPurchasedOption);
    }
}
