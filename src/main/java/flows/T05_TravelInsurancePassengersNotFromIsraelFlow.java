package flows;

import pages.BasePage;
import pages.T05_TravelInsurancePassengersNotFromIsraelPage;
import utils.LoggerUtils;

/**
 * Flow class for handling the case where passengers are not departing from Israel.
 * Covers the redirection logic, error handling, and validation of stop messages.
 */
public class T05_TravelInsurancePassengersNotFromIsraelFlow {

    // ========== Members ==========
    private final BasePage basePage;
    private final LoggerUtils loggerUtils;

    private final T05_TravelInsurancePassengersNotFromIsraelPage passengersNotFromIsraelPage;

    private final T01_TravelInsuranceIntroFlow introFlow;
    private final T02_TravelInsurancePurchasedBeforeQuestionFlow purchasedBeforeQuestionFlow;
    private final T03_TravelInsurancePassengersFromIsraelQuestionFlow passengersFromIsraelQuestionFlow;

    /**
     * Constructor
     * @param basePage Shared base page instance
     * @param loggerUtils Logger for reporting and validation
     */
    public T05_TravelInsurancePassengersNotFromIsraelFlow(BasePage basePage, LoggerUtils loggerUtils) {
        this.basePage = basePage;
        this.loggerUtils = loggerUtils;

        this.passengersNotFromIsraelPage = new T05_TravelInsurancePassengersNotFromIsraelPage(basePage);

        this.introFlow = new T01_TravelInsuranceIntroFlow(basePage, loggerUtils);
        this.purchasedBeforeQuestionFlow = new T02_TravelInsurancePurchasedBeforeQuestionFlow(basePage, loggerUtils);
        this.passengersFromIsraelQuestionFlow = new T03_TravelInsurancePassengersFromIsraelQuestionFlow(basePage, loggerUtils);
    }

    // ========== Actions ==========

    /**
     * Navigates from the intro to the 'Are all passengers departing from Israel?' step.
     */
    public void navigateToPassengersFromIsraelQuestionStep() throws Exception {
        passengersFromIsraelQuestionFlow.navigateToPassengersFromIsraelQuestionStep();
    }

    /**
     * Selects the 'No' option for the 'Are passengers from Israel' question.
     */
    public void clickPassengersFromIsraelNoOption() throws InterruptedException {
        passengersNotFromIsraelPage.clickPassengersAreNotFromIsrael();
        loggerUtils.log("✅ Clicked on 'No' for passengers from Israel", "ClickedNoPassengers", true);
    }

    // ========== Verifications ==========

    /**
     * Verifies that the stop-process error message is displayed after selecting 'No'.
     */
    public void isStopProcessErrorDisplayed() throws InterruptedException {
        boolean errorDisplayed = passengersNotFromIsraelPage.isStopProcessErrorDisplayed();
        loggerUtils.log(
                errorDisplayed
                        ? "✅ Error message is visible"
                        : "❌ Error message is missing",
                "StopProcessErrorMessage",
                errorDisplayed
        );
    }

    /**
     * Verifies that the 'Back to homepage' button is displayed on the stop screen.
     */
    public void isBackToHomeButtonDisplayed() throws InterruptedException {
        boolean backButtonDisplayed = passengersNotFromIsraelPage.isBackToHomeButtonDisplayed();
        loggerUtils.log(
                backButtonDisplayed
                        ? "✅ 'Back to homepage' button is visible"
                        : "❌ 'Back to homepage' button is missing",
                "BackToHomeButton",
                backButtonDisplayed
        );
    }
}
