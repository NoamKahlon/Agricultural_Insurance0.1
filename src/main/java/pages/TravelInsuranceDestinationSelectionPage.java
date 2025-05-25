package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.BasePage;

import java.util.List;

public class TravelInsuranceDestinationSelectionPage {

    private final BasePage basePage;

    public TravelInsuranceDestinationSelectionPage(BasePage basePage) {
        this.basePage = basePage;
    }

    //////////// LOCATORS ////////////
    private final By title = By.cssSelector("h1.title");
    private final By destinationOptions = By.xpath("//*[contains(@class,'textForPickBoxLabelMobile')]");
    private final By tooltipIcon = By.cssSelector("svg.toolTipPic");
    private final By backButton = By.cssSelector(".sc-papXJ.iGkwwW.goback");
    private final By continueButton = By.cssSelector(".sc-papXJ.jyxUhb.procceed");

    //////////// ACTIONS ////////////

    public void clickContinue() {
        basePage.forceClick(continueButton);
    }

    //////////// GETTERS ////////////

    public List<WebElement> getDestinationOptions() {
        return basePage.getElements(destinationOptions);
    }

    public String getTitleText() {
        return basePage.getText(title);
    }

    //////////// VALIDATIONS ////////////

    public boolean isTitleVisible() {
        return basePage.isDisplayed(title);
    }

    public boolean isTooltipVisible() {
        return basePage.isDisplayed(tooltipIcon);
    }

    public boolean isBackButtonVisible() {
        return basePage.isDisplayed(backButton);
    }

    public boolean isContinueButtonVisible() {
        return basePage.isDisplayed(continueButton);
    }
}
