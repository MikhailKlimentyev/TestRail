package pages.testcase;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

public class TestCasesPage extends BasePage {

    public TestCasesPage(WebDriver driver) {
        super(driver);
    }

    public static final By ADD_TEST_CASE_BUTTON = By.id("sidebar-cases-add");

    @Override
    public TestCasesPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_TEST_CASE_BUTTON));
        return this;
    }

    @Step("Click button 'Add Test Case'")
    public NewTestCasePage openNewTestCasePage() {
        driver.findElement(ADD_TEST_CASE_BUTTON).click();
        return new NewTestCasePage(driver);
    }

}
