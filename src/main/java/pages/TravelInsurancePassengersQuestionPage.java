package pages;

import org.openqa.selenium.By;
import pages.BasePage;

public class TravelInsurancePassengersQuestionPage {

    private final BasePage basePage;

    public TravelInsurancePassengersQuestionPage(BasePage basePage) {
        this.basePage = basePage;
    }

    //////////// LOCATORS ////////////
    private final By questionHeader = By.xpath("//h1[contains(.,'האם כל הנוסעים יוצאים מישראל')]");
    private final By yesOption = By.cssSelector("div[data-f='yes']");
    private final By noOption = By.cssSelector("div[data-f='no']");
    private final By errorMessage = By.xpath("//h1[contains(text(),'לצערנו לא נוכל להמשיך')]");
    private final By backToHomeButton = By.xpath("//button[contains(.,'בחזרה לעמוד הבית')]");

    //////////// ACTIONS ////////////

    public void clickYesOption() {
        basePage.click(yesOption);
    }

    public void clickNoOption() {
        basePage.click(noOption);
    }

    //////////// GETTERS ////////////

    public String getQuestionText() {
        return basePage.getText(questionHeader);
    }

    //////////// VALIDATIONS ////////////

    public boolean isErrorMessageVisible() {
        return basePage.isDisplayed(errorMessage);
    }

    public boolean isBackToHomeButtonVisible() {
        return basePage.isDisplayed(backToHomeButton);
    }
}
