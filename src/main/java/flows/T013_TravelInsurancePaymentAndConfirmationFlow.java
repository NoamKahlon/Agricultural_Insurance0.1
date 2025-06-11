package flows;

import pages.BasePage;
import pages.T013_TravelInsurancePaymentAndConfirmationPage;
import utils.LoggerUtils;

import static utils.TravelInsuranceData.EXPECTED_CONTACT_TITLES;

/**
 * Flow class for the Payment and Confirmation step in the travel insurance wizard.
 * Responsible for navigating from the summary step and verifying all required fields for credit card and contact info.
 */
public class T013_TravelInsurancePaymentAndConfirmationFlow {

    // ========== Members ==========
    private final BasePage basePage;
    private final LoggerUtils loggerUtils;

    private final T013_TravelInsurancePaymentAndConfirmationPage paymentAndConfirmationPage;
    private final T012_TravelInsuranceProposalSummaryFlow proposalSummaryFlow;

    // ========== Constructor ==========
    public T013_TravelInsurancePaymentAndConfirmationFlow(BasePage basePage, LoggerUtils loggerUtils) {
        this.basePage = basePage;
        this.loggerUtils = loggerUtils;

        this.paymentAndConfirmationPage = new T013_TravelInsurancePaymentAndConfirmationPage(basePage);
        this.proposalSummaryFlow = new T012_TravelInsuranceProposalSummaryFlow(loggerUtils, basePage);
    }

    // ========== Navigation ==========

    /**
     * Navigates to the Payment and Confirmation step by going through the proposal summary flow.
     */
    public void navigateToPaymentAndConfirmationStep() throws Exception {
        proposalSummaryFlow.navigateToProposalSummaryStep();
        proposalSummaryFlow.clickContinue();
    }

    // ========== Verifications ==========

    /**
     * Verifies that all credit card holder personal details fields are visible.
     */
    public void verifyCreditCardHolderDetails() {
        paymentAndConfirmationPage.verifyCardHolderFirstNameFieldIsDisplayed();
        loggerUtils.log("✅ First name field is displayed", "", true);

        paymentAndConfirmationPage.verifyCardHolderLastNameFieldIsDisplayed();
        loggerUtils.log("✅ Last name field is displayed", "", true);

        paymentAndConfirmationPage.verifyCardHolderEmailFieldIsDisplayed();
        loggerUtils.log("✅ Email field is displayed", "", true);

        paymentAndConfirmationPage.verifyNumberOfPaymentsFieldIsDisplayed();
        loggerUtils.log("✅ Number of payments field is displayed", "", true);
    }

    /**
     * Verifies that all credit card payment fields inside the iframe are visible.
     */
    public void verifyCreditCardDetails() {
        paymentAndConfirmationPage.switchToIframe();

        paymentAndConfirmationPage.verifyCardNumberFieldIsDisplayed();
        loggerUtils.log("✅ Card number field is displayed", "", true);

        paymentAndConfirmationPage.verifyCardExpiryYearFieldIsDisplayed();
        loggerUtils.log("✅ Expiry year field is displayed", "", true);

        paymentAndConfirmationPage.verifyCardHolderIdFieldIsDisplayed();
        loggerUtils.log("✅ Card holder ID field is displayed", "", true);

        paymentAndConfirmationPage.switchToDefaultContent();
    }

    /**
     * Verifies that address, contact and confirmation checkboxes are displayed.
     */
    public void verifyAddressAndContactDetails() {
        paymentAndConfirmationPage.verifyCityFieldIsDisplayed();
        loggerUtils.log("✅ City field is displayed", "", true);

        paymentAndConfirmationPage.verifyStreetFieldIsDisplayed();
        loggerUtils.log("✅ Street field is displayed", "", true);

        paymentAndConfirmationPage.verifyHouseNumberFieldIsDisplayed();
        loggerUtils.log("✅ House number field is displayed", "", true);

        paymentAndConfirmationPage.verifyApartmentNumberFieldIsDisplayed();
        loggerUtils.log("✅ Apartment number field is displayed", "", true);

        paymentAndConfirmationPage.verifyPostalCodeFieldIsDisplayed();
        loggerUtils.log("✅ Postal code field is displayed", "", true);

        paymentAndConfirmationPage.verifyInsuranceDeclarationCheckboxIsDisplayed();
        loggerUtils.log("✅ Insurance declaration checkbox is displayed", "", true);

        paymentAndConfirmationPage.verifyTermsAndConditionsCheckboxIsDisplayed();
        loggerUtils.log("✅ Terms and conditions checkbox is displayed", "", true);
    }

    /**
     * Validates current and previous steps in the progress bar by color.
     */
    public void verifyCurrentAndPreviousStepsByColor() {
        proposalSummaryFlow.verifyCurrentAndPreviousStepsByColor();
    }

    /**
     * Verifies visibility of 'Back' and 'Continue' navigation buttons.
     */
    public void verifyBackButtonIsVisible() {
        boolean backButtonVisible = paymentAndConfirmationPage.verifyBackButton();
        loggerUtils.log(backButtonVisible
                ? "✅ 'Back' button is visible"
                : "❌ 'Back' button is missing", "back_button_visible", backButtonVisible);
    }

    public void verifyPayButtonIsVisible() {
        paymentAndConfirmationPage.switchToIframe();
        boolean payButtonVisible = paymentAndConfirmationPage.verifyPayButton();
        loggerUtils.log(payButtonVisible
                ? "✅ 'Pay' button is visible"
                : "❌ 'Pay' button is missing", "pay_button_visible", payButtonVisible);
        paymentAndConfirmationPage.switchToDefaultContent();
    }

    /**
     * Logs the final price display on screen.
     */
    public void showFinalPrice() {
        proposalSummaryFlow.showFinalPrice();
    }
}
