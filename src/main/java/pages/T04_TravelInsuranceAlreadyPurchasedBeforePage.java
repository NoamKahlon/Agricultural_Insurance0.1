package pages;

import org.openqa.selenium.By;

public class T04_TravelInsuranceAlreadyPurchasedBeforePage {

    //////////// BASE ////////////
    private final BasePage basePage;

    //////////// FORM ELEMENTS ////////////
    private final By alreadyPurchasedRadioButton = By.cssSelector("div[data-f='already-purchased']");
    private final By happyToSeeYouMessage = By.xpath("//*[contains(text(),'שמחים לראותך שוב!')]");
    private final By phoneField = By.id("phone");
    private final By idField = By.id("id");

    //////////// CONSTRUCTOR ////////////
    public T04_TravelInsuranceAlreadyPurchasedBeforePage(BasePage basePage) {
        this.basePage = basePage;
    }

    //////////// ACTIONS ////////////

    public void clickAlreadyPurchasedOption() {
        basePage.click(alreadyPurchasedRadioButton);
    }

    //////////// VALIDATIONS ////////////

    public boolean isPhoneFieldDisplayed() {
        return basePage.isDisplayed(phoneField);
    }

    public boolean isIdFieldDisplayed() {
        return basePage.isDisplayed(idField);
    }

    public boolean isHappyTextDisplayed() {
        return basePage.isDisplayed(happyToSeeYouMessage);
    }
}
