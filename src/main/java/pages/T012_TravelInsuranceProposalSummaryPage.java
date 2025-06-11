package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class T012_TravelInsuranceProposalSummaryPage {

    private final BasePage basePage;

    //////////// LOCATORS ////////////
    private final By passengersNumber = By.xpath("//div[@class='orderInfoBox'][.//span[contains(text(),'כמות נוסעים')]]/h4");
    private final By specificPassengerCoverage = By.xpath("//div[contains(text(),'כיסויים נוספים עבור נוסע.ת') or contains(text(),'נוסעים ספציפיים')]");
    private final By additionalCoversPassengers = By.xpath("//div[contains(@class,'additionalCoversPassengers')]//div[contains(@class,'accordion-content')]");
    private final By allPassengerCoverage = By.cssSelector("div.sc-breuTD.eyjbsn div div.sc-hAZoDl.fJryBF.col-md-6.col-lg-6");
    private final By accordionParagraphs = By.cssSelector(".accordion-content .accordion-section p");

    //////////// CONSTRUCTOR ////////////
    public T012_TravelInsuranceProposalSummaryPage(BasePage basePage) {
        this.basePage = basePage;
    }

    //////////// GETTERS – BY SECTION ////////////

    public String getPassengersNumber() {
        return getOrderInfoByIndex(0);
    }

    public String getDestination() {
        return getOrderInfoByIndex(1);
    }

    public String getDates() {
        return getOrderInfoByIndex(2);
    }

    public List<String> getAllPassengerCoverages() {
        return getTextListFromElements(allPassengerCoverage);
    }

    public String getSpecificPassengerCoveragesTexts() {
        List<WebElement> paragraphs = basePage.getElements(accordionParagraphs);
        StringBuilder allText = new StringBuilder();
        for (int i = 0; i < paragraphs.size(); i++) {
            String text = paragraphs.get(i).getText().trim();
            allText.append(i + 1).append(", ").append(text).append("\n");
        }
        return allText.toString().trim();
    }

    //////////// INTERNAL UTILITIES ////////////

    private String getOrderInfoByIndex(int index) {
        List<WebElement> boxes = basePage.getElements(By.className("orderInfoBox"));
        if (boxes.size() <= index) {
            throw new RuntimeException("❌ No orderInfoBox found at index " + index);
        }
        return boxes.get(index).findElement(By.tagName("h4")).getText().trim();
    }

    private List<String> getTextListFromElements(By locator) {
        List<WebElement> elements = basePage.getElements(locator);
        List<String> texts = new ArrayList<>();
        for (WebElement el : elements) {
            String text = el.getText().trim();
            if (!text.isEmpty()) {
                texts.add(text);
            }
        }
        return texts;
    }
}
