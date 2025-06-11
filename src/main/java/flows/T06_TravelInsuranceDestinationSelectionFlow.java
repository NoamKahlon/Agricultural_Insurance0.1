package flows;

import org.openqa.selenium.WebElement;
import pages.BasePage;
import pages.T06_TravelInsuranceDestinationSelectionPage;
import utils.LoggerUtils;

import java.util.List;

/**
 * Flow class for handling the destination selection step
 * in the travel insurance wizard.
 */
public class T06_TravelInsuranceDestinationSelectionFlow {

    // ========== Members ==========
    private final BasePage basePage;
    private final LoggerUtils loggerUtils;

    private final T06_TravelInsuranceDestinationSelectionPage destinationSelectionPage;
    private final T03_TravelInsurancePassengersFromIsraelQuestionFlow passengersFromIsraelQuestionFlow;

    /**
     * Constructor
     * @param basePage Shared BasePage instance
     * @param loggerUtils Logger for validation logging
     */
    public T06_TravelInsuranceDestinationSelectionFlow(BasePage basePage, LoggerUtils loggerUtils) {
        this.basePage = basePage;
        this.loggerUtils = loggerUtils;

        this.destinationSelectionPage = new T06_TravelInsuranceDestinationSelectionPage(basePage);
        this.passengersFromIsraelQuestionFlow = new T03_TravelInsurancePassengersFromIsraelQuestionFlow(basePage, loggerUtils);
    }

    // ========== Actions ==========

    /**
     * Navigates to the destination selection step by answering previous steps.
     */
    public void navigateToDestinationSelectionStep() throws Exception {
        passengersFromIsraelQuestionFlow.navigateToPassengersFromIsraelQuestionStep();
        passengersFromIsraelQuestionFlow.clickPassengersFromIsraelOption();
    }

    /**
     * Clicks on the 'Europe' destination checkbox.
     */
    public void clickEuropeCheckBox() {
        destinationSelectionPage.clickEuropeCheckBox();
    }

    // ========== Verifications ==========

    /**
     * Verifies that the destination step UI elements are present and functional.
     */
    public void verifyDestinationSelectionStep() {
        List<WebElement> destinations = destinationSelectionPage.getDestinationOptions();
        boolean hasOptions = !destinations.isEmpty();

        loggerUtils.log(
                hasOptions
                        ? "✅ Destination options loaded successfully (" + destinations.size() + " options)"
                        : "❌ No destination options found",
                "DestinationOptions",
                hasOptions
        );

        boolean titleDisplayed = destinationSelectionPage.isWhereAreYouGoingTitleDisplayed();
        loggerUtils.log(
                titleDisplayed
                        ? "✅ 'Where are you going?' title is displayed"
                        : "❌ 'Where are you going?' title is NOT displayed",
                "DestinationTitle",
                titleDisplayed
        );

        boolean tooltipVisible = destinationSelectionPage.isTooltipIconVisible();
        loggerUtils.log(
                tooltipVisible
                        ? "✅ Tooltip icon is visible"
                        : "❌ Tooltip icon is missing",
                "TooltipIcon",
                tooltipVisible
        );

        boolean backButtonVisible = destinationSelectionPage.isBackButtonVisible();
        loggerUtils.log(
                backButtonVisible
                        ? "✅ 'Back' button is visible"
                        : "❌ 'Back' button is missing",
                "BackButtonVisible",
                backButtonVisible
        );

        boolean continueButtonVisible = destinationSelectionPage.isContinueButtonVisible();
        loggerUtils.log(
                continueButtonVisible
                        ? "✅ 'Continue' button is visible"
                        : "❌ 'Continue' button is missing",
                "ContinueButtonVisible",
                continueButtonVisible
        );
    }

    /**
     * Verifies the visual color status of the step bar (completed vs. current).
     */
    public void verifyCurrentAndPreviousStepsByColor() {
        String result = basePage.getStepBarColorValidationResult();
        boolean isValid = result.startsWith("PASS");
        String summary = result.replaceFirst("PASS\n|FAIL\n", "");

        loggerUtils.log("Step validation:\n" + summary, "StepsColorValidation", isValid);
    }
}
