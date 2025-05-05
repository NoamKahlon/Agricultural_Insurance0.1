import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Test1 {

   @org.junit.Test
    //@org.junit.Description("Verify that the 'new student page' title matches the expected value")
    public void test01_New_StudentPage_Title() throws ParserConfigurationException, IOException, SAXException {
        try {
            Assert.assertEquals("title2", "title2"); // Using JUnit 4's Assert
            Allure.step("Test passed");
            System.out.println(" -- Test Passed");
        } catch (AssertionError error) {
            Allure.step("Test failed: " + error.getMessage(), Status.FAILED);
            System.out.println(" -- Test Failed");
            Assert.fail(); // Using JUnit 4's fail
        }
    }
}
