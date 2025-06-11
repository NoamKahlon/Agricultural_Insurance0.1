package pages;

import org.openqa.selenium.By;

public class T011_TravelInsuranceHealthDeclarationPage {

    //////////// BASE ////////////
    private final BasePage basePage;

    //////////// LOCATORS ////////////
    private final By noOptionButton = By.id("לא");

    //////////// CONSTRUCTOR ////////////
    public T011_TravelInsuranceHealthDeclarationPage(BasePage basePage) {
        this.basePage = basePage;
    }

    //////////// ACTIONS ////////////
    public void clickNoButton() {
        basePage.click(noOptionButton);
    }
}
