package hw4;

import hw4.properties.Project;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateProjectPage extends AbstractBasePage {

    @FindBy(id = "project-name")
    WebElement projectNameField;

    @FindBy(id = "project-status")
    WebElement projectStatusField;

    @FindBy(xpath = "//span[@class='lbl']")
    WebElement projectInheritGlobalField;

    @FindBy(id = "project-view-state")
    WebElement projectViewStateField;

    @FindBy(id = "project-description")
    WebElement projectDescriptionField;

    @FindBy(xpath = "//input[@value='Add Project']")
    WebElement addProjectButton;

    public CreateProjectPage(WebDriver driver) {
        super(driver);
    }

    public void addProjectInfo(Project project) {
        projectNameField.sendKeys(project.getProjectName());
        projectStatusField.sendKeys(project.getProjectStatus());
        if (project.getInheritGlobalCategories())
            projectInheritGlobalField.click();
        projectViewStateField.sendKeys(project.getViewStatus());
        projectDescriptionField.sendKeys(project.getDescription());
    }

    public void addProject() {
        addProjectButton.click();
    }
}
