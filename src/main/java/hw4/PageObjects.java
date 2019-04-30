package hw4;

import org.openqa.selenium.WebDriver;

public class PageObjects {
    public LoginPage loginPage;
    public CreateProjectPage createProjectPage;
    public ManagePage managePage;
    public ManageProjectsPage manageProjectsPage;
    public BasePage basePage;
    public LogoutPage logoutPage;
    public ViewIssuesPage viewIssuesPage;

    public PageObjects(WebDriver driver) {
        loginPage = new LoginPage(driver);
        createProjectPage = new CreateProjectPage(driver);
        managePage = new ManagePage(driver);
        manageProjectsPage = new ManageProjectsPage(driver);
        basePage = new BasePage(driver);
        logoutPage = new LogoutPage(driver);
        viewIssuesPage = new ViewIssuesPage(driver);
    }
}
