package pages.project;

import io.qameta.allure.Step;
import modals.DeleteModal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;
import utils.PropertyReader;
import utils.Utils;

public class ProjectsPage extends BasePage {

    public static final String NAME_OF_PROJECT = "//a[contains(text(),'%s')]";
    public static final By ADD_PROJECT_BUTTON = By.xpath("//a[contains(text(),'Add Project')]");
    public static final String DELETE_ICON = "//a[contains(text(),'%s')]/ancestor::tr//div[contains(@class,'icon-small-delete')]";
    public static final String EDIT_ICON = "//a[contains(text(),'%s')]/ancestor::tr//div[contains(@class,'icon-small-edit')]";


    public ProjectsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open page with all projects")
    public ProjectsPage openProjectsPage() {
        String url = System.getenv().getOrDefault("url", PropertyReader.getProperty("url")) +
                "index.php?/admin/projects/overview";
        driver.get(url);
        return this;
    }

    @Override
    public ProjectsPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_PROJECT_BUTTON));
        return this;
    }

    @Step("Click by project '{name}'")
    public ProjectPage openProject(String name) {
        driver.findElement(By.xpath(String.format(NAME_OF_PROJECT, name))).click();
        return new ProjectPage(driver);
    }

    @Step("Click button 'Add project'")
    public NewProjectPage clickButtonAddProject() {
        driver.findElement(ADD_PROJECT_BUTTON).click();
        return new NewProjectPage(driver);
    }

    @Step("Click icon delete project '{name}'")
    public DeleteModal clickDelete(String name) {
        driver.findElement(By.xpath(String.format(DELETE_ICON, name))).click();
        return new DeleteModal(driver);
    }

    @Step("Click icon edit project '{name}'")
    public NewProjectPage clickEdit(String name) {
        driver.findElement(By.xpath(String.format(EDIT_ICON, name))).click();
        return new NewProjectPage(driver);
    }

    public int numberOfProjectsByName(String name) {
        return Utils.numberOfIssuesByName(driver.findElements(By.xpath(String.format(NAME_OF_PROJECT, name))), name);
    }
}
