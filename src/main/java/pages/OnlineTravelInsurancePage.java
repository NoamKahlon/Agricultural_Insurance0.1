package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class OnlineTravelInsurancePage {

    private final WebDriver driver;
    private final BasePage basePage;

    public OnlineTravelInsurancePage(WebDriver driver, BasePage basePage) {
        this.driver = driver;
        this.basePage = basePage;
    }

    //////////// NAVIGATION & STEPS ////////////
    private final By travelInsuranceButton = By.id("abroadplus");
    private final By letsContinueButton = By.cssSelector("button.procceed");
    private final By previousStepButton = By.cssSelector(".sc-papXJ.iGkwwW.goback");
    private final By nextStepButton = By.cssSelector(".sc-papXJ.jyxUhb.procceed");

    //////////// TEXT BLOCKS / QUESTIONS ////////////
    private final By quoteIntroBlock = By.xpath("//div[contains(text(),'קיבלת הנחה בלעדית לרכישה און ליין')]");
    private final By previousInsuranceQuestion = By.xpath("//h1[contains(text(),'רכשת ביטוח')]");
    private final By passengersFromIsraelQuestion = By.xpath("//h1[contains(.,'האם כל הנוסעים יוצאים מישראל')]");
    private final By purchaseBlockedTitle = By.xpath("//h1[contains(text(),'לצערנו לא נוכל להמשיך')]");
    private final By happyToSeeYouMessage = By.xpath("//*[contains(text(),'שמחים לראותך שוב!')]");
    private final By whereAreYouGoingTitle = By.cssSelector("h1.title");

    //////////// FORM FIELDS ////////////
    private final By phoneField = By.id("phone");
    private final By idField = By.id("id");
    private final By startDateField = By.id("startDate");
    private final By endDateField = By.id("endDate");

    //////////// OPTIONS & ANSWERS ////////////
    private final By firstTimeOptionButton = By.cssSelector("div[data-f='first-time-no']");
    private final By alreadyPurchasedOptionButton = By.cssSelector("div[data-f='already-purchased']");
    private final By yesOption = By.cssSelector("div[data-f='yes']");
    private final By noOption = By.cssSelector("div[data-f='no']");
    private final By passengersFromIsraelYes = By.cssSelector("div[data-f='yes']");
    private final By passengersFromIsraelNo = By.cssSelector("div[data-f='no']");
    private final By europeCheckBox = By.id("Europe");

    //////////// UI ELEMENTS ////////////
    private final By destinationTextElements = By.xpath("//*[contains(@class,'textForPickBoxLabelMobile')]");
    private final By tooltipIcon = By.cssSelector("svg.toolTipPic");
    private final By backToHomeButton = By.xpath("//button[contains(.,'בחזרה לעמוד הבית')]");

    // ==============================================
    //                   ACTIONS
    // ==============================================

    //////////// CLICK ////////////

    public void clickOnOnlineTravelInsuranceButton() {
        basePage.click(travelInsuranceButton);
    }

    public void clickOnLetsContinueButton() {
        basePage.forceClick(letsContinueButton);
    }

    public void clickFirstTimeOptionButton() {
        basePage.click(firstTimeOptionButton);
    }

    public void clickAlreadyPurchasedOptionButton() {
        basePage.click(alreadyPurchasedOptionButton);
    }

    public void clickPassengersFromIsraelYesOption() {
        basePage.click(yesOption);
    }

    public void clickPassengersFromIsraelNoOption() {
        basePage.click(noOption);
    }

    public void clickContinueToNextStepButton() {
        basePage.forceClick(nextStepButton);
    }

    public void clickEuropeCheckBox() {
        basePage.clickCheckbox(europeCheckBox);
    }

    //////////// SCROLL ////////////

    public void scrollToTravelInsuranceButton() throws Exception {
        basePage.scrollToElementForce(travelInsuranceButton);
    }

    public void scrollToLetsContinueButton() throws Exception {
        basePage.scrollToElementSafe(letsContinueButton);
    }

    // ==============================================
    //                 GET TEXT / ELEMENTS
    // ==============================================

    public String getPreviousInsuranceQuestionText() {
        return basePage.getText(previousInsuranceQuestion);
    }

    public String getPassengersFromIsraelQuestionText() {
        return basePage.getText(passengersFromIsraelQuestion);
    }

    public String getHappyToSeeYouAgainText() {
        return basePage.getText(happyToSeeYouMessage);
    }

    public List<WebElement> getDestinationTextElements() {
        return basePage.getElements(destinationTextElements);
    }

    // ==============================================
    //                  VALIDATIONS
    // ==============================================

    public boolean isQuoteIntroSectionVisible() {
        return basePage.isDisplayed(quoteIntroBlock);
    }

    public boolean isFirstTimeOptionDisplayed() {
        return basePage.isDisplayed(firstTimeOptionButton);
    }

    public boolean isAlreadyPurchasedOptionDisplayed() {
        return basePage.isDisplayed(alreadyPurchasedOptionButton);
    }

    public boolean isPassengersFromIsraelYesDisplayed() {
        return basePage.isDisplayed(passengersFromIsraelYes);
    }

    public boolean isPassengersFromIsraelNoDisplayed() {
        return basePage.isDisplayed(passengersFromIsraelNo);
    }

    public boolean isStopProcessErrorDisplayed() {
        return basePage.isDisplayed(purchaseBlockedTitle);
    }

    public boolean isBackToHomeButtonDisplayed() {
        return basePage.isDisplayed(backToHomeButton);
    }

    public boolean isPhoneFieldDisplayed() {
        return basePage.isDisplayed(phoneField);
    }

    public boolean isIdFieldDisplayed() {
        return basePage.isDisplayed(idField);
    }

    public boolean isWhereAreYouGoingTitleDisplayed() {
        return basePage.isDisplayed(whereAreYouGoingTitle);
    }

    public boolean isTooltipIconVisible() {
        return basePage.isDisplayed(tooltipIcon);
    }

    public boolean isBackButtonVisible() {
        return basePage.isDisplayed(previousStepButton);
    }

    public boolean isContinueButtonVisible() {
        return basePage.isDisplayed(nextStepButton);
    }

    public boolean verifyStartDateFieldDisplayed() {
        return basePage.isDisplayed(startDateField);
    }

    public boolean verifyEndDateFieldDisplayed() {
        return basePage.isDisplayed(endDateField);
    }

    // ==============================================
    //               LOGIC HELPERS / COLORS
    // ==============================================

    public String verifyCurrentAndPreviousStepsByColor() {
        List<WebElement> steps = basePage.getElements(By.cssSelector("div.step"));
        boolean currentStepFound = false;

        StringBuilder stepsSummary = new StringBuilder();

        for (WebElement step : steps) {
            String text = step.getText().trim();
            String classValue = step.getAttribute("class").trim();
            String backgroundColor = step.getCssValue("background-color").replace(" ", "");

            if (classValue.contains("current")) {
                currentStepFound = true;

                if (!backgroundColor.contains("31,47,102")) {
                    throw new AssertionError("Current step '" + text + "' is not blue!");
                }

                stepsSummary.append("Current step: ").append(text).append(" is in color blue , ");
                break;
            } else {
                if (!backgroundColor.contains("96,187,112")) {
                    throw new AssertionError("Completed step '" + text + "' is not green!");
                }

                stepsSummary.append("Completed step: ").append(text).append(" is in color green\n");
            }
        }

        if (!currentStepFound) {
            throw new AssertionError("No step is marked as current!");
        }

        return stepsSummary.toString();
    }
}
