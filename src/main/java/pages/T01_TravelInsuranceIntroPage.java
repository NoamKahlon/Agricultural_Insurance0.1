package pages;

import org.openqa.selenium.By;

public class T01_TravelInsuranceIntroPage {

    private final BasePage basePage;

    //////////// LOCATORS ////////////
    private final By continueButton = By.cssSelector("button.sc-papXJ");
    private final By quoteIntroBlock = By.xpath("//div[contains(text(),'קיבלת הנחה בלעדית לרכישה און ליין')]");
    private final By travelInsuranceButton = By.id("abroadplus");

    //////////// CONSTRUCTOR ////////////
    public T01_TravelInsuranceIntroPage(BasePage basePage) {
        this.basePage = basePage;
    }

    //////////// ACTIONS ////////////
    public void scrollToTravelInsuranceButton() {
        basePage.scrollToElementForce(travelInsuranceButton);
    }

    public void clickOnOnlineTravelInsuranceButton() {
        basePage.forceClick(travelInsuranceButton);
    }

    public void clickOnContinueButton() {
        basePage.forceClick(continueButton);
    }

    //////////// VALIDATIONS ////////////
    public boolean isQuoteIntroSectionVisible() {
        return basePage.isDisplayed(quoteIntroBlock);
    }
}
