package flows;

import pages.BasePage;
import pages.T07_TravelInsuranceDateSelectionPage;
import utils.LoggerUtils;

/**
 * Flow class for handling the travel insurance date selection step.
 * Covers navigation, date picking, and UI verifications.
 */
public class T07_TravelInsuranceDateSelectionFlow {

    // ========== Members ==========
    private final BasePage basePage;
    private final LoggerUtils loggerUtils;

    private final T07_TravelInsuranceDateSelectionPage dateSelectionPage;
    private final T06_TravelInsuranceDestinationSelectionFlow destinationSelectionFlow;

    /**
     * Constructor
     * @param basePage shared BasePage instance
     * @param loggerUtils logger for reporting validations
     */
    public T07_TravelInsuranceDateSelectionFlow(BasePage basePage, LoggerUtils loggerUtils) {
        this.basePage = basePage;
        this.loggerUtils = loggerUtils;

        this.dateSelectionPage = new T07_TravelInsuranceDateSelectionPage(basePage);
        this.destinationSelectionFlow = new T06_TravelInsuranceDestinationSelectionFlow(basePage, loggerUtils);
    }

    // ========== Actions ==========

    /**
     * Navigates to the date selection step by completing the previous steps.
     */
    public void navigateToDateSelectionStep() throws Exception {
        destinationSelectionFlow.navigateToDestinationSelectionStep();
        destinationSelectionFlow.clickEuropeCheckBox();
        dateSelectionPage.clickContinueButton();
    }

    /**
     * Selects travel start and end dates using page methods.
     */
    public void pickDates() {
        dateSelectionPage.pickDates();
    }

    /**
     * Clicks the 'Continue' button to proceed to the next step.
     */
    public void clickContinueButton() {
        dateSelectionPage.clickContinueButton();
    }

    // ========== Verifications ==========

    /**
     * Verifies visibility of start date, end date, and navigation buttons.
     */
    public void verifyDateSelection() {
        boolean isStartVisible = dateSelectionPage.verifyStartDateFieldDisplayed();
        loggerUtils.log(
                isStartVisible
                        ? "✅ Start date field is visible"
                        : "❌ Start date field is NOT visible",
                "StartDateField",
                isStartVisible
        );

        boolean isEndVisible = dateSelectionPage.verifyEndDateFieldDisplayed();
        loggerUtils.log(
                isEndVisible
                        ? "✅ End date field is visible"
                        : "❌ End date field is NOT visible",
                "EndDateField",
                isEndVisible
        );

        boolean backButtonVisible = dateSelectionPage.isBackButtonVisible();
        loggerUtils.log(
                backButtonVisible
                        ? "✅ 'Back' button is visible"
                        : "❌ 'Back' button is missing",
                "BackButtonVisible",
                backButtonVisible
        );

        boolean continueButtonVisible = dateSelectionPage.isContinueButtonVisible();
        loggerUtils.log(
                continueButtonVisible
                        ? "✅ 'Continue' button is visible"
                        : "❌ 'Continue' button is missing",
                "ContinueButtonVisible",
                continueButtonVisible
        );
    }

    /**
     * Verifies the step bar colors (completed vs. current step) are displayed correctly.
     */
    public void verifyCurrentAndPreviousStepsByColor() {
        destinationSelectionFlow.verifyCurrentAndPreviousStepsByColor();
    }
}
