package utils;

/**
 * Contains expected messages used when verifying the existence of car insurance.
 */
public class CheckingTheExistenceOfCarInsuranceData {

    // ========= Valid Case =========
    /** Message expected when the car has valid insurance */
    public static final String VALID_LICENSE_SUCCESS_TEXT =
            "בתאריך 04/11/2024 נמצא ביטוח תקף לרכב שמספרו 23652125 בביטוח חקלאי";

    // ========= Not Valid Case =========
    /** Message expected when the car has no valid insurance */
    public static final String INVALID_LICENSE_ERROR_TEXT =
            "לא נמצא ביטוח בתוקף עבור הנתונים שהוזנו";
}
