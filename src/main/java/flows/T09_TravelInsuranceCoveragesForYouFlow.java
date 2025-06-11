package flows;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.BasePage;
import pages.T09_TravelInsuranceCoveragesForYouPage;
import utils.LoggerUtils;

import java.util.ArrayList;
import java.util.List;

import static utils.TravelInsuranceData.EXPECTED_COVERAGE_TITLES;

/**
 * Flow class for the "Coverages For You" step in the travel insurance wizard.
 * Handles validations, price checks, and UI navigation actions.
 */
public class T09_TravelInsuranceCoveragesForYouFlow {

    private static final Logger log = LoggerFactory.getLogger(T09_TravelInsuranceCoveragesForYouFlow.class);

    private final LoggerUtils loggerUtils;
    private final BasePage basePage;

    // Page object
    private final T09_TravelInsuranceCoveragesForYouPage coveragesForYouPage;

    // Previous flow
    private final T08_TravelInsurancePassengersDetailsFlow passengersDetailsFlow;

    /**
     * Constructor
     */
    public T09_TravelInsuranceCoveragesForYouFlow(BasePage basePage, LoggerUtils loggerUtils) {
        this.loggerUtils = loggerUtils;
        this.basePage = basePage;
        this.coveragesForYouPage = new T09_TravelInsuranceCoveragesForYouPage(basePage);
        this.passengersDetailsFlow = new T08_TravelInsurancePassengersDetailsFlow(basePage, loggerUtils);
    }

    // ========== Navigation ==========

    /**
     * Navigates to the Coverages For You step by completing all previous steps.
     */
    public void navigateToCoveragesForYouStep() throws Exception {
        passengersDetailsFlow.navigateToPassengersDetailsStep();
        passengersDetailsFlow.fillPassengerDetailsForm();
        passengersDetailsFlow.clickContinue();
    }

    // ========== Verifications ==========

    /**
     * Verifies that all expected coverage titles appear on the page.
     */
    public void verifyCoverages() {
        List<String> expectedTitles = EXPECTED_COVERAGE_TITLES;
        List<WebElement> titleElements = coveragesForYouPage.getCoverageTitles();
        List<String> actualTitles = new ArrayList<>();

        for (WebElement element : titleElements) {
            actualTitles.add(element.getText().trim());
        }

        for (int i = 0; i < expectedTitles.size(); i++) {
            String expected = expectedTitles.get(i);
            String actual = actualTitles.size() > i ? actualTitles.get(i) : "Missing";

            boolean isMatch = expected.equals(actual);
            loggerUtils.log("Expected: '" + expected + "', Actual: '" + actual + "'", "CoverageTitle_" + i, isMatch);
        }
    }

    /**
     * Verifies that the final price field is visible on the screen.
     */
    public void verifyFinalPriceVisible() {
        boolean finalPriceVisible = coveragesForYouPage.isFinalPriceVisible();
        loggerUtils.log(
                finalPriceVisible
                        ? "✅ 'Final Price' field is visible"
                        : "❌ 'Final Price' field is missing",
                "FinalPriceVisible",
                finalPriceVisible
        );
    }

    /**
     * Verifies that price updates after selecting a medical coverage.
     */
    public void verifyPriceUpdatedAfterAddingCoverage() throws Exception {
        double priceBefore = coveragesForYouPage.getFinalPriceValue();

        coveragesForYouPage.chooseMedicalCoverage();

        // Optional wait — could replace with wait for price change
        Thread.sleep(1000);

        double priceAfter = coveragesForYouPage.getFinalPriceValue();
        boolean isPriceUpdated = priceBefore >= 0 && priceAfter >= 0 && priceBefore != priceAfter;

        String logMessage = isPriceUpdated
                ? "✅ Price updated: before = " + priceBefore + ", after = " + priceAfter
                : "❌ Price did not update: before = " + priceBefore + ", after = " + priceAfter;

        loggerUtils.log(logMessage, "PriceUpdate", isPriceUpdated);
    }

    /**
     * Logs the text value of the final price field and verifies that it is not empty.
     */
    public void showFinalPrice() {
        String finalPriceText = coveragesForYouPage.getFinalPriceText();
        boolean isPricePresent = !finalPriceText.isEmpty();

        String message = isPricePresent
                ? "✅ Final price text found: " + finalPriceText
                : "❌ Final price text is missing or empty.";

        loggerUtils.log(message, "FinalPriceText", isPricePresent);
    }

    /**
     * Verifies visibility of Back and Continue buttons.
     */
    public void verifyNavigationButtonsAreVisible() {
        boolean backButtonVisible = coveragesForYouPage.isBackButtonVisible();
        loggerUtils.log(
                backButtonVisible
                        ? "✅ 'Back' button is visible"
                        : "❌ 'Back' button is missing",
                "BackButtonVisible",
                backButtonVisible
        );

        boolean continueButtonVisible = coveragesForYouPage.isContinueButtonVisible();
        loggerUtils.log(
                continueButtonVisible
                        ? "✅ 'Continue' button is visible"
                        : "❌ 'Continue' button is missing",
                "ContinueButtonVisible",
                continueButtonVisible
        );
    }

    /**
     * Verifies the colors of the step bar (completed/active steps).
     */
    public void verifyCurrentAndPreviousStepsByColor() {
        passengersDetailsFlow.verifyCurrentAndPreviousStepsByColor();
    }

    // ========== Actions ==========

    /**
     * Selects the medical coverage option.
     */
    public void chooseMedicalCoverage() {
        coveragesForYouPage.chooseMedicalCoverage();
        loggerUtils.log("✅ Selected medical coverage option", "MedicalCoverageFlow", true);
    }

    /**
     * Clicks the 'Continue' button on the coverages page.
     */
    public void clickContinue() {
        coveragesForYouPage.clickContinue();
        loggerUtils.log("✅ Clicked on 'Continue' button", "ContinueFromCoverages", true);
    }
}
