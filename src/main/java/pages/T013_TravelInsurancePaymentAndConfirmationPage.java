package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class T013_TravelInsurancePaymentAndConfirmationPage {

    private final BasePage basePage;

    //////////// LOCATORS ////////////
    private final By cityField = By.id("payerCityName");
    private final By streetField = By.id("payerStreetName");
    private final By houseNumberField = By.id("payerHouseNumber");
    private final By apartmentNumberField = By.id("payerApartmentNumber");
    private final By postalCodeField = By.id("payerZipCode");

    private final By cardNumberField = By.id("card-number");
    private final By cardHolderFirstNameField = By.id("payerFirstName");
    private final By cardHolderLastNameField = By.id("payerLastName");
    private final By cardHolderEmailField = By.id("deliveryDetails");
    private final By cardHolderIdField = By.id("personal-id");
    private final By numberOfPaymentsField = By.id("numberOfPayments");
    private final By cardExpiryYearField = By.id("expYear");
    private final By cardExpiryMonthField = By.id("expMonth");

    private final By termsAndConditionsCheckbox = By.xpath("//label[@for='acceptTerms']");
    private final By insuranceDeclarationCheckbox = By.xpath("//label[@for='acceptInsuredClient']");

    private final By iframe = By.id("creditFrame");
    private final By payButton = By.id("cg-submit-btn");
    private final By backButton = By.cssSelector("button.sc-papXJ.iGkwwW.goback");

    //////////// CONSTRUCTOR ////////////
    public T013_TravelInsurancePaymentAndConfirmationPage(BasePage basePage) {
        this.basePage = basePage;
    }

    //////////// ACTIONS ////////////
    public List<String> getContactSectionTitles() {
        return basePage.getElements(By.cssSelector(".sc-ksZaOG.bxYabq.col-xs-12.col-lg-6"))
                .stream()
                .map(WebElement::getText)
                .map(String::trim)
                .collect(Collectors.toList());
    }

    //////////// VERIFICATIONS - CREDIT CARD DETAILS ////////////
    public boolean verifyCardNumberFieldIsDisplayed() {
        return basePage.isDisplayed(cardNumberField);
    }

    public boolean verifyCardHolderFirstNameFieldIsDisplayed() {
        return basePage.isDisplayed(cardHolderFirstNameField);
    }

    public boolean verifyCardHolderLastNameFieldIsDisplayed() {
        return basePage.isDisplayed(cardHolderLastNameField);
    }

    public boolean verifyCardHolderEmailFieldIsDisplayed() {
        return basePage.isDisplayed(cardHolderEmailField);
    }

    public boolean verifyCardHolderIdFieldIsDisplayed() {
        return basePage.isDisplayed(cardHolderIdField);
    }

    public boolean verifyNumberOfPaymentsFieldIsDisplayed() {
        return basePage.isDisplayed(numberOfPaymentsField);
    }

    public boolean verifyCardExpiryYearFieldIsDisplayed() {
        return basePage.isDisplayed(cardExpiryYearField);
    }

    //////////// VERIFICATIONS - ADDRESS & CHECKBOXES ////////////
    public boolean verifyCityFieldIsDisplayed() {
        return basePage.isDisplayed(cityField);
    }

    public boolean verifyStreetFieldIsDisplayed() {
        return basePage.isDisplayed(streetField);
    }

    public boolean verifyHouseNumberFieldIsDisplayed() {
        return basePage.isDisplayed(houseNumberField);
    }

    public boolean verifyApartmentNumberFieldIsDisplayed() {
        return basePage.isDisplayed(apartmentNumberField);
    }

    public boolean verifyPostalCodeFieldIsDisplayed() {
        return basePage.isDisplayed(postalCodeField);
    }

    public boolean verifyInsuranceDeclarationCheckboxIsDisplayed() {
        return basePage.isDisplayed(insuranceDeclarationCheckbox);
    }

    public boolean verifyTermsAndConditionsCheckboxIsDisplayed() {
        return basePage.isDisplayed(termsAndConditionsCheckbox);
    }

    //////////// VERIFICATIONS - BUTTONS ////////////
    public boolean verifyPayButton() {
        return basePage.isDisplayed(payButton);
    }

    public boolean verifyBackButton() {
        return basePage.isDisplayed(backButton);
    }

    //////////// IFRAME HANDLING ////////////
    public void switchToIframe() {
        basePage.switchToIframe(iframe);
    }

    public void switchToDefaultContent() {
        basePage.switchToDefaultContent();
    }
}