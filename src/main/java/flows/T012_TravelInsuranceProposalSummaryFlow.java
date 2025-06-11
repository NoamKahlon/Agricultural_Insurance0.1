package flows;

import pages.BasePage;
import pages.T012_TravelInsuranceProposalSummaryPage;
import utils.LoggerUtils;

import java.util.List;

/**
 * Flow class responsible for handling the Proposal Summary step
 * in the online travel insurance process.
 */
public class T012_TravelInsuranceProposalSummaryFlow {

    // ========== Members ==========
    private final LoggerUtils loggerUtils;
    private final BasePage basePage;

    // ========== Page ==========
    private final T012_TravelInsuranceProposalSummaryPage proposalSummaryPage;

    // ========== Flow Dependencies ==========
    private final T011_TravelInsuranceHealthDeclarationFlow healthDeclarationFlow;

    /**
     * Constructor
     * @param loggerUtils Logger utility for reporting
     * @param basePage Shared base page for web interaction
     */
    public T012_TravelInsuranceProposalSummaryFlow(LoggerUtils loggerUtils, BasePage basePage) {
        this.loggerUtils = loggerUtils;
        this.basePage = basePage;

        this.proposalSummaryPage = new T012_TravelInsuranceProposalSummaryPage(basePage);
        this.healthDeclarationFlow = new T011_TravelInsuranceHealthDeclarationFlow(basePage, loggerUtils);
    }

    // ========== Navigation ==========

    /**
     * Navigates to the Proposal Summary step through all prior flow steps.
     */
    public void navigateToProposalSummaryStep() throws Exception {
        healthDeclarationFlow.navigateToHealthDeclarationStep();
        healthDeclarationFlow.clickNoButton();
        healthDeclarationFlow.clickContinue();
    }

    // ========== Verifications ==========

    /**
     * Verifies the summary details displayed on the proposal summary page.
     */
    public void verifySummaryDetails() {
        String passengersNumber = proposalSummaryPage.getPassengersNumber();
        loggerUtils.log("👥 Number of passengers: " + passengersNumber, "SummaryPassengers", true);

        String destination = proposalSummaryPage.getDestination();
        loggerUtils.log("🌍 Destination: " + destination, "SummaryDestination", true);

        String dates = proposalSummaryPage.getDates();
        loggerUtils.log("📅 Travel dates: " + dates, "SummaryDates", true);

        List<String> allCoverages = proposalSummaryPage.getAllPassengerCoverages();
        loggerUtils.log("📋 Coverages: " + String.join(", ", allCoverages), "SummaryCoverages", true);

        String allParagraphsText = proposalSummaryPage.getSpecificPassengerCoveragesTexts();
        loggerUtils.log("📄 Accordion Content:\n" + allParagraphsText, "SummaryAccordionContent", true);
    }

    /**
     * Verifies the color state of the current and previous steps in the wizard.
     */
    public void verifyCurrentAndPreviousStepsByColor() {
        healthDeclarationFlow.verifyCurrentAndPreviousStepsByColor();
    }

    // ========== Actions ==========

    /**
     * Clicks the continue button on the summary step.
     */
    public void clickContinue() throws Exception {
        healthDeclarationFlow.clickContinue();
    }

    /**
     * Verifies visibility of navigation buttons ('Back' and 'Continue').
     */
    public void verifyNavigationButtonsAreVisible() {
        healthDeclarationFlow.verifyNavigationButtonsAreVisible();
    }

    /**
     * Logs the final insurance price, if available.
     */
    public void showFinalPrice() {
        healthDeclarationFlow.showFinalPrice();
    }
}
