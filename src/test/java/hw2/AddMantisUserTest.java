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
public class AddMantisUserTest {
    private WebDriver driver;
    private WebElement createNewAccountBtn;

    private String username;
    private String password;

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

        WebElement manageUsersButton = driver.findElement(By.xpath("//*[@id=\"main-container\"]/div[2]/div[2]/div/ul/li[2]/a"));
        manageUsersButton.click();
        createNewAccountBtn = driver.findElement(By.xpath("//*[@id=\"manage-user-div\"]/div[1]/a"));
        assertTrue(createNewAccountBtn.isEnabled());
    }

    @Test(priority = 5)
    public void createNewAccount()
    {
        createNewAccountBtn.click();
        WebElement userName = driver.findElement(By.xpath("//*[@id=\"manage-user-create-form\"]/div/div[2]/div/div/table/tbody/tr[1]/td[1]"));
        WebElement realName = driver.findElement(By.xpath("//*[@id=\"manage-user-create-form\"]/div/div[2]/div/div/table/tbody/tr[2]/td[1]"));
        WebElement email = driver.findElement(By.xpath("//*[@id=\"manage-user-create-form\"]/div/div[2]/div/div/table/tbody/tr[3]/td[1]"));
        WebElement password = driver.findElement(By.xpath("//*[@id=\"manage-user-create-form\"]/div/div[2]/div/div/table/tbody/tr[4]/td[1]"));
        WebElement verifyPassword = driver.findElement(By.xpath("//*[@id=\"manage-user-create-form\"]/div/div[2]/div/div/table/tbody/tr[5]/td[1]"));
        WebElement accessLevel = driver.findElement(By.xpath("//*[@id=\"manage-user-create-form\"]/div/div[2]/div/div/table/tbody/tr[6]/td[1]"));
        WebElement enabled = driver.findElement(By.xpath("//*[@id=\"manage-user-create-form\"]/div/div[2]/div/div/table/tbody/tr[7]/td[1]"));
        WebElement Protected = driver.findElement(By.xpath("//*[@id=\"manage-user-create-form\"]/div/div[2]/div/div/table/tbody/tr[8]/td[1]"));
        assertTrue(userName.isEnabled() && realName.isEnabled() && email.isEnabled() &&
                password.isEnabled() && verifyPassword.isEnabled() && accessLevel.isEnabled() &&
                enabled.isEnabled() && Protected.isEnabled());
    }

    @Test(priority = 6)
    public void fillUserInformation()
    {
        username = "AlexanderEdomsky" + java.util.UUID.randomUUID().toString();
        driver.findElement(By.id("user-username")).sendKeys(username);
        driver.findElement((By.id("user-realname"))).sendKeys("Alexander" + " " + java.util.UUID.randomUUID().toString());
        driver.findElement(By.id("email-field")).sendKeys(java.util.UUID.randomUUID().toString());
        password = java.util.UUID.randomUUID().toString();
        driver.findElement(By.id("user-password")).sendKeys(password);
        driver.findElement(By.id("user-verify-password")).sendKeys(password);
        driver.findElement(By.id("user-access-level")).sendKeys("reporter");
    }

    @Test(priority = 7)
    public void createNewUserAccountAndLogout()
    {
        driver.findElement(By.xpath("//input[@value='Create User']")).click();
        logout();
    }

    private void logout()
    {
        driver.findElement(By.className("user-info")).click();
        driver.findElement(By.xpath("//*[@id=\"navbar-container\"]/div[2]/ul/li[3]/ul/li[4]/a")).click();
    }

    @Test(priority = 9)
    public void loginMewAccount()
    {
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.xpath("//*[@id=\"login-form\"]/fieldset/input[2]")).click();

        driver.findElement((By.id("password"))).sendKeys(password);
        driver.findElement(By.xpath("//*[@id=\"login-form\"]/fieldset/input[3]")).click();

    }

    @Test(priority = 10)
    public void AssertUserNameAndLogout()
    {
        assertEquals(driver.findElement(By.className("user-info")).getText(),
                username);
        logout();
    }

    @AfterClass
    public void closeDriver()
    {
        driver.close();
    }

}
