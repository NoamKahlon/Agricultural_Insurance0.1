package flows;

import pages.BasePage;
import pages.T04_TravelInsuranceAlreadyPurchasedBeforePage;
import utils.LoggerUtils;

public class T04_TravelInsuranceAlreadyPurchasedBeforeFlow {

    // ========= Members =========
    private final BasePage basePage;
    private final LoggerUtils loggerUtils;
    private final T04_TravelInsuranceAlreadyPurchasedBeforePage t04TravelInsuranceAlreadyPurchasedBeforePage;
    private final T01_TravelInsuranceIntroFlow introFlow;

    // ========= Constructor =========
    public T04_TravelInsuranceAlreadyPurchasedBeforeFlow(BasePage basePage, LoggerUtils loggerUtils) {
        this.basePage = basePage;
        this.loggerUtils = loggerUtils;
        this.t04TravelInsuranceAlreadyPurchasedBeforePage = new T04_TravelInsuranceAlreadyPurchasedBeforePage(basePage);
        this.introFlow = new T01_TravelInsuranceIntroFlow(basePage, loggerUtils);
    }

    // ========= Actions =========
    public void navigateToAlreadyPurchasedBeforeStep() throws Exception {
        introFlow.navigateToIntroPage();
        introFlow.clickContinueButton();
        introFlow.clickContinueButton();
        t04TravelInsuranceAlreadyPurchasedBeforePage.clickAlreadyPurchasedOption();
        loggerUtils.log("✅ Clicked on 'Already Purchased' option", "clicked_already_purchased", true);
    }

    // ========= Verifications =========
    public void verifyAlreadyPurchasedInsuranceDetails() {
        boolean isHappyTextDisplayed = t04TravelInsuranceAlreadyPurchasedBeforePage.isHappyTextDisplayed();
        loggerUtils.log(
                isHappyTextDisplayed
                        ? "✅ 'Happy To See You Again' text is visible"
                        : "❌ 'Happy To See You Again' text not found",
                "verify_happy_text", isHappyTextDisplayed
        );

        boolean isPhoneDisplayed = t04TravelInsuranceAlreadyPurchasedBeforePage.isPhoneFieldDisplayed();
        loggerUtils.log(
                isPhoneDisplayed
                        ? "✅ Phone field is visible"
                        : "❌ Phone field not visible",
                "verify_phone_field", isPhoneDisplayed
        );

        boolean isIdDisplayed = t04TravelInsuranceAlreadyPurchasedBeforePage.isIdFieldDisplayed(); // ודא שהלוקייטור נכון
        loggerUtils.log(
                isIdDisplayed
                        ? "✅ ID field is visible"
                        : "❌ ID field not visible",
                "verify_id_field", isIdDisplayed
        );
    }
}
