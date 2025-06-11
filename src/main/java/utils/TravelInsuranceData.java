package utils;

import java.util.Arrays;
import java.util.List;

public class TravelInsuranceData {

    // === Page Titles ===
    public static final String EXPECTED_TRAVEL_INSURANCE_PAGE_TITLE = "ביטוח חקלאי - ביטוח נסיעות לחו״ל";
    public static final String DID_YOU_PURCHASE_INSURANCE_TEXT = "רכשת ביטוח";

    // === Step Titles ===
    public static final String STEP_DESTINATION = "לאן נוסעים?";
    public static final String PASSENGERS_FROM_ISRAEL_QUESTION = "האם כל הנוסעים יוצאים מישראל?";

    public static final List<String> TRAVEL_INSURANCE_STEPS = Arrays.asList(
            "לאן נוסעים?",
            "מתי נוסעים?",
            "פרטי הנוסעים",
            "כיסויים בשבילך",
            "כיסויים מיוחדים",
            "הצהרת בריאות",
            "סיכום הצעה",
            "תשלום ואישור"
    );

    // === Expected Values ===
    public static final List<String> EXPECTED_DESTINATIONS = Arrays.asList(
            "אירופה",
            "אפריקה",
            "צפון אמריקה",
            "אוסטרליה וניו זילנד",
            "דרום ומרכז אמריקה ואנטרטיקה",
            "אסיה"
    );

    public static final List<String> EXPECTED_COVERAGE_TITLES = Arrays.asList(
            "הרחבה לביטול וקיצור נסיעה מסיבות רפואיות",
            "כבודה",
            "מחשב / טאבלט",
            "מצלמה",
            "טלפון נייד / מכשיר GPS",
            "אופניים"
    );

    public static final List<String> EXPECTED_TITLES = Arrays.asList(
            "ספורט חורף",
            "ספורט אתגרי",
            "ספורט תחרותי",
            "ביטול השתתפות עצמית לרכב שכור"
    );

    public static final List<String> EXPECTED_CONTACT_TITLES = Arrays.asList(
            "עיר",
            "מס' בית",
            "מס' דירה",
            "מיקוד"
    );

    // === Passenger Details Test Data ===
    public static final String GENDER = "זכר";
    public static final String PHONE = "0584451245";
    public static final String FIRST_NAME_EN = "Noam";
    public static final String LAST_NAME_EN = "Kahlon";
    public static final String FIRST_NAME_HE = "נועם";
    public static final String LAST_NAME_HE = "כחלון";
    public static final String ID_NUMBER = "206916009";
    public static final String BIRTH_DATE = "04/08/1999";
    public static final String EMAIL = "noam123@gmail.com";
}
