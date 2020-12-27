package pages.testcase;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;
import utils.Utils;

import java.util.List;

public class TestCasesPage extends BasePage {

    public TestCasesPage(WebDriver driver) {
        super(driver);
    }

    public static final By ADD_TEST_CASE_BUTTON = By.id("sidebar-cases-add");
    public static final String NAME_OF_TEST_CASE = "//table//span[contains(text(),'%s')]";

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

    @Step("Open test case '{name}'")
    public ViewTestCasePage openCreatedTestCase(String name) {
        driver.findElement(By.xpath(String.format(NAME_OF_TEST_CASE,name))).click();
        return new ViewTestCasePage(driver);
    }

    public int numberOfTestCasesByName(String name) {
        return Utils.numberOfIssuesByName(driver.findElements(By.xpath(String.format(NAME_OF_TEST_CASE, name))), name);
    }
}
