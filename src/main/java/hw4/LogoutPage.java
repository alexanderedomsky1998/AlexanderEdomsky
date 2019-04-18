package hw4;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogoutPage extends AbstractBasePage{

    @FindBy(className = "user-info")
    WebElement userInfoButton;

    @FindBy(xpath = "//a[contains(., 'Logout')]")
    WebElement logout;

    public LogoutPage(WebDriver driver)
    {
        super(driver);
    }

    public void logout()
    {
        userInfoButton.click();
        logout.click();
    }
}
