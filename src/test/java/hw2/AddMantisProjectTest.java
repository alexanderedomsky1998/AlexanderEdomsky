package hw2;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

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

        enterButton = driver.findElement(By.xpath("//*[@id=\"login-form\"]/fieldset/input[2]"));
        enterButton.click();

        enterButton = driver.findElement((By.id("password")));
        enterButton.sendKeys("rootroot");

        enterButton = driver.findElement(By.xpath("//*[@id=\"login-form\"]/fieldset/input[3]"));
        enterButton.click();
        assertEquals(driver.findElement(By.className("user-info")).getText(),
                "administrator");
    }


    @Test(priority = 3)
    public void AssertLeftSideMenu()
    {
        assertTrue(driver.findElement(By.xpath("//*[@id='sidebar']")).isEnabled());
    }



    @Test(priority = 4)
    public void testManageButton()
    {
        WebElement manageButton = driver.findElement(By.xpath("//*[@id=\"sidebar\"]/ul/li[7]/a"));
        manageButton.click();

        manageProjectsButton = driver.findElement(By.xpath("//*[@id=\"main-container\"]/div[2]/div[2]/div/ul/li[3]/a"));

        assertTrue(manageProjectsButton.isEnabled());

    }

    @Test(priority = 5)
    public void assertFields()
    {
        manageProjectsButton.click();

        WebElement createNewProjectButton = driver.findElement(By.xpath("//*[@id=\"main-container\"]/div[2]/div[2]/div/div/div[2]/div[2]/div/div[1]/form/fieldset/button\n"));
        createNewProjectButton.click();
        //driver.findElement(By.xpath("//fieldset/button")).click();
        //driver.findElement(By.xpath("//button[contains(text(),'Create New Project')]")).click();

        WebElement projectName = driver.findElement(By.xpath("//*[@id=\"manage-project-create-form\"]/div/div[2]/div/div/table/tbody/tr[1]/td[1]"));
        WebElement status = driver.findElement(By.xpath("//*[@id=\"manage-project-create-form\"]/div/div[2]/div/div/table/tbody/tr[2]/td[1]"));
        WebElement inhetitGlobalCategories = driver.findElement(By.xpath("//*[@id=\"manage-project-create-form\"]/div/div[2]/div/div/table/tbody/tr[3]/td[1]"));
        WebElement viewStatus = driver.findElement(By.xpath("//*[@id=\"manage-project-create-form\"]/div/div[2]/div/div/table/tbody/tr[4]/td[1]"));
        WebElement description = driver.findElement(By.xpath("//*[@id=\"manage-project-create-form\"]/div/div[2]/div/div/table/tbody/tr[5]/td[1]"));

        assertTrue(projectName.isEnabled() && status.isEnabled() && inhetitGlobalCategories.isEnabled() &&
                viewStatus.isEnabled() && description.isEnabled());
    }

    @Test(priority = 6)
    public void addProject()
    {
        WebElement projectName = driver.findElement(By.id("project-name"));
        projectName.sendKeys("AlexanderEdomsky");

        WebElement projectStatus = driver.findElement(By.id("project-status"));
        projectStatus.sendKeys("release");

        WebElement inhetitGlobalCategories = driver.findElement(By.xpath("//span[@class='lbl']"));
        inhetitGlobalCategories.click();

        WebElement viewStatus = driver.findElement(By.id("project-view-state"));
        viewStatus.sendKeys("public");

        WebElement projectDescription = driver.findElement(By.id("project-description"));
        projectDescription.sendKeys("Project description");

        WebElement addProjectBtn = driver.findElement(By.xpath("//*[@id=\"manage-project-create-form\"]/div/div[3]/input"));
        addProjectBtn.click();
    }

    @Test(priority = 7)
    public void logout()
    {
        driver.findElement(By.className("user-info")).click();
        driver.findElement(By.xpath("//*[@id=\"navbar-container\"]/div[2]/ul/li[3]/ul/li[4]/a")).click();
    }

    @AfterClass
    public void closeDriver()
    {
        driver.close();
    }
}
