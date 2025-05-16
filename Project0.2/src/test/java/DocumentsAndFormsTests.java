import io.qameta.allure.Description;
import org.testng.annotations.Test;
import java.util.List;

public class DocumentsAndFormsTests extends Base {

    // TC-058
    @Test(description = "Verify document search functionality and result display on Bituach Haklai site")
    @Description("Navigates to the site, accesses document search, enters query, and verifies expected result fields")
    public void testDocumentSearchAndResultDisplay() throws Exception {
        try {

            basePage.openMainPage();
            smartLog("✅ Opened homepage", "Main Page", true);

            documentsAndForms.scrollAndClickToDocumentsAndForms();
            smartLog("✅ Scrolled and clicked Documents and Forms", "Documents and Forms", true);

            documentsAndForms.FillFreeTextField();
            smartLog("✅ Filled free text search field", "FreeText", true);

            documentsAndForms.clickSearch();
            smartLog("✅ Clicked search button", "Search", true);

// לבדוק אם צריך
//            List<String> rowTexts = documentsAndForms.getSearchRowTexts();
//            assertTextEquals(rowTexts.get(0), "בקשה לביטול פוליסה", "Check document name");
//

            // Optional: validate results
            List<String> results = documentsAndForms.getSearchRowTexts();
            if(results.size() >= 3){
                assertTextEquals(results.get(0), "", "Check document name");
                assertTextEquals(results.get(1), "", "Check primary insurance");
                assertTextEquals(results.get(2), "", "Check secondary insurance");
            }else
                smartLog("❌ Test 'testDocumentSearchAndResultDisplay' failed: no result was found" , "Document Search Failure", false);





        } catch (Exception e) {
            smartLog("❌ Test 'testDocumentSearchAndResultDisplay' failed: " + e.getMessage(), "Document Search Failure", false);
            throw e;
        }
    }


    // TC-059
    @Test(description = "Search for forms and then clear the form fields")
    @Description("Searches a document, verifies result and then clears the form")
    public void testDocumentSearchAndClear() throws Exception {
        try {
            basePage.openMainPage();

            documentsAndForms.scrollAndClickToDocumentsAndForms();
            smartLog("✅ Scrolled and clicked Documents and Forms", "documentsAndForms", true);

            documentsAndForms.selectSubjectDropDownMenu();
            smartLog("✅ Selected subject dropdown", "subject", true);

            documentsAndForms.selectTypeDropDownMenu();
            smartLog("✅ Selected type dropdown", "type", true);

            documentsAndForms.clickSearch();
            smartLog("✅ Clicked search button", "search", true);

            List<String> rowTexts = documentsAndForms.getSearchRowTexts();

            assertTextEquals(rowTexts.get(0), "בקשה לביטול פוליסה", "Check document name");
            assertTextEquals(rowTexts.get(1), "רכב", "Check primary insurance");
            assertTextEquals(rowTexts.get(2), "כללי", "Check secondary insurance");

            documentsAndForms.clickClear();
            smartLog("✅ Clicked clear button", "clear", true);

            List<String> clearedTexts = documentsAndForms.getSearchRowTexts();

            assertTextEquals(clearedTexts.get(0), "", "Check document name cleared");
            assertTextEquals(clearedTexts.get(1), "", "Check primary insurance cleared");
            assertTextEquals(clearedTexts.get(2), "", "Check secondary insurance cleared");

        } catch (Exception e) {
            smartLog("❌ Test 'testDocumentSearchAndClear' failed: " + e.getMessage(), "TestFailure", false);
            throw e;
        }
    }
}
