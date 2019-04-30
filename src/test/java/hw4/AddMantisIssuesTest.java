package hw4;

import hw4.properties.Filter;
import hw4.properties.User;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AddMantisIssuesTest {
    private WebDriver driver;
    private PageObjects pageObjects;

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
       // driver.get("http://khda91.fvds.ru/mantisbt/");

        pageObjects = new PageObjects(driver);
    }

    @Test
    public void addProject()
    {
        User user = new User("administrator", "root");
        Filter filter = new Filter("5", "5", "6", "2019", "March","27", "2019", "April", "1" );
        //check title
        assertEquals(pageObjects.loginPage.getTitle(), "MantisBT");

        //login
        pageObjects.loginPage.login(user);

        //check leftsidebar
        assertTrue(pageObjects.basePage.leftSideBarExists());

        //click view issues button
        pageObjects.basePage.moveToViewIssuesPage();

        //set filter info
        pageObjects.viewIssuesPage.setFilterValues(filter);

        //click apply filter
        pageObjects.viewIssuesPage.applyFilter();

        //check created record
        assertTrue(pageObjects.viewIssuesPage.isRecordExists());

        //logout
        pageObjects.logoutPage.logout();

    }


    @AfterMethod(alwaysRun = true)
    public void tearDownDriver() {
        // Close driver
        driver.close();
    }

}
