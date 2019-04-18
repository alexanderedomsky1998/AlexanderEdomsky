package hw4;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ManageProjectsPage extends AbstractBasePage {

    @FindBy(xpath = "//fieldset/button")
    WebElement createProjectButton;

    public ManageProjectsPage(WebDriver driver)
    {
        super(driver);
    }

    public void moveToCreateProjectPage()
    {
        createProjectButton.click();
    }

}
