package hw4;

import hw4.properties.Filter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ViewIssuesPage extends AbstractBasePage {

    @FindBy(id = "show_priority_filter")
    WebElement priority;

    @FindBy(id = "show_severity_filter")
    WebElement severity;

    @FindBy(id = "show_status_filter")
    WebElement status;

    @FindBy(name = "start_year")
    WebElement startYear;

    @FindBy(name = "start_month")
    WebElement startMonth;

    @FindBy(name = "start_day")
    WebElement startDay;

    @FindBy(name = "end_year")
    WebElement endYear;

    @FindBy(name = "end_month")
    WebElement endMonth;

    @FindBy(name = "end_day")
    WebElement endDay;

    @FindBy(xpath = "//input[@value='Apply Filter']")
    WebElement applyFilter;

    @FindBy(className = "badge")
    WebElement issueCount;

    public ViewIssuesPage(WebDriver driver)
    {
        super(driver);
    }

    WebElement someBtn;

    protected void selectMenu(String id, String option){
        driver.findElement(By.id(id)).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='" + id + "']/select/option[" + option + "]"))).click();
    }

    private void setPriority(String priorityNumber)
    {
        priority.click();
        selectMenu("show_priority_filter_target", priorityNumber);
    }

    private void setSeverity(String severityNumber)
    {
        severity.click();
        selectMenu("show_severity_filter_target", severityNumber);
    }

    private void setStatus(String statusNumber)
    {
        status.click();
        selectMenu("show_status_filter_target", statusNumber);
    }

    private void setStartYear(String startYear)
    {
        this.startYear.click();
        Select select = new Select(this.startYear);
        select.selectByVisibleText(startYear);
    }

    private void setStartMonth(String startMonth)
    {
        this.startMonth.click();
        Select select = new Select(this.startMonth);
        select.selectByVisibleText("March");
    }
    private void setStartDay(String startDay)
    {
        this.startDay.click();
        Select select = new Select(this.startDay);
        select.selectByVisibleText(startDay);
    }

    private void setEndYear(String endYear)
    {
        this.endYear.click();
        Select select = new Select(this.endYear);
        select.selectByVisibleText(endYear);
    }

    private void setEndMonth(String endMonth)
    {
        this.endMonth.click();
        Select select = new Select(this.endMonth);
        select.selectByVisibleText("April");
    }
    private void setEndDay(String endDay)
    {
        this.endDay.click();
        Select select = new Select(this.endDay);
        select.selectByVisibleText(endDay);
    }
    public void setFilterValues(Filter filter)
    {

        setPriority(filter.getPriority());
        setSeverity(filter.getSeverity());
        setStatus(filter.getStatus());

        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.id("do_filter_by_date_filter"))).click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(), 'Filter by Date Submitted')]"))).click();

        setStartYear(filter.getStartYear());
        setStartMonth(filter.getStartMonth());
        setStartDay(filter.getStartDay());

        setEndYear(filter.getEndYear());
        setEndMonth(filter.getEndMonth());
        setEndDay(filter.getEndDay());
    }

    public void applyFilter()
    {
        applyFilter.click();
    }

    public boolean isRecordExists()
    {
        return issueCount.getText() != "0 - 0 / 0" ? true : false;
    }
}
