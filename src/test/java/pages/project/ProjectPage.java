package pages.project;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;
import pages.DashboardPage;
import pages.testcase.TestCasesPage;

public class ProjectPage extends BasePage {

    public static final By PROJECT_TAB = By.id("projects-tabs-project");
    public static final By TEST_CASES_TAB = By.id("navigation-suites");

    public static final By RETURN_TO_DASHBOARD_BUTTON = By.id("navigation-dashboard-top");
    public static final By DASHBOARD_TAB = By.id("navigation-dashboard");

    //public static final By PROJECT_TITLE = By.cssSelector(".page_title");
    //public static final By PROJECT_ANNOUNCEMENT = By.xpath("//div[contains(@class,'markdown')]//p");

    public ProjectPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public ProjectPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(PROJECT_TAB));
        return this;
    }

    @Step("Click tab TEST CASES")
    public TestCasesPage clickTabTestCases() {
        driver.findElement(TEST_CASES_TAB).click();
        return new TestCasesPage(driver);
    }

    public DashboardPage returnToDashboardPage() {
        driver.findElement(DASHBOARD_TAB).click();
        return new DashboardPage(driver);
    }
}