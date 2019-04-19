package hw4;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ManagePage extends AbstractBasePage {

    // TODO Должен быть реализован один метод который будет взаимодействовать с меню
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
