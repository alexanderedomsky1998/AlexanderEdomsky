package hw4;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasePage extends AbstractBasePage{

    @FindBy(linkText = "Manage")
    WebElement manageButton;

    @FindBy(linkText = "View Issues")
    WebElement viewIssuesButton;

    @FindBy(id = "sidebar")
    WebElement leftSideBar;

    public BasePage(WebDriver driver)
    {
        super(driver);
    }

    public void moveToManagePage()
    {
        manageButton.click();
    }

    public void moveToViewIssuesPage(){viewIssuesButton.click();}

    public boolean leftSideBarExists()
    {
        return leftSideBar.isEnabled();
    }
}
