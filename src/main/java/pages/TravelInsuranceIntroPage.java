package pages;

import org.openqa.selenium.By;
import pages.BasePage;

public class TravelInsuranceIntroPage {

    private final BasePage basePage;

    public TravelInsuranceIntroPage(BasePage basePage) {
        this.basePage = basePage;
    }

    //////////// LOCATORS ////////////
    private final By letsContinueButton = By.cssSelector("button.procceed");
    private final By quoteIntroBlock = By.xpath("//div[contains(text(),'קיבלת הנחה בלעדית לרכישה און ליין')]");

    //////////// ACTIONS ////////////

    public void clickContinue() {
        basePage.forceClick(letsContinueButton);
    }

    public void scrollToContinue() throws Exception {
        basePage.scrollToElementSafe(letsContinueButton);
    }

    //////////// VALIDATIONS ////////////

    public boolean isQuoteIntroVisible() {
        return basePage.isDisplayed(quoteIntroBlock);
    }

    public boolean isContinueButtonVisible() {
        return basePage.isDisplayed(letsContinueButton);
    }
}
