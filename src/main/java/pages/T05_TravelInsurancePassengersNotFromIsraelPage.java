package pages;

import org.openqa.selenium.By;

public class T05_TravelInsurancePassengersNotFromIsraelPage {

    private final BasePage basePage;

    //////////// LOCATORS ////////////
    private final By backButton = By.cssSelector("button.iGkwwW.goback");
    private final By purchaseBlockedTitle = By.xpath("//h1[contains(text(),'לצערנו לא נוכל להמשיך')]");
    private final By backToHomeButton = By.xpath("//button[contains(.,'בחזרה לעמוד הבית')]");

    //////////// CONSTRUCTOR ////////////
    public T05_TravelInsurancePassengersNotFromIsraelPage(BasePage basePage) {
        this.basePage = basePage;
    }

    //////////// ACTIONS ////////////
    public void clickPassengersAreNotFromIsrael() {
        basePage.click(backButton);
    }

    //////////// VALIDATIONS ////////////
    public boolean isStopProcessErrorDisplayed() {
        return basePage.isDisplayed(purchaseBlockedTitle);
    }

    public boolean isBackToHomeButtonDisplayed() {
        return basePage.isDisplayed(backToHomeButton);
    }
}
