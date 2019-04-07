package hw3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class PageObjectVoid {

    private WebDriver driver;
    private WebElement usernameTextField;
    private WebElement passwordTextField;
    private WebElement loginButton;

    private String password;
    private String username;

    public PageObjectVoid(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String username, String password) {
        usernameTextField = driver.findElement(By.id("username"));
        usernameTextField.sendKeys(username);
        loginButton = driver.findElement(By.xpath("//input[@value='Login']"));
        loginButton.click();
        passwordTextField = driver.findElement(By.id("password"));
        passwordTextField.sendKeys(password);
        loginButton = driver.findElement(By.xpath("//input[@value='Login']"));
        loginButton.click();
    }

    public String getPageTitle()
    {
        return  driver.getTitle();
    }

    public boolean containsElementById(String id){return driver.findElement(By.id(id)).isEnabled();}

    public boolean containsElementByLinkText(String linkText){return driver.findElement(By.linkText(linkText)).isEnabled();}





    public void addProjectInfo()
    {
        driver.findElement(By.id("project-name")).sendKeys("AlexanderEdomsky");

        driver.findElement(By.id("project-status")).sendKeys("release");

        driver.findElement(By.xpath("//span[@class='lbl']")).click();

        driver.findElement(By.id("project-view-state")).sendKeys("public");

        driver.findElement(By.id("project-description")).sendKeys("Project description");
    }

    public void addUserInfo()
    {
        username = "AlexanderEdomsky" + java.util.UUID.randomUUID().toString();
        driver.findElement(By.id("user-username")).sendKeys(username);

        driver.findElement(By.id("user-realname")).sendKeys("Alexander" + " " + java.util.UUID.randomUUID().toString());

        driver.findElement(By.id("email-field")).sendKeys(java.util.UUID.randomUUID().toString());

        password = java.util.UUID.randomUUID().toString();
        driver.findElement(By.id("user-password")).sendKeys(password);

        driver.findElement(By.id("user-verify-password")).sendKeys(password);

        driver.findElement(By.id("user-access-level")).sendKeys("reporter");

    }


    public void logout()
    {
        driver.findElement(By.className("user-info")).click();
        driver.findElement(By.xpath("//a[contains(., 'Logout')]")).click();
    }

    public void clickButtonByLinkText(String text)
    {
        driver.findElement(By.linkText(text)).click();
    }

    public void clickButtonByXPath(String xpath)
    {
        driver.findElement(By.xpath(xpath)).click();
    }

    public String getPassword(){return password;}

    public String getUsername(){return username;}
}
