package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProjectPage extends BasePage {

    public static final By PROJECT_TAB = By.id("projects-tabs-project");

    public ProjectPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public ProjectPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(PROJECT_TAB));
        return this;
    }


}
