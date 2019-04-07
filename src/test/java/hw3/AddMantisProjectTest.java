package hw3;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class AddMantisProjectTest {

    private PageObjectVoid pageObjectVoid;
    private WebDriver driver;

    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        ChromeDriverManager.chromedriver().setup();
    }

    @BeforeMethod(alwaysRun = true)
    public void initDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Open Mantis
        driver.get("https://mantis.tiulp.in/login_page.php");

        pageObjectVoid = new PageObjectVoid(driver);
    }


    @Test
    public void addProject() {

        //assert browser title
        assertEquals(pageObjectVoid.getPageTitle(), "MantisBT");

        // Login
        pageObjectVoid.login("administrator", "rootroot");

        // Check login
        assertEquals(driver.findElement(By.className("user-info")).getText(),
                "administrator");

        //check left side menu
        assertTrue(pageObjectVoid.containsElementById("sidebar"));

        //click manage button
        pageObjectVoid.clickButtonByLinkText("Manage");

        //check manage Project button
        assertTrue(pageObjectVoid.containsElementByLinkText("Manage Projects"));

        //click manage Project button
        pageObjectVoid.clickButtonByLinkText("Manage Projects");

        //click Create New Project button
        pageObjectVoid.clickButtonByXPath("//fieldset/button");

        //check fields
        ArrayList<String> fields = new ArrayList<String>();
        fields.add("project-name");
        fields.add("project-status");
        fields.add("project-inherit-global");
        fields.add("project-view-state");
        fields.add("project-description");

        for(String field : fields)
            assertTrue(pageObjectVoid.containsElementById(field));

        //add project info
        pageObjectVoid.addProjectInfo();

        //click add Project button
        pageObjectVoid.clickButtonByXPath("//input[@value='Add Project']");

        //logout
        pageObjectVoid.logout();
    }



    @AfterMethod(alwaysRun = true)
    public void tearDownDriver() {
        // Close driver
        driver.close();
    }

}
