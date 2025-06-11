package flows;

import org.openqa.selenium.By;
import pages.BasePage;
import pages.T011_TravelInsuranceHealthDeclarationPage;
import utils.LoggerUtils;

/**
 * Flow class for the Health Declaration step in the travel insurance wizard.
 * Handles navigation from special coverages, button interactions, and visual validations.
 */
public class T011_TravelInsuranceHealthDeclarationFlow {

    // ========== Members ==========
    private final BasePage basePage;
    private final LoggerUtils loggerUtils;

    private final T011_TravelInsuranceHealthDeclarationPage healthDeclarationPage;
    private final T010_TravelInsuranceSpecialCoveragesFlow specialCoveragesFlow;

    // ========== Locators ==========
    private final By continueButton = By.cssSelector("button.sc-papXJ.jyxUhb.procceed");

    /**
     * Constructor
     * @param basePage base page utility
     * @param loggerUtils logger utility
     */
    public T011_TravelInsuranceHealthDeclarationFlow(BasePage basePage, LoggerUtils loggerUtils) {
        this.basePage = basePage;
        this.loggerUtils = loggerUtils;

        this.healthDeclarationPage = new T011_TravelInsuranceHealthDeclarationPage(basePage);
        this.specialCoveragesFlow = new T010_TravelInsuranceSpecialCoveragesFlow(loggerUtils, basePage);
    }

    // ========== Navigation ==========

    /**
     * Navigates to the Health Declaration step by completing all previous steps
     * and clicking 'Continue' from the special coverages screen.
     */
    public void navigateToHealthDeclarationStep() throws Exception {
        specialCoveragesFlow.navigateToSpecialCoveragesStep();
        specialCoveragesFlow.addSpecialCoverage();
        basePage.forceClick(continueButton);
        loggerUtils.log("✅ Navigated to Health Declaration step (clicked continue)", "NavigateHealthDeclaration", true);
    }

    // ========== Actions ==========

    /**
     * Clicks the 'No' option in the health declaration form.
     */
    public void clickNoButton() {
        healthDeclarationPage.clickNoButton();
        loggerUtils.log("✅ Clicked 'No' in health declaration", "ClickNoHealth", true);
    }

    /**
     * Clicks the 'Continue' button on the Health Declaration page.
     */
    public void clickContinue() throws Exception {
        Thread.sleep(1500); // Optional: replace with wait for loading state to disappear
        basePage.click(continueButton);
        loggerUtils.log("✅ Clicked 'Continue' button in health declaration", "ClickContinueHealth", true);
    }

    // ========== Verifications ==========

    /**
     * Verifies the color indicators for current and previous steps.
     */
    public void verifyCurrentAndPreviousStepsByColor() {
        String result = basePage.getStepBarColorValidationResult();
        boolean isPass = result.startsWith("PASS");
        String summary = result.replaceFirst("PASS\n|FAIL\n", "");

        loggerUtils.log("Step validation in health declaration:\n" + summary, "HealthStepColorValidation", isPass);
    }

    /**
     * Verifies visibility of 'Back' and 'Continue' navigation buttons.
     */
    public void verifyNavigationButtonsAreVisible() {
        specialCoveragesFlow.verifyNavigationButtonsAreVisible();
    }

    /**
     * Logs and verifies that the final price is displayed.
     */
    public void showFinalPrice() {
        specialCoveragesFlow.showFinalPrice();
    }
}
