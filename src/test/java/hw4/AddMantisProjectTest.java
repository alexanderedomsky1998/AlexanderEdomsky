package hw4;

import hw4.properties.Project;
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

public class AddMantisProjectTest {

    private WebDriver driver;
    private PageObjects pageObjects;

    // TODO Может быть вынесенов общий класс
    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        ChromeDriverManager.chromedriver().setup();
    }

    // TODO Может быть вынесенов общий класс
    @BeforeMethod(alwaysRun = true)
    public void initDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Open Mantis
        driver.get("https://mantis.tiulp.in/login_page.php");

        pageObjects = new PageObjects(driver);
    }

    @Test
    public void addProject()
    {
        User user = new User("administrator", "rootroot");
        Project project = new Project("AlexanderEdomsky", "release", true, "public", "Project description");
        //check title
        assertEquals(pageObjects.loginPage.getTitle(), "MantisBT");

        //login
        pageObjects.loginPage.login(user);

        //check leftsidebar
        assertTrue(pageObjects.basePage.leftSideBarExists());

        //click managebutton
        pageObjects.basePage.moveToManagePage();

        //click manage projects button
        pageObjects.managePage.moveToManageProjectsPage();

        //click create project button
        pageObjects.manageProjectsPage.moveToCreateProjectPage();

        //fill project info
        pageObjects.createProjectPage.addProjectInfo(project);

        //click add project button
        pageObjects.createProjectPage.addProject();

        //logout
        pageObjects.logoutPage.logout();
    }

    // TODO Может быть вынесенов общий класс
    @AfterMethod(alwaysRun = true)
    public void tearDownDriver() {
        // Close driver
        driver.close();
    }
}
