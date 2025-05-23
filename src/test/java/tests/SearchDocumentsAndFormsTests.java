
package tests;
import flows.SearchDocumentsAndFormsFlow;
import io.qameta.allure.Description;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class SearchDocumentsAndFormsTests extends Base {

    SearchDocumentsAndFormsFlow flow;

    @BeforeMethod
    public void setup() {
        this.flow = new SearchDocumentsAndFormsFlow(driver, basePage, searchDocumentsAndFormsPage, loggerUtils);
    }

    // TC-058 - UI - תוצאות חיפוש מסמכים בחיפוש חופשי
    @Test(description = "TC-058 - Verify free text document search returns correct results")
    @Description("Navigate to Documents & Forms section, perform free text search, and verify result fields (document name, primary and secondary insurance)")
    public void test_TC058_FreeTextDocumentSearch() throws Exception {
        try {
            flow.scrollClickAndSelectCarSubject();

//            searchDocumentsAndForms.selectSubjectCarFromDropDownMenu();
//            //loggerUtils.log("✅ Selected subject", "SubjectSelect", true, LogMode.HARD);
//            loggerUtils.log("✅ Selected subject", "SubjectSelect", true, true);

            searchDocumentsAndFormsPage.FillTextField();
            //loggerUtils.log("✅ Entered free text search value", "FreeText", true, LogMode.HARD);
            loggerUtils.log("✅ Entered free text search value", "FreeText", true, true);


            searchDocumentsAndFormsPage.clickSearch();
            loggerUtils.log("✅ Clicked Search button", "ClickSearch", true, true);

            // check table size
            List<WebElement> tableSearchResult = searchDocumentsAndFormsPage.getTableSearchResult();

            loggerUtils.log(tableSearchResult.isEmpty()
                            ? "❌ No search results found. Table size is 0."
                            : "✅ doucuments Load succsefuly Found " + tableSearchResult.size() + " results",
                    "", !tableSearchResult.isEmpty(), true);

            flow.verifyTableHeaders();



//            //get heades
//            List<WebElement> headerTitles = searchDocumentsAndFormsPage.getTableHeader();
//
//            // make them to strings
//            List<String> actualHeaders = searchDocumentsAndFormsPage.getWebElementListTexts(headerTitles);
//
//            List<String> expectedHeaders =  SearchDocumentsAndFormsData.expectedHeaders;
//
//            // הדפסת כל הכותרות ללוג אחד
//            String allHeadersStr = String.join(" , ", actualHeaders);
//
//            // השוואה ובדיקה
//            boolean headersMatch = actualHeaders.equals(expectedHeaders);
//            loggerUtils.log(headersMatch ? "✅ Table headers match expected values: "+ allHeadersStr : "❌ Table headers do not match expected!\nExpected: "
//                                    + expectedHeaders + "\nActual: " + actualHeaders, "", headersMatch, false);

//            List<WebElement> documentSearchResult = searchDocumentsAndFormsPage.getTableSearchResult();
//
//            List<String> actualdocumentSearchResult = searchDocumentsAndFormsPage.getWebElementListTexts(documentSearchResult);


        } catch (Exception e) {
            loggerUtils.log("❌ TC-058 failed: " + e.getMessage(), "TC058_Failure", false, true);
            throw e;
        } finally {
            loggerUtils.softAssert.assertAll();
        }
    }

     //TC-059 - UI - ניקוי תוצאות חיפוש מסמכים
    @Test(description = "TC-059 - Verify clearing document search fields clears the search results")
    @Description("Select subject and type, perform a search, verify results, then clear the form and validate that results are reset")
    public void test_TC059_DocumentSearchAndClear() throws Exception {
        try {
//
//            searchDocumentsAndForms.scrollAndClickToDocumentsAndForms();
//            loggerUtils.log("✅ Scrolled and clicked Documents and Forms", "DocumentsSection", true, LogMode.SOFT);


//            flow.scrollAndClickDocumentsAndFormsSection();
//
//            searchDocumentsAndForms.selectSubjectCarFromDropDownMenu();
//            loggerUtils.log("✅ Selected subject", "SubjectSelect", true, false);

            flow.scrollClickAndSelectCarSubject();

            // pick type
            searchDocumentsAndFormsPage.selectGeneralFromTypeDropDownMenu();
            loggerUtils.log("✅ Selected General type", "TypeSelect", true, false);

            searchDocumentsAndFormsPage.clickSearch();
            loggerUtils.log("✅ Clicked Search button", "ClickSearch", true, true);



            searchDocumentsAndFormsPage.clickClear();
            loggerUtils.log("✅ Clicked Clear button", "ClickClear", true, false);

            flow.verifyTableIsEmpty();

//            //get heades
//            List<WebElement> headerTitles = searchDocumentsAndFormsPage.getTableHeader();
//
//            // make them to strings
//            List<String> actualHeaders = searchDocumentsAndFormsPage.getWebElementListTexts(headerTitles);
//
//            List<String> expectedHeaders =  SearchDocumentsAndFormsData.expectedHeaders;
//
//            // הדפסת כל הכותרות ללוג אחד
//            String allHeadersStr = String.join(" , ", actualHeaders);
//
//            // השוואה ובדיקה
//            boolean headersMatch = actualHeaders.equals(expectedHeaders);
//            loggerUtils.log(headersMatch ? "✅ Table headers match expected values: "+ allHeadersStr : "❌ Table headers do not match expected!\nExpected: "
//                    + expectedHeaders + "\nActual: " + actualHeaders, "", headersMatch, false);

//            List<String> results = searchDocumentsAndFormsPage.getTableDocumentSearchResult();
//            assertTextEquals(results.get(0), "בקשה לביטול פוליסה", "Document Name");
//            assertTextEquals(results.get(1), "רכב", "Primary Insurance");
//            assertTextEquals(results.get(2), "כללי", "Secondary Insurance");


//            List<String> cleared = searchDocumentsAndFormsPage.getSearchRowTexts();
//            assertTextEquals(cleared.get(0), "", "Document Name Cleared");
//            assertTextEquals(cleared.get(1), "", "Primary Insurance Cleared");
//            assertTextEquals(cleared.get(2), "", "Secondary Insurance Cleared");

        } catch (Exception e) {
            loggerUtils.log("❌ TC-059 failed: " + e.getMessage(), "TC059_Failure", false, true);
            throw e;
        } finally {
            loggerUtils.softAssert.assertAll();
        }
    }

}
