package pages;

import org.openqa.selenium.By;
import pages.BasePage;

public class TravelInsuranceDateSelectionPage {

    private final BasePage basePage;

    public TravelInsuranceDateSelectionPage(BasePage basePage) {
        this.basePage = basePage;
    }

    //////////// LOCATORS ////////////
    private final By europeCheckbox = By.id("Europe");
    private final By startDateField = By.id("startDate");
    private final By endDateField = By.id("endDate");
    private final By continueButton = By.cssSelector(".sc-papXJ.jyxUhb.procceed");

    //////////// ACTIONS ////////////

    public void selectEuropeDestination() {
        basePage.clickCheckbox(europeCheckbox);
    }

    public void clickContinue() {
        basePage.forceClick(continueButton);
    }

    //////////// VALIDATIONS ////////////

    public boolean isStartDateFieldVisible() {
        return basePage.isDisplayed(startDateField);
    }

    public boolean isEndDateFieldVisible() {
        return basePage.isDisplayed(endDateField);
    }

    public boolean isContinueButtonVisible() {
        return basePage.isDisplayed(continueButton);
    }
}
