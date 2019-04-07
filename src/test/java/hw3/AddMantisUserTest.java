package hw3;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AddMantisUserTest {

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
    public void addUser()
    {
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

        //click ManageUsers button
        pageObjectVoid.clickButtonByLinkText("Manage Users");

        //check Create New Account button
        assertTrue(pageObjectVoid.containsElementByLinkText("Create New Account"));

        //click Create New Account button
        pageObjectVoid.clickButtonByLinkText("Create New Account");

        //check fields
        ArrayList<String> fields = new ArrayList<String>();
        fields.add("user-username");
        fields.add("user-realname");
        fields.add("email-field");
        fields.add("user-password");
        fields.add("user-verify-password");
        fields.add("user-access-level");

        for(String field : fields)
            assertTrue(pageObjectVoid.containsElementById(field));

        //add user info
        pageObjectVoid.addUserInfo();

        //click Create User
        pageObjectVoid.clickButtonByXPath("//input[@value='Create User']");

        //logout
        pageObjectVoid.logout();

        //login new User
        pageObjectVoid.login(pageObjectVoid.getUsername(), pageObjectVoid.getPassword());

        // Check login
        assertEquals(driver.findElement(By.className("user-info")).getText(),
                pageObjectVoid.getUsername());

        //logout
        pageObjectVoid.logout();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownDriver() {
        // Close driver
        driver.close();
    }
}
