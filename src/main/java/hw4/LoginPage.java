package hw4;


import hw4.properties.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractBasePage {

    @FindBy(id = "username")
    private WebElement usernameTextField;

    @FindBy(id = "password")
    private WebElement passwordTextField;

    @FindBy(xpath = "//input[@value='Login']")
    private WebElement loginButton;

    public LoginPage(WebDriver driver)
    {
        super(driver);
    }

    public void login(User user) {
        usernameTextField.sendKeys(user.getUserName());
        loginButton.click();
        passwordTextField.sendKeys(user.getUserPassword());
        loginButton.click();
    }

    public String getTitle()
    {
        return driver.getTitle();
    }
}
