import io.qameta.allure.Description;
import org.testng.annotations.Test;
import java.util.List;

public class SearchDocumentsAndFormsTests extends Base {

    // TC-058 - UI - תוצאות חיפוש מסמכים בחיפוש חופשי
    @Test(description = "TC-058 - Verify free text document search returns correct results")
    @Description("Navigate to Documents & Forms section, perform free text search, and verify result fields (document name, primary and secondary insurance)")
    public void test_TC058_FreeTextDocumentSearch() throws Exception {
        try {
            searchDocumentsAndForms.scrollAndClickToDocumentsAndForms();
            smartLog("✅ Scrolled and clicked Documents and Forms", "DocumentsSection", true, LogMode.SOFT);

            searchDocumentsAndForms.FillFreeTextField();
            smartLog("✅ Entered free text search value", "FreeText", true, LogMode.SOFT);

            searchDocumentsAndForms.clickSearch();
            smartLog("✅ Clicked Search button", "ClickSearch", true, LogMode.SOFT);

            List<String> results = searchDocumentsAndForms.getSearchRowTexts();

            if (results.size() >= 3) {
                assertTextEquals(results.get(0), "בקשה לביטול פוליסה", "Document Name");
                assertTextEquals(results.get(1), "רכב", "Primary Insurance");
                assertTextEquals(results.get(2), "כללי", "Secondary Insurance");
            } else {
                smartLog("❌ Not enough results found in search (less than 3)", "SearchResults", false, LogMode.HARD);
            }

        } catch (Exception e) {
            smartLog("❌ TC-058 failed: " + e.getMessage(), "TC058_Failure", false, LogMode.HARD);
            throw e;
        } finally {
            assertAllSoft();
        }
    }

    // TC-059 - UI - ניקוי תוצאות חיפוש מסמכים
    @Test(description = "TC-059 - Verify clearing document search fields clears the search results")
    @Description("Select subject and type, perform a search, verify results, then clear the form and validate that results are reset")
    public void test_TC059_DocumentSearchAndClear() throws Exception {
        try {
            searchDocumentsAndForms.scrollAndClickToDocumentsAndForms();
            smartLog("✅ Scrolled and clicked Documents and Forms", "DocumentsSection", true, LogMode.SOFT);

            searchDocumentsAndForms.selectSubjectDropDownMenu();
            smartLog("✅ Selected subject", "SubjectSelect", true, LogMode.SOFT);

            searchDocumentsAndForms.selectTypeDropDownMenu();
            smartLog("✅ Selected type", "TypeSelect", true, LogMode.SOFT);

            searchDocumentsAndForms.clickSearch();
            smartLog("✅ Clicked Search button", "ClickSearch", true, LogMode.SOFT);

            List<String> results = searchDocumentsAndForms.getSearchRowTexts();
            assertTextEquals(results.get(0), "בקשה לביטול פוליסה", "Document Name");
            assertTextEquals(results.get(1), "רכב", "Primary Insurance");
            assertTextEquals(results.get(2), "כללי", "Secondary Insurance");

            searchDocumentsAndForms.clickClear();
            smartLog("✅ Clicked Clear button", "ClickClear", true, LogMode.SOFT);

            List<String> cleared = searchDocumentsAndForms.getSearchRowTexts();
            assertTextEquals(cleared.get(0), "", "Document Name Cleared");
            assertTextEquals(cleared.get(1), "", "Primary Insurance Cleared");
            assertTextEquals(cleared.get(2), "", "Secondary Insurance Cleared");

        } catch (Exception e) {
            smartLog("❌ TC-059 failed: " + e.getMessage(), "TC059_Failure", false, LogMode.HARD);
            throw e;
        } finally {
            assertAllSoft();
        }
    }
}
