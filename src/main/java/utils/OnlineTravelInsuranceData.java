package utils;

import java.util.Arrays;
import java.util.List;

public class OnlineTravelInsuranceData {


    public static final String EXPECTED_TRAVEL_INSURANCE_PAGE_TITLE = "ביטוח חקלאי - ביטוח נסיעות לחו״ל";
    public static final String DID_YOU_PURCHASE_INSURANCE_TEXT = "רכשת ביטוח";
    public static final List<String> EXPECTED_DESTINATIONS = Arrays.asList(
            "אירופה",
            "אפריקה",
            "צפון אמריקה",
            "אוסטרליה וניו זילנד",
            "דרום ומרכז אמריקה ואנטרטיקה", // <-- כתיב נכון לפי מה שבאמת מופיע באתר
            "אסיה"
    );
    public static final String STEP_DESTINATION = "לאן נוסעים?";



}
