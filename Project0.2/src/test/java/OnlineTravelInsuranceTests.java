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
//        smartLog("✅ Scrolled to Travel Insurance section", "ScrollToTravel", true, LogMode.SOFT);
//
//        // צריך לתקן את הסקרייןשוט לא מראה את הדף הנכון
//        onlineTravelInsurancePage.clickOnOnlineTravelInsuranceButton();
//
//        smartLog("✅ Clicked on 'onlineTravelInsuranceButton' button", "Click Online Travel Insurance", true, LogMode.HARD);
//
//        basePage.switchToNewTab();
//
//        String expectedTitle = "ביטוח חקלאי - ביטוח נסיעות לחו״ל";
//        Thread.sleep(300);
//        String actualTitle = basePage.getTitle();
//
//        boolean isTitleRight = actualTitle.equals(expectedTitle);
//        smartLog(isTitleRight ? "✅ Title matches expected title: " + actualTitle: "❌ Title mismatch! Expected: '" + expectedTitle + "', Got: '" + actualTitle + "'", "", isTitleRight, LogMode.SOFT);
//
//        onlineTravelInsurancePage.clickOnContinueButton();
//        smartLog("✅ Clicked on 'Continue Button'", "ClickContinue", true, LogMode.HARD);
//
//
//        actualTitle = basePage.getTitle();
//        isTitleRight = actualTitle.equals(expectedTitle);
//        smartLog(isTitleRight ? "✅ Title matches expected title: "+ actualTitle: "❌ Title mismatch! Expected: '" + expectedTitle + "', Got: '" + actualTitle + "'", "", isTitleRight, LogMode.SOFT);
//
//
//        boolean isLetsContinueButtonDisplayed = onlineTravelInsurancePage.isLetsContinueButtonDisplayed();
//        smartLog(isLetsContinueButtonDisplayed ? "✅ Continue button is displayed" : "❌ Continue button is not displayed", "ContinueButton", isLetsContinueButtonDisplayed, LogMode.SOFT);
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
////            smartLog("✅ Scrolled to Travel Insurance section", "ScrollToTravel", true, LogMode.SOFT);
////
////
////            // צריך לתקן את הסקרייןשוט לא מראה את הדף הנכון
////            onlineTravelInsurancePage.clickOnOnlineTravelInsuranceButton();
////            Thread.sleep(500);
////            smartLog("✅ Clicked on 'onlineTravelInsuranceButton' button", "Click Online Travel Insurance", true, LogMode.HARD);
////
////
////           // boolean switched =
////                    basePage.switchToNewTab();
////            //smartLog(switched ? "✅ Switched to new tab" : "❌ Failed to switch to new tab", "", switched, LogMode.HARD);
//
//
////            String expectedTitle = "ביטוח חקלאי - ביטוח נסיעות לחו״ל";
////            String actualTitle = basePage.getTitle();
////
////            boolean isTitleRight = actualTitle.equals(expectedTitle);
////            smartLog(isTitleRight ? "✅ Title matches expected title: "+ actualTitle: "❌ Title mismatch! Expected: '" + expectedTitle + "', Got: '" + actualTitle + "'", "", isTitleRight, LogMode.SOFT);
//
////            onlineTravelInsurancePage.clickOnContinueButton();
////            smartLog("✅ Clicked on 'Continue Button'", "ClickContinue", true, LogMode.HARD);
////
////
////            actualTitle = basePage.getTitle();
////            isTitleRight = actualTitle.equals(expectedTitle);
////            smartLog(isTitleRight ? "✅ Title matches expected title: "+ actualTitle: "❌ Title mismatch! Expected: '" + expectedTitle + "', Got: '" + actualTitle + "'", "", isTitleRight, LogMode.SOFT);
////
////
////            boolean isLetsContinueButtonDisplayed = onlineTravelInsurancePage.isLetsContinueButtonDisplayed();
////            smartLog(isLetsContinueButtonDisplayed ? "✅ Continue button is displayed" : "❌ Continue button is not displayed", "ContinueButton", isLetsContinueButtonDisplayed, LogMode.SOFT);
//
//
//
//        } catch (Exception e) {
//            smartLog("❌ Test 'test_OnlineTravelInsuranceQuoteFlow' failed: " + e.getMessage(), "TravelInsuranceFlow", false, LogMode.HARD);
//            throw e;
//        }
//        finally {
//            assertAllSoft();
//        }
//    }
//
//
//
//    public void continueToNextQuestion() throws Exception {
//        onlineTravelInsurancePage.scrollToLetsContinueButton();
//        onlineTravelInsurancePage.clickOnLetsContinueButton();
//        Thread.sleep(300);
//        smartLog("✅ Clicked on 'Let's Continue' button", "ClickLetsContinue", true, LogMode.HARD);
//
//
//    }
//
//    public void verifyPassengersFromIsraelQuestion() {
//        String question = onlineTravelInsurancePage.getPassengersFromIsraelQuestionText();
//        boolean isMatch = question.contains("יוצאים מישראל");
//
//        smartLog(isMatch ? "✅ Question text found: '" + question + "'"
//                        : "❌ Question mismatch! Got: '" + question + "'",
//                "PassengersFromIsraelQuestion", isMatch, LogMode.SOFT);
//
//        boolean optionsDisplayed = onlineTravelInsurancePage.arePassengersFromIsraelOptionsDisplayed();
//        smartLog(optionsDisplayed ? "✅ Both 'Yes' and 'No' options are visible"
//                        : "❌ One or both options are missing",
//                "PassengersFromIsraelOptions", optionsDisplayed, LogMode.SOFT);
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
//            smartLog("❌ Test 'test_PreviousInsuranceStepOptions' failed: " + e.getMessage(), "PreviousInsuranceTest", false, LogMode.HARD);
//            throw e;
//        } finally {
//            assertAllSoft();
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
//            smartLog("❌ Test 'test_PassengersFromIsraelStepAppears' failed: " + e.getMessage(), "PassengersFromIsraelTest", false, LogMode.HARD);
//            throw e;
//        } finally {
//            assertAllSoft();
//        }
//    }
//
//
//
//
//
//}

// OnlineTravelInsuranceTests.java

import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OnlineTravelInsuranceTests extends Base {


    public void initTravelFlow() throws Exception {
        onlineTravelInsurancePage.scrollToTravelInsuranceButton();
        smartLog("✅ Scrolled to Travel Insurance section", "ScrollToTravel", true, LogMode.SOFT);

        onlineTravelInsurancePage.clickOnOnlineTravelInsuranceButton();
        smartLog("✅ Clicked on travel insurance button", "ClickTravelInsurance", true, LogMode.HARD);

        basePage.switchToNewTab();

        basePage.waitForElementToAppear(By.id("root"));

        // put it on utilitys
        String expectedTitle = "ביטוח חקלאי - ביטוח נסיעות לחו״ל";
        String actualTitle = basePage.getTitle();

        boolean titleOk = actualTitle.equals(expectedTitle);
        smartLog(titleOk ? "✅ Title OK: " + actualTitle : "❌ Wrong title. Got: " + actualTitle, "PageTitle", titleOk, LogMode.SOFT);

        boolean introVisible = onlineTravelInsurancePage.isQuoteIntroSectionVisible();
        smartLog(introVisible ? "✅ Quote intro section is visible" : "❌ Intro section is not visible", "IntroVisible", introVisible, LogMode.SOFT);
    }


    @Test(description = "TC-038 - Verify landing on online travel insurance flow")
    @Description("Open insurance flow, verify title and main intro block")
    public void test_StartInsuranceFlow() throws Exception {
        try {
            initTravelFlow();
            // הכל ב-BeforeMethod
        } catch (Exception e) {
            smartLog("❌ Test failed: " + e.getMessage(), "StartFlowFail", false, LogMode.HARD);
            throw e;
        } finally {
            assertAllSoft();
        }
    }


    @Test(description = "TC-039 - Verify previous insurance question appears")
    @Description("Click continue and verify previous insurance question and buttons")
    public void test_PreviousInsuranceQuestion() throws Exception {
        try {
            initTravelFlow();
            goToPreviousInsuranceQuestionStep();
            continueAndVerifyPreviousInsuranceQuestionStep();
        } catch (Exception e) {
            smartLog("❌ Test failed: " + e.getMessage(), "PreviousQuestionFail", false, LogMode.HARD);
            throw e;
        } finally {
            assertAllSoft();
        }
    }


    @Test(description = "TC-040 - Verify passengers from Israel question appears")
    @Description("Continue twice and verify question and both options")
    public void test_PassengersFromIsraelQuestion() throws Exception {
        try {
            initTravelFlow();
            goToPreviousInsuranceQuestionStep();
            continueAndVerifyPreviousInsuranceQuestionStep();
            verifyPassengersFromIsraelQuestionStep();
        } catch (Exception e) {
            smartLog("❌ Test failed: " + e.getMessage(), "PassengersQuestionFail", false, LogMode.HARD);
            throw e;
        } finally {
            assertAllSoft();
        }
    }



    @Test(description = "TC-041 - UI - Verify quote suggestion flow with previous insurance selected")
    @Description("Navigate to online travel insurance, select 'previously purchased insurance' and verify quote options page appears")
    public void test_QuoteWithPreviousInsurance() throws Exception {
        try {
            initTravelFlow();
            continueAndVerifyPreviousInsuranceQuestionStep();

            // בוחרים שכבר רכש בעבר
            onlineTravelInsurancePage.clickAlreadyPurchasedOptionButton();
            smartLog("✅ Clicked on 'Already Purchased' option", "ClickedAlreadyPurchased", true, LogMode.HARD);


            // בדיקת טקסט "שמחים לראותך שוב!"
            String welcomeText = basePage.getTextSafe(By.xpath("//*[contains(text(),'שמחים לראותך שוב!')]"));
            boolean welcomeTextDisplayed = !welcomeText.isEmpty();

            smartLog(welcomeTextDisplayed ? "✅ 'Welcome back' text is visible: '" + welcomeText + "'" : "❌ 'Welcome back' text not found",
                    "", welcomeTextDisplayed, LogMode.SOFT);


            // בדיקת שדה טלפון
            boolean emailFieldDisplayed = basePage.isDisplayedSafe(By.id("phone")); // שנה לפי locator האמיתי אם שונה
            smartLog(emailFieldDisplayed ? "✅ phone field is visible" : "❌ phone field not visible",
                    "", emailFieldDisplayed, LogMode.SOFT);

            // בדיקת שדה תעודת זהות
            boolean idFieldDisplayed = basePage.isDisplayedSafe(By.id("id")); // שנה לפי locator האמיתי אם שונה
            smartLog(idFieldDisplayed ? "✅ ID field is visible" : "❌ ID field not visible",
                    "", idFieldDisplayed, LogMode.SOFT);



        } catch (Exception e) {
            smartLog("❌ Test failed: " + e.getMessage(), "TC041_Failure", false, LogMode.HARD);
            throw e;
        } finally {
            assertAllSoft();
        }
    }




    @Test(description = "TC-042 - UI - Block flow when not all passengers depart from Israel")
    @Description("Verify that the process is blocked when user selects 'not all passengers are departing from Israel'")
    public void test_BlockFlowWhenPassengersNotFromIsrael() throws Exception {
        try {
            goToPreviousInsuranceQuestionStep();
            continueAndVerifyPreviousInsuranceQuestionStep();
            verifyPassengersFromIsraelQuestionStep();

            onlineTravelInsurancePage.clickPassengersFromIsraelNoOption();
            smartLog("✅ Clicked on 'No' for passengers from Israel", "ClickedNoPassengers", true, LogMode.HARD);

            boolean errorDisplayed = onlineTravelInsurancePage.isStopProcessErrorDisplayed();
            smartLog(errorDisplayed ? "✅ Error message visible" : "❌ Error message missing", "", errorDisplayed, LogMode.SOFT);

            boolean backButtonDisplayed = onlineTravelInsurancePage.isBackToHomeButtonDisplayed();
            smartLog(backButtonDisplayed ? "✅ 'Back to homepage' button visible" : "❌ 'Back to homepage' button missing", "", backButtonDisplayed, LogMode.SOFT);


        } catch (Exception e) {
            smartLog("❌ Test failed: " + e.getMessage(), "TC042_Failure", false, LogMode.HARD);
            throw e;
        } finally {
            assertAllSoft();
        }
    }



    public void continueToNextQuestion() throws Exception {
        onlineTravelInsurancePage.scrollToLetsContinueButton();
        onlineTravelInsurancePage.clickOnLetsContinueButton();
        Thread.sleep(600);
    }

    public void goToPreviousInsuranceQuestionStep() throws Exception {
        continueToNextQuestion();
        smartLog("✅ Clicked on first 'Continue'", "ClickContinueBtn1", true, LogMode.HARD);

        continueToNextQuestion();
        smartLog("✅ Clicked on second 'Continue'", "ClickContinueBtn2", true, LogMode.HARD);
    }


    public void continueAndVerifyPreviousInsuranceQuestionStep() throws Exception {
        String question = onlineTravelInsurancePage.getPreviousInsuranceQuestionText();
        boolean questionOk = question.contains("רכשת ביטוח");
        smartLog(questionOk ? "✅ Found question: " + question : "❌ Wrong question text: " + question, "PreviousQuestion", questionOk, LogMode.SOFT);


        boolean firstDisplayed = onlineTravelInsurancePage.isFirstTimeOptionDisplayed();
        smartLog(firstDisplayed ? "✅ 'First time' option visible" : "❌ 'First time' option not visible",
                "", firstDisplayed, LogMode.SOFT);

        boolean alreadyDisplayed = onlineTravelInsurancePage.isAlreadyPurchasedOptionDisplayed();
        smartLog(alreadyDisplayed ? "✅ 'Already purchased' option visible" : "❌ 'Already purchased' option not visible",
                "", alreadyDisplayed, LogMode.SOFT);
    }

    public void verifyPassengersFromIsraelQuestionStep() throws Exception {
        onlineTravelInsurancePage.clickFirstTimeOptionButton();

        String question = onlineTravelInsurancePage.getPassengersFromIsraelQuestionText();
        boolean questionOk = question.contains("יוצאים מישראל");
        smartLog(questionOk ? "✅ Found question: " + question : "❌ Wrong question text: " + question, "PassengersQuestion", questionOk, LogMode.SOFT);

        boolean yesOptionDisplayed = onlineTravelInsurancePage.isPassengersFromIsraelYesDisplayed();
        smartLog(yesOptionDisplayed ? "✅ 'Yes' option is visible" : "❌ 'Yes' option is NOT visible",
                "", yesOptionDisplayed, LogMode.SOFT);

        boolean noOptionDisplayed = onlineTravelInsurancePage.isPassengersFromIsraelNoDisplayed();
        smartLog(noOptionDisplayed ? "✅ 'No' option is visible" : "❌ 'No' option is NOT visible",
                "", noOptionDisplayed, LogMode.SOFT);

        boolean optionsOk = yesOptionDisplayed && noOptionDisplayed;
    }
}