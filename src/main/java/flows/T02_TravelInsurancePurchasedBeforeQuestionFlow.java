package flows;

import pages.BasePage;
import pages.T02_TravelInsurancePurchasedBeforeQuestionPage;
import utils.LoggerUtils;
import utils.TravelInsuranceData;

/**
 * Flow class for handling the 'Have you purchased insurance before?' question step
 * in the travel insurance wizard.
 */
public class T02_TravelInsurancePurchasedBeforeQuestionFlow {

    // ========== Members ==========
    private final BasePage basePage;
    private final LoggerUtils loggerUtils;
    private final T02_TravelInsurancePurchasedBeforeQuestionPage purchasedBeforeQuestionPage;
    private final T01_TravelInsuranceIntroFlow introFlow;

    /**
     * Constructor
     * @param basePage shared BasePage instance
     * @param loggerUtils logger instance for reporting
     */
    public T02_TravelInsurancePurchasedBeforeQuestionFlow(BasePage basePage, LoggerUtils loggerUtils) {
        this.basePage = basePage;
        this.loggerUtils = loggerUtils;

        this.introFlow = new T01_TravelInsuranceIntroFlow(basePage, loggerUtils);
        this.purchasedBeforeQuestionPage = new T02_TravelInsurancePurchasedBeforeQuestionPage(basePage);
    }

    // ========== Actions ==========

    /**
     * Navigates from the intro step to the 'Have you purchased insurance before?' step.
     */
    public void navigateToPurchasedInsuranceBeforeQuestionStep() throws Exception {
        introFlow.navigateToIntroPage();
        introFlow.verifyTitle();
        introFlow.clickContinueButton(); // From intro
        introFlow.clickContinueButton(); // From quote/intro section
    }

    /**
     * Clicks on the 'First time' option button.
     */
    public void clickFirstTimeButton() {
        purchasedBeforeQuestionPage.clickFirstTimeOption();
        loggerUtils.log("✅ Clicked 'First Time' option button", "ClickFirstTimeOptionButton", true);
    }

    /**
     * Clicks on the 'Already purchased' option button.
     */
    public void clickAlreadyPurchasedButton() {
        purchasedBeforeQuestionPage.clickAlreadyPurchasedOption();
        loggerUtils.log("✅ Clicked 'Already Purchased' option button", "ClickAlreadyPurchasedOptionButton", true);
    }

    // ========== Verifications ==========

    /**
     * Verifies that the question text and both answer options are visible and correct.
     */
    public void verifyInsurancePurchasedQuestion() throws Exception {
        String question = purchasedBeforeQuestionPage.getQuestionText();
        boolean questionOk = question.contains(TravelInsuranceData.DID_YOU_PURCHASE_INSURANCE_TEXT);

        loggerUtils.log(
                questionOk
                        ? "✅ Found correct question text: " + question
                        : "❌ Wrong question text: " + question,
                "VerifyQuestionText",
                questionOk
        );

        boolean firstDisplayed = purchasedBeforeQuestionPage.isFirstTimeOptionVisible();
        loggerUtils.log(
                firstDisplayed
                        ? "✅ 'First time' option is visible"
                        : "❌ 'First time' option is NOT visible",
                "FirstTimeOptionVisible",
                firstDisplayed
        );

        boolean alreadyDisplayed = purchasedBeforeQuestionPage.isAlreadyPurchasedOptionVisible();
        loggerUtils.log(
                alreadyDisplayed
                        ? "✅ 'Already purchased' option is visible"
                        : "❌ 'Already purchased' option is NOT visible",
                "AlreadyPurchasedOptionVisible",
                alreadyDisplayed
        );
    }
}
