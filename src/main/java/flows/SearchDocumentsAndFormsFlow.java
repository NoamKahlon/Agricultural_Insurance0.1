package flows;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;
import pages.SearchDocumentsAndFormsPage;
import utils.LoggerUtils;
import utils.SearchDocumentsAndFormsData;

import java.util.List;

public class SearchDocumentsAndFormsFlow {

    WebDriver driver;
    BasePage basePage;
    SearchDocumentsAndFormsPage searchDocumentsAndFormsPage;
    LoggerUtils loggerUtils;


    public SearchDocumentsAndFormsFlow(WebDriver driver, BasePage basePage, SearchDocumentsAndFormsPage searchDocumentsAndFormsPage, LoggerUtils loggerUtils) {
        this.driver = driver;
        this.basePage = basePage;
        this.searchDocumentsAndFormsPage = searchDocumentsAndFormsPage;
        this.loggerUtils = loggerUtils;
    }


    public void scrollClickAndSelectCarSubject() throws Exception {

        boolean didScroll = searchDocumentsAndFormsPage.scrollToDocumentsAndForms();
        loggerUtils.log(didScroll ? "✅ Scrolled to Documents and Forms section" : "❌ Failed to scroll to Documents and Forms section",
                "DocumentsSection", didScroll, true);

        searchDocumentsAndFormsPage.ClickDocumentsAndForms();
        loggerUtils.log("✅ Clicked on Documents and Forms", "DocumentsClick", true, true);

        searchDocumentsAndFormsPage.selectSubjectCarFromDropDownMenu();
        loggerUtils.log("✅ Selected subject", "SubjectSelect", true, true);

    }


    public void verifyTableHeaders() {
        // שליפת כותרות מהטבלה
        List<WebElement> headerTitles = searchDocumentsAndFormsPage.getTableHeader();
        List<String> actualHeaders = searchDocumentsAndFormsPage.getWebElementListTexts(headerTitles);

        // קבלת ערכים צפויים
        List<String> expectedHeaders = SearchDocumentsAndFormsData.expectedHeaders;

        // לוג על כל הכותרות שנמצאו בפועל
        String allHeadersStr = String.join(" , ", actualHeaders);

        // השוואה
        boolean headersMatch = actualHeaders.equals(expectedHeaders);

        // לוג תוצאת ההשוואה
        loggerUtils.log(
                headersMatch
                        ? "✅ Table headers match expected values: " + allHeadersStr
                        : "❌ Table headers do not match expected!\nExpected: " + expectedHeaders + "\nActual: " + actualHeaders,
                "TableHeaders", headersMatch, false);
    }


    public void verifyTableIsEmpty() {
        // שליפת כותרות מהטבלה
        List<WebElement> headerTitles = searchDocumentsAndFormsPage.getTableHeader();
        List<String> actualHeaders = searchDocumentsAndFormsPage.getWebElementListTexts(headerTitles);

        loggerUtils.log(
                actualHeaders.isEmpty()
                        ? "✅ Table headers are empty as expected"
                        : "❌ Table headers wasnt empty, expected to be empty. Actual: " + actualHeaders,
                "",  actualHeaders.isEmpty(), false);
        }
}
