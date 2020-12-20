package pages;

import io.qameta.allure.Step;
import modals.DeleteModal;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.PropertyReader;

import static data.TestData.URL_PROJECTS;
import static org.testng.Assert.assertEquals;

public class ProjectsPage extends BasePage {
    public static final String NAME_OF_PROJECT = "//a[contains(text(),'%s')]";
    public static final String ADD_PROJECT_BUTTON = "//a[contains(text(),'Add Project')]";
    public static final String DELETE_ICON = "//a[contains(text(),'%s')]/ancestor::tr//div[contains(@class,'icon-small-delete')]";
    public static final String EDIT_ICON = "//a[contains(text(),'%s')]/ancestor::tr//div[contains(@class,'icon-small-edit')]";

    public ProjectsPage(WebDriver driver) {
        super(driver);
    }

    public ProjectsPage openProjectsPage() {
        driver.get(System.getenv().getOrDefault("url", PropertyReader.getProperty("url")) + URL_PROJECTS);
        return this;
    }

    @Override
    public ProjectsPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ADD_PROJECT_BUTTON)));
        return this;
    }

    public ProjectsPage validateExistentProject(String name) {
        assertEquals(name, driver.findElement(By.xpath(String.format(NAME_OF_PROJECT, name))).getText(), name + " not found");
        return this;
    }

    @Step("Click icon delete project '{name}'")
    public DeleteModal clickDelete(String name) {
        driver.findElement(By.xpath(String.format(DELETE_ICON, name))).click();
        return new DeleteModal(driver);
    }

    //через findElements по имени
    public boolean validateNonexistentProject(String name) {
        try {
            assertEquals(name, driver.findElement(By.xpath(String.format(NAME_OF_PROJECT, name))).getText());
            return false;
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    @Step("Click icon edit project '{name}'")
    public ProjectPage clickEdit(String name) {
        driver.findElement(By.xpath(String.format(EDIT_ICON, name))).click();
        return new ProjectPage(driver);
    }
}
