package hw4;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ManagePage extends AbstractBasePage {

    @FindBy(linkText = "Manage Projects")
    WebElement manageProjectsButton;

    public ManagePage(WebDriver driver)
    {
        super(driver);
    }

    public void moveToManageProjectsPage()
    {
        manageProjectsButton.click();
    }
}
