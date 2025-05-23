//import io.qameta.allure.Description;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import java.util.List;
//import java.util.Set;
//
//import static org.testng.Assert.assertTrue;
//
//public class OnlineTravelInsuranceTests extends Base {
//
//
//    @BeforeMethod
//    public void initTravelFlow() throws Exception {
//
//        onlineTravelInsurancePage.scrollToTravelInsuranceButton(); // make it soft
//        loggerUtils.log("✅ Scrolled to Travel Insurance section", "ScrollToTravel", true, false);
//
//        // צריך לתקן את הסקרייןשוט לא מראה את הדף הנכון
//        onlineTravelInsurancePage.clickOnOnlineTravelInsuranceButton();
//
//        loggerUtils.log("✅ Clicked on 'onlineTravelInsuranceButton' button", "Click Online Travel Insurance", true, true);
//
//        basePage.switchToNewTab();
//
//        String expectedTitle = "ביטוח חקלאי - ביטוח נסיעות לחו״ל";
//        Thread.sleep(300);
//        String actualTitle = basePage.getTitle();
//
//        boolean isTitleRight = actualTitle.equals(expectedTitle);
//        loggerUtils.log(isTitleRight ? "✅ Title matches expected title: " + actualTitle: "❌ Title mismatch! Expected: '" + expectedTitle + "', Got: '" + actualTitle + "'", "", isTitleRight, false);
//
//        onlineTravelInsurancePage.clickOnContinueButton();
//        loggerUtils.log("✅ Clicked on 'Continue Button'", "ClickContinue", true, true);
//
//
//        actualTitle = basePage.getTitle();
//        isTitleRight = actualTitle.equals(expectedTitle);
//        loggerUtils.log(isTitleRight ? "✅ Title matches expected title: "+ actualTitle: "❌ Title mismatch! Expected: '" + expectedTitle + "', Got: '" + actualTitle + "'", "", isTitleRight, false);
//
//
//        boolean isLetsContinueButtonDisplayed = onlineTravelInsurancePage.isLetsContinueButtonDisplayed();
//        loggerUtils.log(isLetsContinueButtonDisplayed ? "✅ Continue button is displayed" : "❌ Continue button is not displayed", "ContinueButton", isLetsContinueButtonDisplayed, false);
//
//
//    }
//
//
//    @Test(description = "TC-038 - Verify Online Travel Insurance Quote flow from homepage to section")
//    @Description("Opens homepage, scrolls to online travel insurance section, clicks abroadplus button, and verifies section loads")
//    public void test_OnlineTravelInsuranceQuoteFlow() throws Exception {
//        try {
//
////            onlineTravelInsurancePage.scrollToTravelInsuranceButton(); // make it soft
////            loggerUtils.log("✅ Scrolled to Travel Insurance section", "ScrollToTravel", true, false);
////
////
////            // צריך לתקן את הסקרייןשוט לא מראה את הדף הנכון
////            onlineTravelInsurancePage.clickOnOnlineTravelInsuranceButton();
////            Thread.sleep(500);
////            loggerUtils.log("✅ Clicked on 'onlineTravelInsuranceButton' button", "Click Online Travel Insurance", true, true);
////
////
////           // boolean switched =
////                    basePage.switchToNewTab();
////            //loggerUtils.log(switched ? "✅ Switched to new tab" : "❌ Failed to switch to new tab", "", switched, true);
//
//
////            String expectedTitle = "ביטוח חקלאי - ביטוח נסיעות לחו״ל";
////            String actualTitle = basePage.getTitle();
////
////            boolean isTitleRight = actualTitle.equals(expectedTitle);
////            loggerUtils.log(isTitleRight ? "✅ Title matches expected title: "+ actualTitle: "❌ Title mismatch! Expected: '" + expectedTitle + "', Got: '" + actualTitle + "'", "", isTitleRight, false);
//
////            onlineTravelInsurancePage.clickOnContinueButton();
////            loggerUtils.log("✅ Clicked on 'Continue Button'", "ClickContinue", true, true);
////
////
////            actualTitle = basePage.getTitle();
////            isTitleRight = actualTitle.equals(expectedTitle);
////            loggerUtils.log(isTitleRight ? "✅ Title matches expected title: "+ actualTitle: "❌ Title mismatch! Expected: '" + expectedTitle + "', Got: '" + actualTitle + "'", "", isTitleRight, false);
////
////
////            boolean isLetsContinueButtonDisplayed = onlineTravelInsurancePage.isLetsContinueButtonDisplayed();
////            loggerUtils.log(isLetsContinueButtonDisplayed ? "✅ Continue button is displayed" : "❌ Continue button is not displayed", "ContinueButton", isLetsContinueButtonDisplayed, false);
//
//
//
//        } catch (Exception e) {
//            loggerUtils.log("❌ Test 'test_OnlineTravelInsuranceQuoteFlow' failed: " + e.getMessage(), "TravelInsuranceFlow", false, true);
//            throw e;
//        }
//        finally {
//            loggerUtils.assertAllSoft();
//        }
//    }
//
//
//
//    public void continueToNextQuestion() throws Exception {
//        onlineTravelInsurancePage.scrollToLetsContinueButton();
//        onlineTravelInsurancePage.clickOnLetsContinueButton();
//        Thread.sleep(300);
//        loggerUtils.log("✅ Clicked on 'Let's Continue' button", "ClickLetsContinue", true, true);
//
//
//    }
//
//    public void verifyPassengersFromIsraelQuestion() {
//        String question = onlineTravelInsurancePage.getPassengersFromIsraelQuestionText();
//        boolean isMatch = question.contains("יוצאים מישראל");
//
//        loggerUtils.log(isMatch ? "✅ Question text found: '" + question + "'"
//                        : "❌ Question mismatch! Got: '" + question + "'",
//                "PassengersFromIsraelQuestion", isMatch, false);
//
//        boolean optionsDisplayed = onlineTravelInsurancePage.arePassengersFromIsraelOptionsDisplayed();
//        loggerUtils.log(optionsDisplayed ? "✅ Both 'Yes' and 'No' options are visible"
//                        : "❌ One or both options are missing",
//                "PassengersFromIsraelOptions", optionsDisplayed, false);
//    }
//
//
//
//    @Test(description = "TC-039 - Verify 'Was insurance purchased before?' step displays correctly")
//    @Description("Opens the travel insurance flow and verifies that the 'Previous insurance purchase' question appears with two options")
//    public void test_PreviousInsuranceStepOptions() throws Exception {
//        try {
//            continueToNextQuestion();
//            verifyPassengersFromIsraelQuestion();
//        } catch (Exception e) {
//            loggerUtils.log("❌ Test 'test_PreviousInsuranceStepOptions' failed: " + e.getMessage(), "PreviousInsuranceTest", false, true);
//            throw e;
//        } finally {
//            loggerUtils.assertAllSoft();
//        }
//    }
//
//
//
//
//
//    @Test(description = "TC-040 - Verify 'Are all passengers departing from Israel?' step appears with options")
//    @Description("Continues the travel insurance flow and verifies that the 'Passengers from Israel' question appears with two options")
//    public void test_PassengersFromIsraelStepAppears() throws Exception {
//        try {
//            // שלב קודם - "האם רכשת ביטוח בעבר?"
//            continueToNextQuestion();
//
//            // ממשיכים לשאלה על נוסעים
//            continueToNextQuestion();
//
//            // אימות שאלה
//            verifyPassengersFromIsraelQuestion();
//
//        } catch (Exception e) {
//            loggerUtils.log("❌ Test 'test_PassengersFromIsraelStepAppears' failed: " + e.getMessage(), "PassengersFromIsraelTest", false, true);
//            throw e;
//        } finally {
//            loggerUtils.assertAllSoft();
//        }
//    }
//
//
//
//
//
//}

// OnlineTravelInsuranceTests.java
package tests;
import flows.OnlineTravelInsuranceFlow;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.OnlineTravelInsuranceData;

public class OnlineTravelInsuranceTests extends Base {


//    public void initTravelFlow() throws Exception {
//        onlineTravelInsurancePage.scrollToTravelInsuranceButton();
//        loggerUtils.log("✅ Scrolled to Travel Insurance section", "ScrollToTravel", true, false);
//
//        onlineTravelInsurancePage.clickOnOnlineTravelInsuranceButton();
//        loggerUtils.log("✅ Clicked on travel insurance button", "ClickTravelInsurance", true, false);
//
//        basePage.switchToNewTab();
//
//        basePage.waitForElementToAppear(By.id("root"));
//
//        // put it on utilitys
//        String expectedTitle = "ביטוח חקלאי - ביטוח נסיעות לחו״ל";
//        String actualTitle = basePage.getTitle();
//
//        boolean titleOk = actualTitle.equals(expectedTitle);
//        loggerUtils.log(titleOk ? "✅ Title OK: " + actualTitle : "❌ Wrong title. Got: " + actualTitle, "PageTitle", titleOk, false);
//
//        boolean introVisible = onlineTravelInsurancePage.isQuoteIntroSectionVisible();
//        loggerUtils.log(introVisible ? "✅ Quote intro section is visible" : "❌ Intro section is not visible", "IntroVisible", introVisible, false);
    //    }

    OnlineTravelInsuranceFlow flow;

    @BeforeMethod
    public void setup(){
        flow = new OnlineTravelInsuranceFlow(driver,basePage,onlineTravelInsurancePage,loggerUtils);
    }

    @Test(description = "TC-038 - Verify landing on online travel insurance flow")
    @Description("Open insurance flow, verify title and main intro block")
    public void test_StartInsuranceFlow() throws Exception {
        try {
            flow.initTravelFlow();
        } catch (Exception e) {
            loggerUtils.log("❌ Test failed: " + e.getMessage(), "StartFlowFail", false, true);
            throw e;
        }
    }


    @Test(description = "TC-039 - Verify previous insurance question appears")
    @Description("Click continue and verify previous insurance question and buttons")
    public void test_PreviousInsuranceQuestion() throws Exception {
        try {
            flow.navigateToDestinationSelectionStep();
        } catch (Exception e) {
            loggerUtils.log("❌ Test failed: " + e.getMessage(), "PreviousQuestionFail", false, true);
            throw e;
        }
    }


    @Test(description = "TC-040 - Verify passengers from Israel question appears")
    @Description("Continue twice and verify question and both options")
    public void test_PassengersFromIsraelQuestion() throws Exception {
        try {
            flow.navigateToPurchasedInsuranceQuestion();

            onlineTravelInsurancePage.clickFirstTimeOptionButton();
            flow.verifyPassengersFromIsraelQuestionStep();
        } catch (Exception e) {
            loggerUtils.log("❌ Test failed: " + e.getMessage(), "PassengersQuestionFail", false, true);
            throw e;
        }
    }



    @Test(description = "TC-041 - UI - Verify quote suggestion flow with previous insurance selected")
    @Description("Navigate to online travel insurance, select 'previously purchased insurance' and verify quote options page appears")
    public void test_QuoteWithPreviousInsurance() throws Exception {
        try {
            flow.navigateToPurchasedInsuranceQuestion();

            // בוחרים שכבר רכש בעבר
            onlineTravelInsurancePage.clickAlreadyPurchasedOptionButton();
            loggerUtils.log("✅ Clicked on 'Already Purchased' option", "ClickedAlreadyPurchased", true, true);

            // בדיקת טקסט "שמחים לראותך שוב!"
            boolean welcomeTextDisplayed = !onlineTravelInsurancePage.getHappyToSeeYouAgainText().isEmpty();

            loggerUtils.log(welcomeTextDisplayed ? "✅ 'Welcome back' text is visible: '" : "❌ 'Welcome back' text not found",
                    "", welcomeTextDisplayed, false);

            // בדיקת שדה טלפון
            boolean emailFieldDisplayed = onlineTravelInsurancePage.isPhoneFieldDisplayed();
            loggerUtils.log(emailFieldDisplayed ? "✅ phone field is visible" : "❌ phone field not visible",
                    "", emailFieldDisplayed, false);

            // בדיקת שדה תעודת זהות
            boolean idFieldDisplayed = onlineTravelInsurancePage.isIdFieldDisplayed(); // שנה לפי locator האמיתי אם שונה
            loggerUtils.log(idFieldDisplayed ? "✅ ID field is visible" : "❌ ID field not visible",
                    "", idFieldDisplayed, false);

        } catch (Exception e) {
            loggerUtils.log("❌ Test failed: " + e.getMessage(), "TC041_Failure", false, true);
            throw e;
        }
    }




    @Test(description = "TC-042 - UI - Block flow when not all passengers depart from Israel")
    @Description("Verify that the process is blocked when user selects 'not all passengers are departing from Israel'")
    public void test_BlockFlowWhenPassengersNotFromIsrael() throws Exception {
        try {

            flow.navigateToDestinationSelectionStep();


            onlineTravelInsurancePage.clickPassengersFromIsraelNoOption();
            loggerUtils.log("✅ Clicked on 'No' for passengers from Israel", "ClickedNoPassengers", true, true);

            boolean errorDisplayed = onlineTravelInsurancePage.isStopProcessErrorDisplayed();
            loggerUtils.log(errorDisplayed ? "✅ Error message visible" : "❌ Error message missing", "", errorDisplayed, false);

            boolean backButtonDisplayed = onlineTravelInsurancePage.isBackToHomeButtonDisplayed();
            loggerUtils.log(backButtonDisplayed ? "✅ 'Back to homepage' button visible" : "❌ 'Back to homepage' button missing", "", backButtonDisplayed, false);

        } catch (Exception e) {
            loggerUtils.log("❌ Test failed: " + e.getMessage(), "TC042_Failure", false, true);
        }
    }

    @Test(description = "TC-043 - Verify online travel insurance quote flow is accessible and displayed correctly")
    @Description("Navigates to the online travel insurance quote page on Bituach Haklai site, verifies correct UI flow and elements for stage 1: destination selection")
    public void TC043() {
        try {


            flow.navigateToDestinationSelectionStep();

            onlineTravelInsurancePage.clickPassengersFromIsraelYesOption();
            loggerUtils.log("✅ Clicked on 'Yes' all passengers from Israel", "ClickedYesPassengers", true, true);


            boolean iswhereAreYouGoingTitleDisplayed= onlineTravelInsurancePage.iswhereAreYouGoingTitleDisplayed();

            loggerUtils.log(iswhereAreYouGoingTitleDisplayed ? "✅ 'Where are you going?' title is displayed" : "❌ 'Where are you going?' title is NOT displayed", "WhereAreYouGoingTitle", iswhereAreYouGoingTitleDisplayed, false);


            flow.verifyDestinationOptionsTexts();

            boolean tooltipVisible = onlineTravelInsurancePage.isTooltipIconVisible();
            loggerUtils.log(tooltipVisible ? "✅ Tooltip icon is visible" : "❌ Tooltip icon is missing", "", tooltipVisible, false);

            boolean backButtonVisible = onlineTravelInsurancePage.isBackButtonVisible();
            loggerUtils.log(backButtonVisible ? "✅ 'Back' button is visible" : "❌ 'Back' button is missing", "", backButtonVisible, false);

            boolean continueButtonVisible = onlineTravelInsurancePage.isContinueButtonVisible();
            loggerUtils.log(continueButtonVisible ? "✅ 'Continue' button is visible" : "❌ 'Continue' button is missing", "", continueButtonVisible, false);


            String currentStep = onlineTravelInsurancePage.getCurrentStepText();
            boolean isCorrectStep = currentStep.equals(OnlineTravelInsuranceData.STEP_DESTINATION);
            loggerUtils.log(
                    isCorrectStep ? "✅ Current step is 'לאן נוסעים?'" : "❌ Current step is '" + currentStep + "' instead of 'לאן נוסעים?'",
                    "StepVerification", // שם הסקרינשוט אם תרצה
                    isCorrectStep,
                    false // לא hard assert – רק לוג
            );


        } catch (Exception e) {
            loggerUtils.log("❌ Test failed: " + e.getMessage(), "TC042_Failure", false, true);
        }
    }

    

}