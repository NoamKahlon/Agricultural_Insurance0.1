package utils;

/**
 * Holds test data and expected values used for the "Contact Us" form validations.
 */
public class ContactUsData {

    // ========= Validation Messages =========
    /** Expected error message for empty required fields */
    public static final String EXPECTED_EMPTY_FIELD_ERROR = "חובה למלא שדה זה";

    // ========= Valid Input Data =========
    /** Valid full name for test input */
    public static final String VALID_NAME = "נועם";

    /** Valid Israeli phone number */
    public static final String VALID_PHONE = "0501234567";

    /** Valid email address for contact form */
    public static final String VALID_EMAIL = "test@example.com";

    /** Valid subject line */
    public static final String VALID_SUBJECT = "שירות";

    /** Valid message body for submission */
    public static final String VALID_MESSAGE = "הודעת בדיקה - נא לא ליצור קשר בפועל";

    // ========= Expected Titles on the Page =========
    /** Expected title for the sales contact section */
    public static final String SALES_CENTER_TITLE = "מרכז מכירות";

    /** Expected title for the claims contact section */
    public static final String CLAIMS_CENTER_TITLE = "מרכז תביעות";

    /** Expected title for the service contact section */
    public static final String SERVICE_CENTER_TITLE = "מרכז שירות";
}
