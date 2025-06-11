package utils;

import java.util.Arrays;
import java.util.List;

/**
 * Holds test data and constants used for the 'Search Documents and Forms' section validations.
 */
public class SearchDocumentsAndFormsData {

    // ========= Expected Table Headers =========
    /**
     * Expected headers of the search results table in the "Documents and Forms" section.
     */
    public static final List<String> expectedHeaders = Arrays.asList(
            "שם המסמך",        // Document name
            "ביטוח ראשי",       // Primary insurance
            "ביטוח משני",       // Secondary insurance
            "הורדה למחשב",     // Download to PC
            "מילוי אונליין"     // Online fillable
    );

    // ========= Text Constants =========
    /**
     * General insurance keyword used in free text search.
     */
    public static final String insurance = "ביטוח";
}
