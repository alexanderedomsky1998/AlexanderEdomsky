package hw2;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.asserts.SoftAssert;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/* TODO
        3. Локаторы автосгенерированные
 */
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
    public void assertLeftSideMenu()
    {
        assertTrue(driver.findElement(By.id("sidebar")).isEnabled());
    }

    @Test(priority = 4)
    public void testManageButton()
    {
        WebElement manageButton = driver.findElement(By.linkText("Manage"));
        manageButton.click();

        WebElement manageUsersButton = driver.findElement(By.linkText("Manage Users"));
        manageUsersButton.click();
        createNewAccountBtn = driver.findElement(By.linkText("Create New Account"));
        assertTrue(createNewAccountBtn.isEnabled());
    }

    @Test(priority = 5)
    public void createNewAccount()
    {
        createNewAccountBtn.click();
        WebElement userName = driver.findElement(By.id("user-username"));
        WebElement realName = driver.findElement(By.id("user-realname"));
        WebElement email = driver.findElement(By.id("email-field"));
        WebElement password = driver.findElement(By.id("user=password"));
        WebElement verifyPassword = driver.findElement(By.id("user-verify-password"));
        WebElement accessLevel = driver.findElement(By.id("user-access-level"));

        SoftAssert asert = new SoftAssert();

        asert.assertTrue(userName.isEnabled());
        asert.assertTrue(realName.isEnabled());
        asert.assertTrue(email.isEnabled());
        asert.assertTrue(password.isEnabled());
        asert.assertTrue(verifyPassword.isEnabled());
        asert.assertTrue(accessLevel.isEnabled());

        asert.assertAll();
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
        driver.findElement(By.xpath("//a[contains(., 'Logout')]")).click();
    }

    @Test(priority = 9)
    public void loginMewAccount()
    {
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.xpath("//input[@value='Login']")).click();

        driver.findElement((By.id("password"))).sendKeys(password);
        driver.findElement(By.xpath("//input[@value='Login']")).click();

    }

    @Test(priority = 10)
    public void assertUserNameAndLogout()
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
