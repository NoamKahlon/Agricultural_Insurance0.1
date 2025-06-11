package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class T010_TravelInsuranceSpecialCoveragesPage {

    private final BasePage basePage;

    //////////// LOCATORS ////////////
    private final By titleDiv = By.cssSelector("div.title");
    private final By extremeSportsButton = By.xpath("//button[.//div[text()='ספורט אתגרי']]");
    private final By customerName = By.cssSelector("label[for='ExtremeSports_0']");
    private final By addCoverageButton = By.xpath("//button[contains(., 'הוספת כיסוי')]");

    //////////// CONSTRUCTOR ////////////
    public T010_TravelInsuranceSpecialCoveragesPage(BasePage basePage) {
        this.basePage = basePage;
    }

    //////////// ACTIONS ////////////
    public void clickExtremeSportsButton() {
        basePage.click(extremeSportsButton);
    }

    public void clickCustomerName() {
        basePage.click(customerName);
    }

    public void clickAddCoverage() {
        basePage.click(addCoverageButton);
    }

    //////////// GETTERS ////////////
    public List<String> getActualTitles() {
        return basePage.getElements(titleDiv).stream()
                .map(WebElement::getText)
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
