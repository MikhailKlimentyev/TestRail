package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

    @Step("Click button 'Add Project'")
    public ProjectPage openProjectPage() {
        driver.findElement(ADD_PROJECT_BUTTON).click();
        return new ProjectPage(driver);
    }

    @Step("Open project '{name}}'")
    public void openProject(String name) {
        driver.findElement(By.xpath(String.format(PROJECT,name))).click();
    }
}
