package pages;

import org.openqa.selenium.By;

public class TravelInsuranceStartPage {
    private final BasePage basePage;

    private final By travelInsuranceButton = By.id("abroadplus");
    private final By letsContinueButton = By.cssSelector("button.procceed");

    public TravelInsuranceStartPage(BasePage basePage) {
        this.basePage = basePage;
    }

    public void clickTravelInsuranceButton() {
       // basePage.click(travelInsuranceButton);
        basePage.forceClick(travelInsuranceButton);
    }

    public void clickLetsContinueButton() {
        basePage.forceClick(letsContinueButton);
    }

    public void scrollToTravelInsuranceButton() throws Exception {
        basePage.scrollToElementForce(travelInsuranceButton);
    }

    public void scrollToLetsContinueButton() throws Exception {
        basePage.scrollToElementSafe(letsContinueButton);
    }
}