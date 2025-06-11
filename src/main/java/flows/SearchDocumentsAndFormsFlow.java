package flows;

import org.openqa.selenium.WebElement;
import pages.BasePage;
import pages.SearchDocumentsAndFormsPage;
import utils.LoggerUtils;
import utils.SearchDocumentsAndFormsData;

import java.util.List;

/**
 * Flow class for handling the 'Documents and Forms' search functionality.
 * Covers scrolling, selecting options, performing searches, clearing, and result validation.
 */
public class SearchDocumentsAndFormsFlow {

    // ========== Members ==========
    private final BasePage basePage;
    private final SearchDocumentsAndFormsPage searchDocumentsAndFormsPage;
    private final LoggerUtils loggerUtils;

    // ========== Constructor ==========
    public SearchDocumentsAndFormsFlow(BasePage basePage, LoggerUtils loggerUtils) {
        this.basePage = basePage;
        this.loggerUtils = loggerUtils;
        this.searchDocumentsAndFormsPage = new SearchDocumentsAndFormsPage(basePage);
    }

    // ========== Actions ==========

    /**
     * Scrolls to the 'Documents and Forms' section, clicks it,
     * and selects the 'Car' subject from the dropdown.
     */
    public void scrollClickAndSelectCarSubject() throws Exception {
        boolean didScroll = searchDocumentsAndFormsPage.scrollToDocumentsAndForms();
        loggerUtils.log(
                didScroll
                        ? "✅ Scrolled to Documents and Forms section"
                        : "❌ Failed to scroll to Documents and Forms section",
                "DocumentsSection",
                didScroll
        );

        searchDocumentsAndFormsPage.clickDocumentsAndForms();
        loggerUtils.log("✅ Clicked on Documents and Forms", "DocumentsClick", true);

        searchDocumentsAndFormsPage.selectSubjectCarFromDropDownMenu();
        loggerUtils.log("✅ Selected subject: Car", "SubjectSelect", true);
    }

    /**
     * Fills a free-text search, executes it, and verifies the results table and headers.
     */
    public void performFreeTextSearchAndVerifyResults() throws Exception {
        scrollClickAndSelectCarSubject();

        searchDocumentsAndFormsPage.fillTextField();
        loggerUtils.log("✅ Entered free text search value", "FreeText", true);

        searchDocumentsAndFormsPage.clickSearch();
        loggerUtils.log("✅ Clicked Search button", "ClickSearch", true);

        List<WebElement> tableSearchResult = searchDocumentsAndFormsPage.getTableSearchResult();
        loggerUtils.log(
                tableSearchResult.isEmpty()
                        ? "❌ No search results found. Table size is 0."
                        : "✅ Documents loaded successfully. Found " + tableSearchResult.size() + " results",
                "TableResult",
                !tableSearchResult.isEmpty()
        );

        verifyTableHeaders();
    }

    /**
     * Performs a search using type selection, then clears the form
     * and verifies that the table is empty.
     */
    public void performSearchAndClearAndVerifyTableIsEmpty() throws Exception {
        scrollClickAndSelectCarSubject();

        searchDocumentsAndFormsPage.selectGeneralFromTypeDropDownMenu();
        loggerUtils.log("✅ Selected General type", "TypeSelect", true);

        searchDocumentsAndFormsPage.clickSearch();
        loggerUtils.log("✅ Clicked Search button", "ClickSearch", true);

        searchDocumentsAndFormsPage.clickClear();
        loggerUtils.log("✅ Clicked Clear button", "ClickClear", true);

        verifyTableIsEmpty();
    }

    // ========== Verifications ==========

    /**
     * Verifies that the table headers match the expected values.
     */
    public void verifyTableHeaders() {
        List<WebElement> headerElements = searchDocumentsAndFormsPage.getTableHeader();
        List<String> actualHeaders = searchDocumentsAndFormsPage.getWebElementListTexts(headerElements);
        List<String> expectedHeaders = SearchDocumentsAndFormsData.expectedHeaders;

        boolean headersMatch = actualHeaders.equals(expectedHeaders);
        loggerUtils.log(
                headersMatch
                        ? "✅ Table headers match expected values: " + String.join(" , ", actualHeaders)
                        : "❌ Table headers do not match expected!\nExpected: " + expectedHeaders + "\nActual: " + actualHeaders,
                "TableHeaders",
                headersMatch
        );
    }

    /**
     * Verifies that the table headers are empty after clearing the search.
     */
    public void verifyTableIsEmpty() {
        List<WebElement> headerElements = searchDocumentsAndFormsPage.getTableHeader();
        List<String> actualHeaders = searchDocumentsAndFormsPage.getWebElementListTexts(headerElements);

        boolean isEmpty = actualHeaders.isEmpty();
        loggerUtils.log(
                isEmpty
                        ? "✅ Table headers are empty as expected"
                        : "❌ Table headers weren't empty. Actual: " + actualHeaders,
                "ClearResultVerification",
                isEmpty
        );
    }
}
