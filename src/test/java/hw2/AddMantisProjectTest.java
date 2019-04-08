package hw2;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;


// TODO Смотри комментарии к классу AddMantisUserTest.class
public class AddMantisProjectTest {

    private WebDriver driver;
    private WebElement manageProjectsButton;

    @BeforeClass
    public void setUps()
    {
        System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\webdriver\\chrome\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Open Mantis
        driver.get("https://mantis.tiulp.in/login_page.php");
    }

    @Test(priority = 1)
    public void assertBrowserTitle()
    {
        assertEquals(driver.getTitle(), "MantisBT");
    }

    @Test(priority = 2)
    public void performLoginAndAssertUserName() {

        WebElement enterButton = driver.findElement(By.id("username"));
        enterButton.sendKeys("administrator");

        enterButton = driver.findElement(By.xpath("//input[@value='Login']"));
        enterButton.click();

        enterButton = driver.findElement((By.id("password")));
        enterButton.sendKeys("rootroot");

        enterButton = driver.findElement(By.xpath("//input[@value='Login']"));
        enterButton.click();
        assertEquals(driver.findElement(By.className("user-info")).getText(),
                "administrator");
    }


    @Test(priority = 3)
    public void AssertLeftSideMenu()
    {

        assertTrue(driver.findElement(By.id("sidebar")).isEnabled());

    }



    @Test(priority = 4)
    public void testManageButton()
    {
        WebElement manageButton = driver.findElement(By.linkText("Manage"));
        manageButton.click();

        manageProjectsButton = driver.findElement(By.linkText("Manage Projects"));

        assertTrue(manageProjectsButton.isEnabled());

    }

    @Test(priority = 5)
    public void assertFields()
    {
        manageProjectsButton.click();

        WebElement createNewProjectButton = driver.findElement(By.xpath("//fieldset/button"));
        createNewProjectButton.click();

        WebElement projectName = driver.findElement(By.id("project-name"));
        WebElement status = driver.findElement(By.id("project-status"));
        WebElement inhetitGlobalCategories = driver.findElement(By.id("project-inherit-global"));
        WebElement viewStatus = driver.findElement(By.id("project-view-state"));
        WebElement description = driver.findElement(By.id("project-description"));

        SoftAssert asert = new SoftAssert();

        asert.assertTrue(projectName.isEnabled());
        asert.assertTrue(status.isEnabled());
        asert.assertTrue(inhetitGlobalCategories.isEnabled());
        asert.assertTrue(viewStatus.isEnabled());
        asert.assertTrue(description.isEnabled());
        asert.assertAll();
    }

    @Test(priority = 6)
    public void addProject()
    {
        WebElement projectName = driver.findElement(By.id("project-name"));
        projectName.sendKeys("AlexanderEdomsky");

        WebElement projectStatus = driver.findElement(By.id("project-status"));
        projectStatus.sendKeys("release");

        WebElement inhetitGlobalCategories = driver.findElement(By.id("project-inherit-global"));
        inhetitGlobalCategories.click();

        WebElement viewStatus = driver.findElement(By.id("project-view-state"));
        viewStatus.sendKeys("public");

        WebElement projectDescription = driver.findElement(By.id("project-description"));
        projectDescription.sendKeys("Project description");

        WebElement addProjectBtn = driver.findElement(By.xpath("//input[@value='Add Project']"));
        addProjectBtn.click();
    }

    @Test(priority = 7)
    public void logout()
    {
        driver.findElement(By.className("user-info")).click();
        driver.findElement(By.xpath("//a[contains(., 'Logout')]")).click();
    }

    @AfterClass
    public void closeDriver()
    {
        driver.close();
    }
}
