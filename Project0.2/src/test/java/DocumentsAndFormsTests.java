import io.qameta.allure.Description;
import org.testng.annotations.Test;
import java.util.List;

public class DocumentsAndFormsTests extends Base {

    // TC-058
    @Test(description = "Verify document search functionality and result display on Bituach Haklai site")
    @Description("Navigates to the site, accesses document search, enters query, and verifies expected result fields")
    public void testDocumentSearchAndResultDisplay() throws Exception {
        try {


            documentsAndForms.scrollAndClickToDocumentsAndForms();
            smartLog("✅ Scrolled and clicked Documents and Forms", "Documents and Forms", true, LogMode.SOFT);

            documentsAndForms.FillFreeTextField();
            smartLog("✅ Filled free text search field", "FreeText", true, LogMode.SOFT);

            documentsAndForms.clickSearch();
            smartLog("✅ Clicked search button", "Search", true, LogMode.SOFT);

            List<String> results = documentsAndForms.getSearchRowTexts();
            if (results.size() >= 3) {
                assertTextEquals(results.get(0), "בקשה לביטול פוליסה", "Check document name");
                assertTextEquals(results.get(1), "רכב", "Check primary insurance");
                assertTextEquals(results.get(2), "כללי", "Check secondary insurance");
            } else {
                smartLog("❌ Test 'testDocumentSearchAndResultDisplay' failed: not enough results found", "Document Search Failure", false, LogMode.HARD);
            }

        } catch (Exception e) {
            smartLog("❌ Test 'testDocumentSearchAndResultDisplay' failed: " + e.getMessage(), "Document Search Failure", false, LogMode.HARD);
            throw e;
        } finally {
            assertAllSoft();
        }
    }

    // TC-059
    @Test(description = "Search for forms and then clear the form fields")
    @Description("Searches a document, verifies result and then clears the form")
    public void testDocumentSearchAndClear() throws Exception {
        try {

            documentsAndForms.scrollAndClickToDocumentsAndForms();
            smartLog("✅ Scrolled and clicked Documents and Forms", "Documents and Forms", true, LogMode.SOFT);

            documentsAndForms.selectSubjectDropDownMenu();
            smartLog("✅ Selected subject dropdown", "Subject Dropdown", true, LogMode.SOFT);

            documentsAndForms.selectTypeDropDownMenu();
            smartLog("✅ Selected type dropdown", "Type Dropdown", true, LogMode.SOFT);

            documentsAndForms.clickSearch();
            smartLog("✅ Clicked search button", "Search", true, LogMode.SOFT);

            List<String> rowTexts = documentsAndForms.getSearchRowTexts();

            assertTextEquals(rowTexts.get(0), "בקשה לביטול פוליסה", "Check document name");
            assertTextEquals(rowTexts.get(1), "רכב", "Check primary insurance");
            assertTextEquals(rowTexts.get(2), "כללי", "Check secondary insurance");

            documentsAndForms.clickClear();
            smartLog("✅ Clicked clear button", "Clear", true, LogMode.SOFT);

            List<String> clearedTexts = documentsAndForms.getSearchRowTexts();

            assertTextEquals(clearedTexts.get(0), "", "Check document name cleared");
            assertTextEquals(clearedTexts.get(1), "", "Check primary insurance cleared");
            assertTextEquals(clearedTexts.get(2), "", "Check secondary insurance cleared");

        } catch (Exception e) {
            smartLog("❌ Test 'testDocumentSearchAndClear' failed: " + e.getMessage(), "Test Failure", false, LogMode.HARD);
            throw e;
        } finally {
            assertAllSoft();
        }
    }
}
