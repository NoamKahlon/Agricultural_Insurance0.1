package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.SearchDocumentsAndFormsData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SearchDocumentsAndFormsPage {

    private BasePage basePage;

    private final By searchButton = By.cssSelector("button[type='submit']");
    private final By documentsAndFormsBtn = By.xpath("//img[@alt='חיפוש טפסים ומסמכים']/ancestor::a");
    private final By resetButtonLoc = By.cssSelector("#table_form > div:nth-child(4) > button:nth-child(2)");
    private final By primary = By.id("primary");
    private final By secondary = By.id("secondary");
    private final By listOfDocumentsSearched = By.cssSelector(".table__row:not(.row--hide)");
    private final By freeTextField = By.id("opentext");
    private final By headers = By.cssSelector("div.table__item");

    //get row data
   // private final By b = By.cssSelector("span.table__text");


    public SearchDocumentsAndFormsPage(BasePage basePage) {
        this.basePage = basePage;
    }

    public boolean scrollToDocumentsAndForms() throws Exception {return basePage.scrollToElement(documentsAndFormsBtn);}

    public void ClickDocumentsAndForms() throws Exception {basePage.forceClick(documentsAndFormsBtn);}

    public void selectSubjectCarFromDropDownMenu() {basePage.chooseValueFromDropDownMenu(primary, 1);}

    public void selectGeneralFromTypeDropDownMenu() {
        basePage.chooseValueFromDropDownMenu(secondary, 1);
    }

    public void clickSearch() {
        basePage.click(searchButton);
    }

    public void clickClear() {
        basePage.click(resetButtonLoc);
    }

    public void FillTextField() {basePage.sendKeys(freeTextField, SearchDocumentsAndFormsData.insurance);}

    public List<String> getWebElementListTexts(List<WebElement> webElementList) {
        return webElementList.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<WebElement> getSearchResultDocument() {
        List<WebElement> results = new ArrayList<>();
        try {
            results = basePage.getElements(listOfDocumentsSearched);
        } catch (Exception e) {}
        return results;
    }

    public List<WebElement> getTableSearchResult() {
        List<WebElement> SearchResultDocument = getSearchResultDocument();

        // 1 is header
        if (SearchResultDocument.size() <= 1) {
            return Collections.emptyList();
        }

        return SearchResultDocument;
    }

    public List<WebElement> getTableHeader(){
        List<WebElement> SearchResultDocument = getTableSearchResult();

        if(SearchResultDocument.isEmpty())
            return Collections.emptyList();

        WebElement headersElement = SearchResultDocument.get(0);

        return headersElement.findElements(headers);
    }
}
