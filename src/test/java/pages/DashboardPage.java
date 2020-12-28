package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.project.ProjectPage;
import utils.PropertyReader;

import static data.TestData.URL_DASHBOARD;

public class DashboardPage extends BasePage {

    public static final By ADD_PROJECT_BUTTON = By.id("sidebar-projects-add");
    public static final String PROJECT = "//div[contains(@class,'project')]//a[contains(text(), '%s')]";

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public DashboardPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_PROJECT_BUTTON));
        return this;
    }

    @Step("Open Dashboard")
    public DashboardPage openDashboardPage() {
        driver.get(System.getenv().getOrDefault("url", PropertyReader.getProperty("url")) + URL_DASHBOARD);
        return this;
    }

    @Step("Click button 'Add Project'")
    public ProjectPage openProjectPage() {
        driver.findElement(ADD_PROJECT_BUTTON).click();
        return new ProjectPage(driver);
    }

    @Step("Open project '{name}'")
    public ProjectPage openProject(String name) {
        driver.findElement(By.xpath(String.format(PROJECT, name))).click();
        return new ProjectPage(driver);
    }
}
