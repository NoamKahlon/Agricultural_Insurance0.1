
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.testng.Assert;


public class Test1 extends Base {



    @Test
    void test01_New_StudentPage_Title() {
        testPage.openUrl();
        String actualTitle = getTitleWithStep();
        try {
            Assert.assertEquals("Google", actualTitle);
        } catch (AssertionError error) {
            failedStep(error);
            Assert.fail(error);
        }
    }

    @Step("Getting the title from the page")
    public String getTitleWithStep() {
        return testPage.getTitle();
    }

    @Step("Test failed: {error}")
    public void failedStep(Throwable error) {
        // logging failure
    }

}
