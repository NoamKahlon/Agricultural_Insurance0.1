package flows;

import pages.BasePage;
import pages.T01_TravelInsuranceIntroPage;
import utils.LoggerUtils;
import utils.TravelInsuranceData;

/**
 * Flow class for the Travel Insurance Intro step.
 * Handles navigation to the page, title verification, and continuation to the next step.
 */
public class T01_TravelInsuranceIntroFlow {

    // ========== Members ==========
    private final BasePage basePage;
    private final T01_TravelInsuranceIntroPage t01TravelInsuranceIntroPage;
    private final LoggerUtils loggerUtils;

    /**
     * Constructor
     * @param basePage shared BasePage instance
     * @param loggerUtils logger for test logging
     */
    public T01_TravelInsuranceIntroFlow(BasePage basePage, LoggerUtils loggerUtils) {
        this.loggerUtils = loggerUtils;
        this.basePage = basePage;
        this.t01TravelInsuranceIntroPage = new T01_TravelInsuranceIntroPage(basePage);
    }

    // ========== Actions ==========

    /**
     * Navigates to the travel insurance page by scrolling to the section,
     * clicking the relevant button, and switching to the new browser tab.
     */
    public void navigateToIntroPage() {
        t01TravelInsuranceIntroPage.scrollToTravelInsuranceButton();
        loggerUtils.log("✅ Scrolled to Travel Insurance section", "", true);

        t01TravelInsuranceIntroPage.clickOnOnlineTravelInsuranceButton();
        loggerUtils.log("✅ Clicked on travel insurance button", "ClickTravelInsurance", true);

        basePage.switchToNewTab();
    }

    /**
     * Verifies that the page title is correct and the quote intro section is visible.
     * @throws Exception in case of unexpected page state
     */
    public void verifyTitle() throws Exception {
        String expectedTitle = TravelInsuranceData.EXPECTED_TRAVEL_INSURANCE_PAGE_TITLE;
        String actualTitle = basePage.getTitle();

        boolean titleOk = actualTitle.equals(expectedTitle);
        loggerUtils.log(
                titleOk
                        ? "✅ Title OK: " + actualTitle
                        : "❌ Wrong title. Got: " + actualTitle,
                "PageTitle",
                titleOk
        );

        boolean introVisible = t01TravelInsuranceIntroPage.isQuoteIntroSectionVisible();
        loggerUtils.log(
                introVisible
                        ? "✅ Quote intro section is visible"
                        : "❌ Intro section is not visible",
                "IntroVisible",
                introVisible
        );
    }

    /**
     * Clicks the continue button to proceed to the next step.
     */
    public void clickContinueButton() {
        t01TravelInsuranceIntroPage.clickOnContinueButton();
        loggerUtils.log("✅ Clicked successfully on Continue button", "ContinueButtonClicked", true);
    }
}
