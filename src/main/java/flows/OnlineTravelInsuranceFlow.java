package flows;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.OnlineTravelInsurancePage;
import utils.LoggerUtils;
import utils.OnlineTravelInsuranceData;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OnlineTravelInsuranceFlow {

    WebDriver driver;
    BasePage basePage;
    OnlineTravelInsurancePage onlineTravelInsurancePage;
    LoggerUtils loggerUtils;


    public OnlineTravelInsuranceFlow(WebDriver driver, BasePage basePage, OnlineTravelInsurancePage onlineTravelInsurancePage, LoggerUtils loggerUtils) {
        this.driver = driver;
        this.basePage = basePage;
        this.onlineTravelInsurancePage = onlineTravelInsurancePage;
        this.loggerUtils = loggerUtils;
    }


    public void initTravelFlow() throws Exception {

        onlineTravelInsurancePage.scrollToTravelInsuranceButton();
        loggerUtils.log("✅ Scrolled to Travel Insurance section", "ScrollToTravel", true, false);

        onlineTravelInsurancePage.clickOnOnlineTravelInsuranceButton();
        loggerUtils.log("✅ Clicked on travel insurance button", "ClickTravelInsurance", true, false);

        basePage.switchToNewTab();

        basePage.waitForElementToAppear(By.className("imageTitle"));


        String expectedTitle = OnlineTravelInsuranceData.EXPECTED_TRAVEL_INSURANCE_PAGE_TITLE;
        String actualTitle = basePage.getTitle();

        boolean titleOk = actualTitle.equals(expectedTitle);
        loggerUtils.log(titleOk ? "✅ Title OK: " + actualTitle : "❌ Wrong title. Got: " + actualTitle, "PageTitle", titleOk, false);

        boolean introVisible = onlineTravelInsurancePage.isQuoteIntroSectionVisible();
        loggerUtils.log(introVisible ? "✅ Quote intro section is visible" : "❌ Intro section is not visible", "IntroVisible", introVisible, false);
    }


    public void continueToNextQuestion() throws Exception {
        onlineTravelInsurancePage.scrollToLetsContinueButton();
        onlineTravelInsurancePage.clickOnLetsContinueButton();
        Thread.sleep(200);
    }


    public void clickContinueButtonTwice() throws Exception {
        continueToNextQuestion();
        loggerUtils.log("✅ Clicked on first 'Continue'", "ClickContinueBtn1", true, true);

        continueToNextQuestion();
        loggerUtils.log("✅ Clicked on second 'Continue'", "ClickContinueBtn2", true, true);
    }



    public void VerifyInsurancePurchasedQuestion() throws Exception {

        String question = onlineTravelInsurancePage.getPreviousInsuranceQuestionText();
        boolean questionOk = question.contains(utils.OnlineTravelInsuranceData.DID_YOU_PURCHASE_INSURANCE_TEXT);
        loggerUtils.log(questionOk ? "✅ Found question: " + question : "❌ Wrong question text: " + question, "", questionOk, false);


        boolean firstDisplayed = onlineTravelInsurancePage.isFirstTimeOptionDisplayed();
        loggerUtils.log(firstDisplayed ? "✅ 'First time' option visible" : "❌ 'First time' option not visible",
                "", firstDisplayed, false);

        boolean alreadyDisplayed = onlineTravelInsurancePage.isAlreadyPurchasedOptionDisplayed();
        loggerUtils.log(alreadyDisplayed ? "✅ 'Already purchased' option visible" : "❌ 'Already purchased' option not visible",
                "", alreadyDisplayed, false);
    }


    public void verifyPassengersFromIsraelQuestionStep() throws Exception {

        String question = onlineTravelInsurancePage.getPassengersFromIsraelQuestionText();
        boolean questionOk = question.contains("יוצאים מישראל");
        loggerUtils.log(questionOk ? "✅ Found question: " + question : "❌ Wrong question text: " + question, "PassengersQuestion", questionOk, false);

        boolean yesOptionDisplayed = onlineTravelInsurancePage.isPassengersFromIsraelYesDisplayed();
        loggerUtils.log(yesOptionDisplayed ? "✅ 'Yes' option is visible" : "❌ 'Yes' option is NOT visible",
                "", yesOptionDisplayed, false);

        boolean noOptionDisplayed = onlineTravelInsurancePage.isPassengersFromIsraelNoDisplayed();
        loggerUtils.log(noOptionDisplayed ? "✅ 'No' option is visible" : "❌ 'No' option is NOT visible",
                "", noOptionDisplayed, false);

        boolean optionsOk = yesOptionDisplayed && noOptionDisplayed;
    }


    public void navigateToPurchasedInsuranceQuestion() throws Exception {
        initTravelFlow();
        clickContinueButtonTwice();
        VerifyInsurancePurchasedQuestion();
    }



    public void navigateToDestinationSelectionStep() throws Exception {
        initTravelFlow();
        clickContinueButtonTwice();
        VerifyInsurancePurchasedQuestion();
        onlineTravelInsurancePage.clickFirstTimeOptionButton();
        verifyPassengersFromIsraelQuestionStep();
    }


    public void verifyDestinationOptionsTexts() {

        List<String> expectedDestinations = OnlineTravelInsuranceData.EXPECTED_DESTINATIONS;
        List<String> actualDestinations = onlineTravelInsurancePage.getDestinationTextElements()
                .stream()
                .map(e -> cleanText(e.getText()))
                .collect(Collectors.toList());

        Set<String> expectedSet = new HashSet<>(expectedDestinations);
        Set<String> actualSet = new HashSet<>(actualDestinations);

        boolean isMatch = expectedSet.equals(actualSet);

        if (isMatch) {
            loggerUtils.log("✅ All destination names match (any order)", "DestinationCheck", true, false);
        } else {
            Set<String> missing = new HashSet<>(expectedSet);
            missing.removeAll(actualSet);

            Set<String> unexpected = new HashSet<>(actualSet);
            unexpected.removeAll(expectedSet);

            loggerUtils.log("❌ Destination mismatch! Missing: " + missing + ", Unexpected: " + unexpected,
                    "DestinationsMismatch", false, true);
        }
    }

    String cleanText(String text) {
        return text
                .replaceAll("\\p{C}", "")
                .replaceAll("\\s+", " ")
                .trim();
    }



    public void verifyDestinationSelectionStep() {

        onlineTravelInsurancePage.clickPassengersFromIsraelYesOption();
        loggerUtils.log("✅ Clicked on 'Yes' all passengers from Israel", "ClickedYesPassengers", true, true);

        boolean titleDisplayed = onlineTravelInsurancePage.isWhereAreYouGoingTitleDisplayed();
        loggerUtils.log(titleDisplayed ? "✅ 'Where are you going?' title is displayed" : "❌ 'Where are you going?' title is NOT displayed", "", titleDisplayed, false);

        verifyDestinationOptionsTexts();

        boolean tooltipVisible = onlineTravelInsurancePage.isTooltipIconVisible();
        loggerUtils.log(tooltipVisible ? "✅ Tooltip icon is visible" : "❌ Tooltip icon is missing", "", tooltipVisible, false);

        boolean backButtonVisible = onlineTravelInsurancePage.isBackButtonVisible();
        loggerUtils.log(backButtonVisible ? "✅ 'Back' button is visible" : "❌ 'Back' button is missing", "", backButtonVisible, false);

        boolean continueButtonVisible = onlineTravelInsurancePage.isContinueButtonVisible();
        loggerUtils.log(continueButtonVisible ? "✅ 'Continue' button is visible" : "❌ 'Continue' button is missing", "", continueButtonVisible, false);

    }



}
