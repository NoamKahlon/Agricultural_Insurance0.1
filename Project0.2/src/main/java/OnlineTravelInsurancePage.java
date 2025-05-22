//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//
//public class OnlineTravelInsurancePage {
//
//    private WebDriver driver;
//    private BasePage basePage;
//
//    private By TravelInsuranceButton = By.id("abroadplus");
//   // private By continueButton = By.cssSelector("button.procceed");
//    By continueButton = By.xpath("//button[div[text()='להמשיך']]");
//    private By letsContinueButton = By.cssSelector("div[data-f='procceed']");
//    private By travelInsuranceButton = By.xpath("//h3[contains(text(), 'חו\"ל')]/ancestor::div[contains(@class,'insurances__item')]//div[contains(@class, 'insurances__item-footer')]");
//    private By firstTimeOptionButton = By.cssSelector("div[data-f='first-time-no']");
//    private By alreadyPurchasedOptionButton = By.cssSelector("div[data-f='already-purchased']");
//    private final By previousInsuranceQuestion = By.xpath("//h1[contains(text(),'רכשת ביטוח')]");
//    private final By passengersFromIsraelQuestion = By.xpath("//h1[contains(text(),'האם כל הנוסעים יוצאים מישראל')]");
//    private final By passengersFromIsraelYesOption = By.cssSelector("div[data-f='all-passengers-from-israel-yes']");
//    private final By passengersFromIsraelNoOption = By.cssSelector("div[data-f='all-passengers-from-israel-no']");
//
//
//    private String OnlineTravelInsuranceTitle = "ביטוח חקלאי - ביטוח נסיעות לחו״ל";
//    private String previousquestionInsuranceHeader = "האם רכשת ביטוח בעבר?";
//
//
//
//    public OnlineTravelInsurancePage(WebDriver driver, BasePage basePage) {
//        this.driver = driver;
//        this.basePage = basePage;
//    }
//
//
//    public void clickOnContinueButton() {
//        WebElement button = basePage.getElement(By.xpath("//button[div[text()='להמשיך']]"));
//        System.out.println("Location: " + button.getLocation());
//        System.out.println("Size: " + button.getSize());
//
//        basePage.scrollToElementSafe(continueButton); // לוודא שהוא בטווח גלילה
//        basePage.forceClick(continueButton);
//    }
//
//
//    public boolean isLetsContinueButtonDisplayed() throws Exception {
//        return basePage.isDisplayed(letsContinueButton);
//    }
//
//
//    public void scrollToTravelInsuranceButton() throws Exception {
//        basePage.scrollToElementForce(TravelInsuranceButton); // או לפי טקסט כמו קודם
//    }
//
//    public void clickOnOnlineTravelInsuranceButton() {
//        basePage.click(TravelInsuranceButton);
//    }
//
//
//    public void scrollToLetsContinueButton() throws Exception {
//       // basePage.scrollToElement(letsContinueButton);
//        basePage.scrollToElementForce(letsContinueButton); // או לפי טקסט כמו קודם
//    }
//
//    public void clickOnLetsContinueButton() {
//        By letsContinueButton = By.cssSelector("button.procceed");
//        //basePage.click(letsContinueButton);
//        basePage.forceClick(letsContinueButton);
//    }
//
//    public String getPreviousInsuranceQuestionText() {
//        return basePage.getText(previousInsuranceQuestion);
//    }
//
//    public boolean arePreviousInsuranceOptionsDisplayed() {
//        boolean isFirstTimeVisible = basePage.isDisplayedSafe(firstTimeOptionButton);
//        boolean isAlreadyPurchasedVisible = basePage.isDisplayedSafe(alreadyPurchasedOptionButton);
//        return isFirstTimeVisible && isAlreadyPurchasedVisible;
//    }
//
//
//    public String getPassengersFromIsraelQuestionText() {
//        return basePage.getText(passengersFromIsraelQuestion);
//    }
//
//    public boolean arePassengersFromIsraelOptionsDisplayed() {
//        boolean yesVisible = basePage.isDisplayedSafe(passengersFromIsraelYesOption);
//        boolean noVisible = basePage.isDisplayedSafe(passengersFromIsraelNoOption);
//        return yesVisible && noVisible;
//    }
//
//
//}
//
// OnlineTravelInsurancePage.java

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OnlineTravelInsurancePage {

    private WebDriver driver;
    private BasePage basePage;

    public OnlineTravelInsurancePage(WebDriver driver, BasePage basePage) {
        this.driver = driver;
        this.basePage = basePage;
    }

    private final By travelInsuranceButton = By.id("abroadplus");
    private final By letsContinueButton = By.cssSelector("button.procceed");
    private final By quoteIntroBlock = By.xpath("//div[contains(text(),'קיבלת הנחה בלעדית לרכישה און ליין')]");
    private final By previousInsuranceQuestion = By.xpath("//h1[contains(text(),'רכשת ביטוח')]");
    private final By passengersFromIsraelQuestion = By.xpath("//h1[contains(.,'האם כל הנוסעים יוצאים מישראל')]");
    private final By passengersFromIsraelYes = By.cssSelector("div[data-f='yes']");
    private final By passengersFromIsraelNo = By.cssSelector("div[data-f='no']");
    private final By firstTimeOptionButton = By.cssSelector("div[data-f='first-time-no']");
    private final By alreadyPurchasedOptionButton = By.cssSelector("div[data-f='already-purchased']");
    private final By yesOption = By.xpath("//div[@data-f='yes']");
    private final By noOption = By.xpath("//div[@data-f='no']");

    private final By purchaseBlockedTitle = By.xpath("//h1[contains(text(),'לצערנו לא נוכל להמשיך')]");
    private final By backToHomeButton = By.xpath("//button[contains(.,'בחזרה לעמוד הבית')]");


    public void scrollToTravelInsuranceButton() throws Exception {
        basePage.scrollToElementForce(travelInsuranceButton);
    }

    public void clickOnOnlineTravelInsuranceButton() {
        basePage.click(travelInsuranceButton);
    }

    public void clickOnContinueButton() {basePage.forceClick(letsContinueButton); }

    public void scrollToLetsContinueButton() throws Exception {basePage.scrollToElementSafe(letsContinueButton);}

    public void clickOnLetsContinueButton() {
        basePage.forceClick(letsContinueButton);
    }

    public boolean isQuoteIntroSectionVisible() {
        return basePage.isDisplayedSafe(quoteIntroBlock);
    }

    public String getPreviousInsuranceQuestionText() {
        return basePage.getTextSafe(previousInsuranceQuestion);
    }


    public boolean isFirstTimeOptionDisplayed() {
        return basePage.isDisplayedSafe(firstTimeOptionButton);
    }

    public boolean isAlreadyPurchasedOptionDisplayed() {return basePage.isDisplayedSafe(alreadyPurchasedOptionButton);}

    public String getPassengersFromIsraelQuestionText() {
        return basePage.getTextSafe(passengersFromIsraelQuestion);
    }

    public boolean isPassengersFromIsraelYesDisplayed() {
        return basePage.isDisplayedSafe(passengersFromIsraelYes);
    }

    public boolean isPassengersFromIsraelNoDisplayed() {
        return basePage.isDisplayedSafe(passengersFromIsraelNo);
    }

    public void clickFirstTimeOptionButton() {basePage.click(firstTimeOptionButton); }

    public void clickAlreadyPurchasedOptionButton() {basePage.click(alreadyPurchasedOptionButton); }

    public void clickPassengersFromIsraelYesOption() {basePage.click(yesOption);}

    public void clickPassengersFromIsraelNoOption() {basePage.click(noOption);}

    public boolean isStopProcessErrorDisplayed() {return basePage.isDisplayedSafe(purchaseBlockedTitle);}

    public boolean isBackToHomeButtonDisplayed() {return basePage.isDisplayedSafe(backToHomeButton);}


}