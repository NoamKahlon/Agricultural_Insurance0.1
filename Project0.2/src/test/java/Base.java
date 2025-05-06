import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Base {


    TestPage testpage;

    public static WebDriver driver;
    static String configPath = "src/test/testData/config.xml";

    public Base() {
        // no-arg constructor
    }



   @BeforeSuite
    public void setup() throws ParserConfigurationException, IOException, SAXException {

        try{
            System.out.println(readFromFile("browser",configPath));
        }catch (Exception e){
            System.out.println("didnt work");
        }

        driver = new EdgeDriver();
   }


    @BeforeTest
    public void beforetest(){
        testpage = new TestPage(driver);

    }



    protected static String readFromFile(String keyData, String pathName) throws ParserConfigurationException, IOException, SAXException {
        String value = null;
        try {
            // Load the XML file
            File xmlFile = new File(pathName.toString());
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            // Normalize the document
            doc.getDocumentElement().normalize();

            // Extract values from XML
            value = doc.getElementsByTagName(keyData).item(0).getTextContent();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return value;
    }
}
