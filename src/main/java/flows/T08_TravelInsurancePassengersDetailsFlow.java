package flows;

import pages.BasePage;
import pages.T08_TravelInsurancePassengersDetailsPage;
import utils.LoggerUtils;

import static utils.TravelInsuranceData.*;

/**
 * Flow class for handling passenger details input during the travel insurance flow.
 * Includes navigation, form filling, field validation, and step verification.
 */
public class T08_TravelInsurancePassengersDetailsFlow {

    // ========== Members ==========
    private final BasePage basePage;
    private final LoggerUtils loggerUtils;

    private final T08_TravelInsurancePassengersDetailsPage passengersDetailsPage;
    private final T07_TravelInsuranceDateSelectionFlow dateSelectionFlow;

    /**
     * Constructor
     * @param basePage shared base page
     * @param loggerUtils logger for structured logging
     */
    public T08_TravelInsurancePassengersDetailsFlow(BasePage basePage, LoggerUtils loggerUtils) {
        this.basePage = basePage;
        this.loggerUtils = loggerUtils;

        this.passengersDetailsPage = new T08_TravelInsurancePassengersDetailsPage(basePage);
        this.dateSelectionFlow = new T07_TravelInsuranceDateSelectionFlow(basePage, loggerUtils);
    }

    // ========== Navigation ==========

    /**
     * Navigates to the passenger details step by completing previous steps.
     */
    public void navigateToPassengersDetailsStep() throws Exception {
        dateSelectionFlow.navigateToDateSelectionStep();
        dateSelectionFlow.pickDates();
        dateSelectionFlow.clickContinueButton();
    }

    // ========== Actions ==========

    /**
     * Clicks the 'Continue' button on the passenger details step.
     */
    public void clickContinue() {
        dateSelectionFlow.clickContinueButton();
        loggerUtils.log("✅ Clicked 'Continue' button", "ContinueClicked", true);
    }

    /**
     * Fills the passenger details form using static test data.
     */
    public void fillPassengerDetailsForm() {
        passengersDetailsPage.selectMaleGender();
        loggerUtils.log("✅ Selected male gender", "GenderSelected", true);

        passengersDetailsPage.enterPhoneNumber(PHONE);
        loggerUtils.log("✅ Entered phone number: " + PHONE, "PhoneFilled", true);

        passengersDetailsPage.enterFirstNameEnglish(FIRST_NAME_EN);
        loggerUtils.log("✅ Entered first name (EN): " + FIRST_NAME_EN, "FirstNameEn", true);

        passengersDetailsPage.enterLastNameEnglish(LAST_NAME_EN);
        loggerUtils.log("✅ Entered last name (EN): " + LAST_NAME_EN, "LastNameEn", true);

        passengersDetailsPage.enterFirstNameHebrew(FIRST_NAME_HE);
        loggerUtils.log("✅ Entered first name (HE): " + FIRST_NAME_HE, "FirstNameHe", true);

        passengersDetailsPage.enterLastNameHebrew(LAST_NAME_HE);
        loggerUtils.log("✅ Entered last name (HE): " + LAST_NAME_HE, "LastNameHe", true);

        passengersDetailsPage.enterIdNumber(ID_NUMBER);
        loggerUtils.log("✅ Entered ID number: " + ID_NUMBER, "IdNumber", true);

        passengersDetailsPage.enterBirthDate(BIRTH_DATE);
        loggerUtils.log("✅ Entered birth date: " + BIRTH_DATE, "BirthDate", true);

        passengersDetailsPage.enterEmail(EMAIL);
        loggerUtils.log("✅ Entered email address: " + EMAIL, "Email", true);
    }

    // ========== Verifications ==========

    /**
     * Verifies the color indicators for current and completed steps in the progress bar.
     */
    public void verifyCurrentAndPreviousStepsByColor() {
        dateSelectionFlow.verifyCurrentAndPreviousStepsByColor();
    }

    /**
     * Verifies that all passenger detail form fields are visible.
     */
    public void verifyPassengerDetailsFieldsAreVisible() {
        loggerUtils.log("📋 Verifying passenger detail fields visibility", "PassengerDetailsFieldsCheck", true);

        loggerUtils.log("🆔 ID field visible", "IdField", passengersDetailsPage.isIdFieldVisible());
        loggerUtils.log("📱 Phone field visible", "PhoneField", passengersDetailsPage.isPhoneFieldVisible());
        loggerUtils.log("🧑 First Name (HE) field visible", "FirstNameHeField", passengersDetailsPage.isFirstNameHebrewVisible());
        loggerUtils.log("👨‍👩‍👧 Last Name (HE) field visible", "LastNameHeField", passengersDetailsPage.isLastNameHebrewVisible());
        loggerUtils.log("🧑 First Name (EN) field visible", "FirstNameEnField", passengersDetailsPage.isFirstNameEnglishVisible());
        loggerUtils.log("👨‍👩‍👧 Last Name (EN) field visible", "LastNameEnField", passengersDetailsPage.isLastNameEnglishVisible());
        loggerUtils.log("🎂 Birth Date field visible", "BirthDateField", passengersDetailsPage.isBirthDateFieldVisible());
        loggerUtils.log("📧 Email field visible", "EmailField", passengersDetailsPage.isEmailFieldVisible());
        loggerUtils.log("🚻 Gender field visible", "GenderField", passengersDetailsPage.isGenderFieldVisible());
        loggerUtils.log("👥 Add Passenger field visible", "AddPassengerField", passengersDetailsPage.isAddPassengerFieldVisible());
        loggerUtils.log("➡️ Continue button visible", "ContinueButton", passengersDetailsPage.isContinueButtonVisible());
        loggerUtils.log("⬅️ Back button visible", "BackButton", passengersDetailsPage.isBackButtonVisible());
    }
}
