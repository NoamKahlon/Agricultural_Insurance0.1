package tests;

import flows.SearchDocumentsAndFormsFlow;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchDocumentsAndFormsTests extends Base {

    private SearchDocumentsAndFormsFlow flow;

    @BeforeMethod
    public void setup() {
        this.flow = new SearchDocumentsAndFormsFlow(basePage, loggerUtils);
    }

    @Test(description = "TC-058 - Verify free text document search returns correct results")
    public void test_TC058_FreeTextDocumentSearch() throws Exception {
        try {
            flow.performFreeTextSearchAndVerifyResults();
        } catch (Exception e) {
            loggerUtils.log("❌ TC-058 failed: " + e.getMessage(), "TC058_Failure", false);
            throw e;
        }
    }

    @Test(description = "TC-059 - Verify clearing document search fields clears the search results")
    public void test_TC059_DocumentSearchAndClear() throws Exception {
        try {
            flow.performSearchAndClearAndVerifyTableIsEmpty();
        } catch (Exception e) {
            loggerUtils.log("❌ TC-059 failed: " + e.getMessage(), "TC059_Failure", false);
            throw e;
        }
    }
}
