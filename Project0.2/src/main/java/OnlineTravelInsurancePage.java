import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OnlineTravelInsurancePage {

    private WebDriver driver;
    private BasePage basePage;

    private final By travelInsuranceButton = By.xpath("//h3[contains(text(), 'חו\"ל')]/ancestor::div[contains(@class,'insurances__item')]//div[contains(@class, 'insurances__item-footer')]");

    public OnlineTravelInsurancePage(WebDriver driver, BasePage basePage) {
        this.driver = driver;
        this.basePage = basePage;
    }

    public void scrollToTravelInsuranceButton() throws Exception {
        basePage.scrollToElement(travelInsuranceButton);
    }

    public String getTitle() {
        return basePage.getTitle();
    }
}

