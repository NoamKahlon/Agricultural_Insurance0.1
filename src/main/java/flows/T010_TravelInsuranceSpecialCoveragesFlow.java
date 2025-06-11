package flows;

import pages.BasePage;
import pages.T010_TravelInsuranceSpecialCoveragesPage;
import utils.LoggerUtils;

import java.util.List;

import static utils.TravelInsuranceData.EXPECTED_COVERAGE_TITLES;

/**
 * Flow class for handling the "Special Coverages" step in the travel insurance wizard.
 * Includes coverage selection, title validation, and navigation handling.
 */
public class T010_TravelInsuranceSpecialCoveragesFlow {

    // ========== Members ==========
    private final LoggerUtils loggerUtils;
    private final BasePage basePage;

    // Pages
    private final T010_TravelInsuranceSpecialCoveragesPage specialCoveragesPage;

    // Flows
    private final T09_TravelInsuranceCoveragesForYouFlow coveragesForYouFlow;

    /**
     * Constructor
     * @param loggerUtils utility for logging actions and validations
     * @param basePage shared BasePage instance
     */
    public T010_TravelInsuranceSpecialCoveragesFlow(LoggerUtils loggerUtils, BasePage basePage) {
        this.loggerUtils = loggerUtils;
        this.basePage = basePage;

        this.specialCoveragesPage = new T010_TravelInsuranceSpecialCoveragesPage(basePage);
        this.coveragesForYouFlow = new T09_TravelInsuranceCoveragesForYouFlow(basePage, loggerUtils);
    }

    // ========== Navigation ==========

    /**
     * Navigates to the special coverages step by completing the previous steps.
     */
    public void navigateToSpecialCoveragesStep() throws Exception {
        coveragesForYouFlow.navigateToCoveragesForYouStep();
        coveragesForYouFlow.chooseMedicalCoverage();
        coveragesForYouFlow.clickContinue();
    }

    // ========== Verifications ==========

    /**
     * Verifies that the title texts in the special coverage section match expected values.
     */
    public void verifyTitleTexts() {
        List<String> actualTitles = specialCoveragesPage.getActualTitles();
        List<String> expectedTitles = EXPECTED_COVERAGE_TITLES;

        boolean allMatch = actualTitles.equals(expectedTitles);

        loggerUtils.log(
                allMatch
                        ? "✅ All title texts match expected list:\n" + actualTitles
                        : "❌ Title texts do not match.\nExpected: " + expectedTitles + "\nActual: " + actualTitles,
                "SpecialCoverageTitles",
                allMatch
        );
    }

    /**
     * Verifies color indicators for step progress.
     */
    public void verifyCurrentAndPreviousStepsByColor() {
        coveragesForYouFlow.verifyCurrentAndPreviousStepsByColor();
    }

    /**
     * Verifies the visibility of navigation buttons (Back/Continue).
     */
    public void verifyNavigationButtonsAreVisible() {
        coveragesForYouFlow.verifyNavigationButtonsAreVisible();
    }

    /**
     * Verifies that final price is displayed and logged.
     */
    public void showFinalPrice() {
        coveragesForYouFlow.showFinalPrice();
    }

    // ========== Actions ==========

    /**
     * Adds a special coverage (e.g., Extreme Sports) by clicking relevant options.
     */
    public void addSpecialCoverage() {
        specialCoveragesPage.clickExtremeSportsButton();
        loggerUtils.log("✅ Clicked on 'Extreme Sports' special coverage", "ExtremeSportsClick", true);

        specialCoveragesPage.clickCustomerName();
        loggerUtils.log("✅ Selected 'Extreme Sports' for Customer", "ExtremeSportsCustomerLabelClick", true);

        specialCoveragesPage.clickAddCoverage();
        loggerUtils.log("✅ Clicked on 'Add Coverage' button", "AddCoverageClick", true);
    }
}
