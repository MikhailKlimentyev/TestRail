package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProjectPage extends BasePage {

    public static final By PROJECT_TAB = By.id("projects-tabs-project");
    public static final By NAME_INPUT = By.id("name");
    public static final By ANNOUNCEMENT_TEXTAREA = By.id("announcement");
    public static final By ANNOUNCEMENT_CHECKBOX = By.id("show_announcement");
    public static final String RADIOBUTTON_GROUP = "//div[contains(@class,'row add-project-row')]/descendant::strong[contains(text(),'%s')]";
    public static final String NAME_OF_CHECKED_RADIOBUTTON = "//div[contains(@class, 'radio')]//input[contains(@checked, 'checked')]//ancestor::label/strong";
    public static final By ADD_PROJECT_BUTTON = By.id("accept");
    public static final By RETURN_TO_DASHBOARD_BUTTON = By.id("navigation-dashboard-top");
    public static final By DASHBOARD_TAB = By.id("navigation-dashboard");

    //public static final By PROJECT_TITLE = By.cssSelector(".page_title");
    //public static final By PROJECT_ANNOUNCEMENT = By.xpath("//div[contains(@class,'markdown')]//p");

    public ProjectPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public ProjectPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_PROJECT_BUTTON));
        return this;
    }

    @Step("Set '{name}' into field Name")
    public ProjectPage setNameOfProject(String name) {
        driver.findElement(NAME_INPUT).clear();
        driver.findElement(NAME_INPUT).sendKeys(name);
        return this;
    }

    public String getNameOfProject() {
        return driver.findElement(NAME_INPUT).getAttribute("value");
    }

    @Step("Set '{announcement}' into field Announcement")
    public ProjectPage setAnnouncement(String announcement) {
        driver.findElement(ANNOUNCEMENT_TEXTAREA).clear();
        driver.findElement(ANNOUNCEMENT_TEXTAREA).sendKeys(announcement);
        return this;
    }

    public String getAnnouncement() {
        return driver.findElement(ANNOUNCEMENT_TEXTAREA).getText();
    }

    @Step("Activate checkbox 'Show announcement'")
    public ProjectPage activateCheckbox(boolean showAnnouncement) {
        if (showAnnouncement) {
            if (!driver.findElement(ANNOUNCEMENT_CHECKBOX).isSelected()) {
                driver.findElement(ANNOUNCEMENT_CHECKBOX).click();
            }
        } else {
            if (driver.findElement(ANNOUNCEMENT_CHECKBOX).isSelected()) {
                driver.findElement(ANNOUNCEMENT_CHECKBOX).click();
            }
        }
        return this;
    }

    public boolean getStatusCheckbox() {
        return driver.findElement(ANNOUNCEMENT_CHECKBOX).isSelected();
    }

    @Step("Choose radiobutton '{radio}'")
    public ProjectPage chooseRadiobutton(String radio) {
        driver.findElement(By.xpath(String.format(RADIOBUTTON_GROUP, radio))).click();
        return this;
    }

    public String getValueOfRadiobutton() {
        return driver.findElement(By.xpath(NAME_OF_CHECKED_RADIOBUTTON)).getText();
    }

    @Step("Click button 'Add Project'")
    public ProjectPage clickAddProjectButton() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView()", driver.findElement(ADD_PROJECT_BUTTON));
        driver.findElement(ADD_PROJECT_BUTTON).click();
        return this;
    }

    public DashboardPage returnToDashboardPage() {
        driver.findElement(DASHBOARD_TAB).click();
        return new DashboardPage(driver);
    }
}