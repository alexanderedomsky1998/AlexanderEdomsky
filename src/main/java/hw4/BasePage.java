package hw4;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasePage extends AbstractBasePage{

    // TODO Должен быть реализован один метод который будет взаимодействовать с меню
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
