//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//
//public class OnlineTravelInsurancePage {
//
//    private WebDriver driver;
//    private BasePage basePage;
//
//    private By TravelInsuranceButton = By.id("abroadplus");
//   // private By continueButton = By.cssSelector("button.procceed");
//    By continueButton = By.xpath("//button[div[text()='להמשיך']]");
//    private By letsContinueButton = By.cssSelector("div[data-f='procceed']");
//    private By travelInsuranceButton = By.xpath("//h3[contains(text(), 'חו\"ל')]/ancestor::div[contains(@class,'insurances__item')]//div[contains(@class, 'insurances__item-footer')]");
//    private By firstTimeOptionButton = By.cssSelector("div[data-f='first-time-no']");
//    private By alreadyPurchasedOptionButton = By.cssSelector("div[data-f='already-purchased']");
//    private final By previousInsuranceQuestion = By.xpath("//h1[contains(text(),'רכשת ביטוח')]");
//    private final By passengersFromIsraelQuestion = By.xpath("//h1[contains(text(),'האם כל הנוסעים יוצאים מישראל')]");
//    private final By passengersFromIsraelYesOption = By.cssSelector("div[data-f='all-passengers-from-israel-yes']");
//    private final By passengersFromIsraelNoOption = By.cssSelector("div[data-f='all-passengers-from-israel-no']");
//
//
//    private String OnlineTravelInsuranceTitle = "ביטוח חקלאי - ביטוח נסיעות לחו״ל";
//    private String previousquestionInsuranceHeader = "האם רכשת ביטוח בעבר?";
//
//
//
//    public OnlineTravelInsurancePage(WebDriver driver, BasePage basePage) {
//        this.driver = driver;
//        this.basePage = basePage;
//    }
//
//
//    public void clickOnContinueButton() {
//        WebElement button = basePage.getElement(By.xpath("//button[div[text()='להמשיך']]"));
//        System.out.println("Location: " + button.getLocation());
//        System.out.println("Size: " + button.getSize());
//
//        basePage.scrollToElementSafe(continueButton); // לוודא שהוא בטווח גלילה
//        basePage.forceClick(continueButton);
//    }
//
//
//    public boolean isLetsContinueButtonDisplayed() throws Exception {
//        return basePage.isDisplayed(letsContinueButton);
//    }
//
//
//    public void scrollToTravelInsuranceButton() throws Exception {
//        basePage.scrollToElementForce(TravelInsuranceButton); // או לפי טקסט כמו קודם
//    }
//
//    public void clickOnOnlineTravelInsuranceButton() {
//        basePage.click(TravelInsuranceButton);
//    }
//
//
//    public void scrollToLetsContinueButton() throws Exception {
//       // basePage.scrollToElement(letsContinueButton);
//        basePage.scrollToElementForce(letsContinueButton); // או לפי טקסט כמו קודם
//    }
//
//    public void clickOnLetsContinueButton() {
//        By letsContinueButton = By.cssSelector("button.procceed");
//        //basePage.click(letsContinueButton);
//        basePage.forceClick(letsContinueButton);
//    }
//
//    public String getPreviousInsuranceQuestionText() {
//        return basePage.getText(previousInsuranceQuestion);
//    }
//
//    public boolean arePreviousInsuranceOptionsDisplayed() {
//        boolean isFirstTimeVisible = basePage.isDisplayedSafe(firstTimeOptionButton);
//        boolean isAlreadyPurchasedVisible = basePage.isDisplayedSafe(alreadyPurchasedOptionButton);
//        return isFirstTimeVisible && isAlreadyPurchasedVisible;
//    }
//
//
//    public String getPassengersFromIsraelQuestionText() {
//        return basePage.getText(passengersFromIsraelQuestion);
//    }
//
//    public boolean arePassengersFromIsraelOptionsDisplayed() {
//        boolean yesVisible = basePage.isDisplayedSafe(passengersFromIsraelYesOption);
//        boolean noVisible = basePage.isDisplayedSafe(passengersFromIsraelNoOption);
//        return yesVisible && noVisible;
//    }
//
//
//}
//
// OnlineTravelInsurancePage.java
package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.NoSuchElementException;

public class OnlineTravelInsurancePage {

    private WebDriver driver;
    private BasePage basePage;

    public OnlineTravelInsurancePage(WebDriver driver, BasePage basePage) {
        this.driver = driver;
        this.basePage = basePage;
    }


    private final By travelInsuranceButton = By.id("abroadplus");
    private final By letsContinueButton = By.cssSelector("button.procceed");
    private final By quoteIntroBlock = By.xpath("//div[contains(text(),'קיבלת הנחה בלעדית לרכישה און ליין')]");
    private final By previousInsuranceQuestion = By.xpath("//h1[contains(text(),'רכשת ביטוח')]");
    private final By passengersFromIsraelQuestion = By.xpath("//h1[contains(.,'האם כל הנוסעים יוצאים מישראל')]");
    private final By passengersFromIsraelYes = By.cssSelector("div[data-f='yes']");
    private final By passengersFromIsraelNo = By.cssSelector("div[data-f='no']");
    private final By firstTimeOptionButton = By.cssSelector("div[data-f='first-time-no']");
    private final By alreadyPurchasedOptionButton = By.cssSelector("div[data-f='already-purchased']");
    private final By yesOption = By.xpath("//div[@data-f='yes']");
    private final By noOption = By.xpath("//div[@data-f='no']");
    private final By purchaseBlockedTitle = By.xpath("//h1[contains(text(),'לצערנו לא נוכל להמשיך')]");
    private final By backToHomeButton = By.xpath("//button[contains(.,'בחזרה לעמוד הבית')]");
    private final By phoneField = By.id("phone");
    private final By idField = By.id("id");
    private final By happyToSeeYouMesseage = By.xpath("//*[contains(text(),'שמחים לראותך שוב!')]");
    private final By whereAreYouGoingTitle = By.cssSelector("h1.title");
    //private final By DestinationTextElements = By.className("textForPickBoxLabelMobile");
    private final By DestinationTextElements = By.xpath("//*[contains(@class,'textForPickBoxLabelMobile')]");
    private final By TooltipIcon = By.cssSelector("svg.toolTipPic");
    //private final By backButton = By.cssSelector("div[data-f='back']");
//    private final By previousStepButton = By.className("sc-papXJ iGkwwW goback");
//    private final By NextStepButton = By.cssSelector("sc-papXJ jyxUhb procceed");
    private final By previousStepButton = By.cssSelector(".sc-papXJ.iGkwwW.goback");
    private final By NextStepButton = By.cssSelector(".sc-papXJ.jyxUhb.procceed");

   // private final By continueToNextStepButton = By.cssSelector("div[data-f='continue']");
    private final By europeCheckBox = By.id("Europe");
    public By startDateField = By.id("startDate");
    public By endDateField = By.id("endDate");


    public void scrollToTravelInsuranceButton() throws Exception {
        basePage.scrollToElementForce(travelInsuranceButton);
    }

    public void clickOnOnlineTravelInsuranceButton() {
        basePage.click(travelInsuranceButton);
    }

    public void clickOnContinueButton() {
        basePage.forceClick(letsContinueButton);
    }

    public void scrollToLetsContinueButton() throws Exception {
        basePage.scrollToElementSafe(letsContinueButton);
    }

    public void clickOnLetsContinueButton() {
        basePage.forceClick(letsContinueButton);
    }

    public boolean isQuoteIntroSectionVisible() {
        return basePage.isDisplayedSafe(quoteIntroBlock);
    }

    public String getPreviousInsuranceQuestionText() {
        return basePage.getTextSafe(previousInsuranceQuestion);
    }


    public boolean isFirstTimeOptionDisplayed() {
        return basePage.isDisplayedSafe(firstTimeOptionButton);
    }

    public boolean isAlreadyPurchasedOptionDisplayed() {
        return basePage.isDisplayedSafe(alreadyPurchasedOptionButton);
    }

    public String getPassengersFromIsraelQuestionText() {
        return basePage.getTextSafe(passengersFromIsraelQuestion);
    }

    public boolean isPassengersFromIsraelYesDisplayed() {
        return basePage.isDisplayedSafe(passengersFromIsraelYes);
    }

    public boolean isPassengersFromIsraelNoDisplayed() {
        return basePage.isDisplayedSafe(passengersFromIsraelNo);
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

    public boolean isStopProcessErrorDisplayed() {
        return basePage.isDisplayedSafe(purchaseBlockedTitle);
    }

    public boolean isBackToHomeButtonDisplayed() {
        return basePage.isDisplayedSafe(backToHomeButton);
    }

    public String getHappyToSeeYouAgainText() {
        return basePage.getTextSafe(happyToSeeYouMesseage);
    }

    public boolean isPhoneFieldDisplayed() {
        return basePage.isDisplayedSafe(phoneField);
    }

    public boolean isIdFieldDisplayed() {
        return basePage.isDisplayedSafe(idField);
    }

    public boolean iswhereAreYouGoingTitleDisplayed() {
        return basePage.isDisplayedSafe(whereAreYouGoingTitle);
    }

    public List<WebElement> getDestinationTextElements() {
        //basePage.waitForNumberOfElements(DestinationTextElements, 6); // ⬅ מחכה ל-6
        return basePage.getElements(DestinationTextElements);
    }


    public boolean isTooltipIconVisible() {
        return basePage.isDisplayedSafe(TooltipIcon);
    }


    public boolean isBackButtonVisible() {
        return basePage.isDisplayedSafe(previousStepButton);
    }


    public boolean isContinueButtonVisible() {
        return basePage.isDisplayedSafe(NextStepButton);
    }



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

                stepsSummary
                        .append("Current step: ").append(text)
                        .append(" is in color blue , ");
                break;

            } else {
                if (!backgroundColor.contains("96,187,112")) {
                    throw new AssertionError("Completed step '" + text + "' is not green!");
                }

                stepsSummary
                        .append("Completed step: ").append(text)
                        .append(" is in color green\n");
            }
        }

        if (!currentStepFound) {
            throw new AssertionError("No step is marked as current!");
        }

        return stepsSummary.toString();
    }




    public void clickcontinueToNextStepButton() {
        basePage.forceClick(NextStepButton);
    }


    public void clickEuropeCheckBox() {
        basePage.clickCheckbox(europeCheckBox);
    }



    public boolean verifyStartDateFieldDisplayed(){
        return basePage.isDisplayed(startDateField);
    }

    public boolean verifyEndDateFieldDisplayed(){
        return basePage.isDisplayed(endDateField);
    }

}