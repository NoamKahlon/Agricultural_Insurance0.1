package tests;

import flows.SearchDocumentsAndFormsFlow;
import io.qameta.allure.Description;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;

public class SearchDocumentsAndFormsTests extends Base {

    private SearchDocumentsAndFormsFlow flow;

    @BeforeMethod
    public void setup() {
        this.flow = new SearchDocumentsAndFormsFlow(driver, basePage, searchDocumentsAndFormsPage, loggerUtils);
    }

    @Test(description = "TC-058 - Verify free text document search returns correct results")
    @Description("Navigate to Documents & Forms section, perform free text search, and verify result fields (document name, primary and secondary insurance)")
    public void test_TC058_FreeTextDocumentSearch() throws Exception {
        try {

            flow.scrollClickAndSelectCarSubject();

            searchDocumentsAndFormsPage.fillTextField();
            loggerUtils.log("✅ Entered free text search value", "FreeText", true, true);


            searchDocumentsAndFormsPage.clickSearch();
            loggerUtils.log("✅ Clicked Search button", "ClickSearch", true, true);


            List<WebElement> tableSearchResult = searchDocumentsAndFormsPage.getTableSearchResult();
            loggerUtils.log(tableSearchResult.isEmpty() ? "❌ No search results found. Table size is 0." : "✅ doucuments Load succsefuly Found " +
                    tableSearchResult.size() + " results", "", !tableSearchResult.isEmpty(), true);

            flow.verifyTableHeaders();

        } catch (Exception e) {
            loggerUtils.log("❌ TC-058 failed: " + e.getMessage(), "TC058_Failure", false, true);
            throw e;
        }
    }

    @Test(description = "TC-059 - Verify clearing document search fields clears the search results")
    @Description("Select subject and type, perform a search, verify results, then clear the form and validate that results are reset")
    public void test_TC059_DocumentSearchAndClear() throws Exception {
        try {

            flow.scrollClickAndSelectCarSubject();

            searchDocumentsAndFormsPage.selectGeneralFromTypeDropDownMenu();
            loggerUtils.log("✅ Selected General type", "TypeSelect", true, false);

            searchDocumentsAndFormsPage.clickSearch();
            loggerUtils.log("✅ Clicked Search button", "ClickSearch", true, true);

            searchDocumentsAndFormsPage.clickClear();
            loggerUtils.log("✅ Clicked Clear button", "ClickClear", true, false);

            flow.verifyTableIsEmpty();

        } catch (Exception e) {
            loggerUtils.log("❌ TC-059 failed: " + e.getMessage(), "TC059_Failure", false, true);
            throw e;
        }
    }
}
