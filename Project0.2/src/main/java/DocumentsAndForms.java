import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DocumentsAndForms {

    private WebDriver driver;
    private BasePage basePage;

    private final By searchButton = By.cssSelector("button[type='submit']");
    private final By documentsAndFormsBtn = By.xpath("//img[@alt='חיפוש טפסים ומסמכים']/ancestor::a");
    private final By resetButtonLoc = By.cssSelector("#table_form > div:nth-child(4) > button:nth-child(2)");
    private final By primary = By.id("primary");
    private final By secondary = By.id("secondary");
    private final By list = By.cssSelector(".table__row:not(.row--hide)");
    private final By freeTextField = By.id("opentext");

    public DocumentsAndForms(WebDriver driver, BasePage basePage) {
        this.driver = driver;
        this.basePage = basePage;
    }

    public void scrollAndClickToDocumentsAndForms() throws Exception {
        basePage.scrollToElement(documentsAndFormsBtn);
        basePage.forceClick(documentsAndFormsBtn);
    }

    public void selectSubjectDropDownMenu() {
        basePage.chooseValueFromDropDownMenu(primary, 1);
    }

    public void selectTypeDropDownMenu() {
        basePage.chooseValueFromDropDownMenu(secondary, 1);
    }

    public void clickSearch() {
        basePage.click(searchButton);
    }

    public void clickClear() {
        basePage.click(resetButtonLoc);
    }

    public List<WebElement> getVisibleSearchResultRows() {
        List<WebElement> rows = basePage.getElements(list);
        if (rows.size() <= 1) {
            return Collections.emptyList();
        }
        WebElement firstDataRow = rows.get(1);
        List<WebElement> columns = firstDataRow.findElements(By.cssSelector("span.table__text"));
        if (columns.size() < 3) {
            return Collections.emptyList();
        }
        return columns;
    }

    public List<String> getSearchRowTexts() {
        List<WebElement> columns = getVisibleSearchResultRows();
        return columns.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void FillFreeTextField() {
        basePage.sendKeys(freeTextField,"ביטוח");
    }
}
