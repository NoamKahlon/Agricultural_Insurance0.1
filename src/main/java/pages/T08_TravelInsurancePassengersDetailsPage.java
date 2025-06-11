package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class T08_TravelInsurancePassengersDetailsPage {

    private final BasePage basePage;

    // === Locators ===
    private final By idField = By.id("idNumber_0");
    private final By phoneField = By.id("cellphoneNumber_0");
    private final By firstNameHebrew = By.id("firstNameHeb_0");
    private final By firstNameEnglish = By.id("firstNameEng_0");
    private final By lastNameHebrew = By.id("lastNameHeb_0");
    private final By lastNameEnglish = By.id("lastNameEng_0");
    private final By birthDateField = By.id("dateOfBirth_0");
    private final By emailField = By.id("emailAddressLabel_0");
    private final By addPassengerField = By.cssSelector(".passenger-add");
    private final By genderField = By.cssSelector("div.sc-bjUoiL");
    private final By continueButton = By.cssSelector("button.sc-papXJ.jyxUhb.procceed");
    private final By backButton = By.cssSelector("button.sc-papXJ.iGkwwW.goback");
    private final By maleGenderButton = By.id("זכר");
    private final By insuranceFinalPrice = By.cssSelector(".finalPriceValue");

    // === Constructor ===
    public T08_TravelInsurancePassengersDetailsPage(BasePage basePage) {
        this.basePage = basePage;
    }

    // === Actions ===
    public void selectMaleGender() {
        basePage.click(maleGenderButton);
    }

    public void enterIdNumber(String id) {
        basePage.sendKeys(idField, id);
    }

    public void enterPhoneNumber(String phone) {
        basePage.sendKeys(phoneField, phone);
    }

    public void enterFirstNameHebrew(String name) {
        basePage.sendKeys(firstNameHebrew, name);
    }

    public void enterLastNameHebrew(String name) {
        basePage.sendKeys(lastNameHebrew, name);
    }

    public void enterFirstNameEnglish(String name) {
        basePage.sendKeys(firstNameEnglish, name);
    }

    public void enterLastNameEnglish(String name) {
        basePage.sendKeys(lastNameEnglish, name);
    }

    public void enterBirthDate(String date) {
        basePage.sendKeys(birthDateField, date);
    }

    public void enterEmail(String email) {
        basePage.sendKeys(emailField, email);
        basePage.sendKeys(emailField, Keys.TAB);
    }

    // === Validations ===
    public boolean isIdFieldVisible() {
        return basePage.isDisplayed(idField);
    }

    public boolean isPhoneFieldVisible() {
        return basePage.isDisplayed(phoneField);
    }

    public boolean isFirstNameHebrewVisible() {
        return basePage.isDisplayed(firstNameHebrew);
    }

    public boolean isFirstNameEnglishVisible() {
        return basePage.isDisplayed(firstNameEnglish);
    }

    public boolean isLastNameHebrewVisible() {
        return basePage.isDisplayed(lastNameHebrew);
    }

    public boolean isLastNameEnglishVisible() {
        return basePage.isDisplayed(lastNameEnglish);
    }

    public boolean isBirthDateFieldVisible() {
        return basePage.isDisplayed(birthDateField);
    }

    public boolean isEmailFieldVisible() {
        return basePage.isDisplayed(emailField);
    }

    public boolean isAddPassengerFieldVisible() {
        return basePage.isDisplayed(addPassengerField);
    }

    public boolean isGenderFieldVisible() {
        return basePage.isDisplayed(genderField);
    }

    public boolean isContinueButtonVisible() {
        return basePage.isDisplayed(continueButton);
    }

    public boolean isBackButtonVisible() {
        return basePage.isDisplayed(backButton);
    }

    public boolean isInsuranceFinalPriceVisible() {
        return basePage.isDisplayed(insuranceFinalPrice);
    }
}
