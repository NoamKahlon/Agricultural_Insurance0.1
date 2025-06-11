package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class T06_TravelInsuranceDestinationSelectionPage {

    private final BasePage basePage;

    public T06_TravelInsuranceDestinationSelectionPage(BasePage basePage) {
        this.basePage = basePage;
    }

    //////////// LOCATORS ////////////
    private final By title = By.cssSelector("h1.title");
    private final By tooltipIcon = By.cssSelector("svg.toolTipPic");
    private final By continueButton = By.cssSelector(".sc-papXJ.jyxUhb.procceed");
    private final By backButton = By.cssSelector(".sc-papXJ.iGkwwW.goback");
    private final By backToHomeButton = By.xpath("//button[contains(.,'בחזרה לעמוד הבית')]");
    private final By europeCheckBox = By.xpath("//input[@id='Europe']/parent::label");
    private final By destinationOptions = By.cssSelector("span.textForPickBoxLabelMobile");

    //////////// ACTIONS ////////////

    public void clickEuropeCheckBox() {
        basePage.clickCheckbox(europeCheckBox);
    }

    //////////// VALIDATIONS ////////////

    public boolean isWhereAreYouGoingTitleDisplayed() {
        return basePage.isDisplayed(title);
    }

    public boolean isContinueButtonVisible() {
        return basePage.isDisplayed(continueButton);
    }

    public boolean isTooltipIconVisible() {
        return basePage.isDisplayed(tooltipIcon);
    }

    public boolean isBackButtonVisible() {
        return basePage.isDisplayed(backButton);
    }

    public String verifyCurrentAndPreviousStepsByColor() {
        return basePage.getStepBarColorValidationResult();
    }

    //////////// GETTERS ////////////

    public List<WebElement> getDestinationOptions() {
        return basePage.getElements(destinationOptions);
    }
}
