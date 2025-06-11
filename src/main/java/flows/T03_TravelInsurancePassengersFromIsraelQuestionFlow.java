package flows;

import pages.BasePage;
import pages.T03_TravelInsurancePassengersFromIsraelQuestionPage;
import utils.LoggerUtils;

public class T03_TravelInsurancePassengersFromIsraelQuestionFlow {

    // ========= Members =========
    private final BasePage basePage;
    private final LoggerUtils loggerUtils;
    private final T03_TravelInsurancePassengersFromIsraelQuestionPage passengersFromIsraelQuestionPage;
    private final T02_TravelInsurancePurchasedBeforeQuestionFlow purchasedBeforeQuestionFlow;

    // ========= Constructor =========
    public T03_TravelInsurancePassengersFromIsraelQuestionFlow(BasePage basePage, LoggerUtils loggerUtils) {
        this.basePage = basePage;
        this.loggerUtils = loggerUtils;
        this.passengersFromIsraelQuestionPage = new T03_TravelInsurancePassengersFromIsraelQuestionPage(basePage);
        this.purchasedBeforeQuestionFlow = new T02_TravelInsurancePurchasedBeforeQuestionFlow(basePage, loggerUtils);
    }

    // ========= Actions =========
    public void navigateToPassengersFromIsraelQuestionStep() throws Exception {
        purchasedBeforeQuestionFlow.navigateToPurchasedInsuranceBeforeQuestionStep();
        purchasedBeforeQuestionFlow.clickFirstTimeButton();
    }

    public void clickPassengersFromIsraelOption() {
        passengersFromIsraelQuestionPage.clickYesButton();
        loggerUtils.log("✅ Clicked on 'Yes' - all passengers are from Israel", "clicked_yes_passengers", true);
    }

    // ========= Verifications =========
    public void verifyPassengersFromIsraelQuestion() throws Exception {
        String questionText = passengersFromIsraelQuestionPage.getQuestionText();
        boolean isQuestionCorrect = questionText.contains("יוצאים מישראל");

        loggerUtils.log(
                isQuestionCorrect
                        ? "✅ Found correct question: " + questionText
                        : "❌ Unexpected question text: " + questionText,
                "verify_passengers_question",
                isQuestionCorrect
        );

        boolean isYesOptionVisible = passengersFromIsraelQuestionPage.isYesOptionDisplayed();
        loggerUtils.log(
                isYesOptionVisible
                        ? "✅ 'Yes' option is visible"
                        : "❌ 'Yes' option is NOT visible",
                "",
                isYesOptionVisible
        );

        boolean isNoOptionVisible = passengersFromIsraelQuestionPage.isNoOptionDisplayed();
        loggerUtils.log(
                isNoOptionVisible
                        ? "✅ 'No' option is visible"
                        : "❌ 'No' option is NOT visible",
                "",
                isNoOptionVisible
        );
    }
}
