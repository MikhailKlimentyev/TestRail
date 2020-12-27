package pages.testcase;

import elements.DropDown;
import io.qameta.allure.Step;
import modals.DeleteTestCaseModal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

public class NewTestCasePage extends BasePage {

    public static final By TITLE_INPUT = By.id("title");
    public static final By ESTIMATE_INPUT = By.id("estimate");
    public static final By REFERENCES_INPUT = By.id("refs");
    public static final By PRECONDITIONS_TEXTAREA = By.id("custom_preconds");
    public static final By STEPS_TEXTAREA = By.id("custom_steps");
    public static final By EXPECTED_RESULT_TEXTAREA = By.id("custom_expected");
    public static final By ADD_TEST_CASE_BUTTON = By.id("accept");
    public static final By DELETE_BUTTON = By.xpath("//a[contains(text(),'Delete this test case')]");

    public NewTestCasePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public NewTestCasePage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_TEST_CASE_BUTTON));
        return this;
    }

    @Step("Set '{name}' into field Title")
    public NewTestCasePage setTitle(String name) {
        driver.findElement(TITLE_INPUT).clear();
        driver.findElement(TITLE_INPUT).sendKeys(name);
        return this;
    }

    @Step("Set '{type}' into dropdown Type")
    public NewTestCasePage setType(String type) {
        new DropDown(driver,"Type").select(type);
        return this;
    }

    @Step("Set '{priority}' into dropdown Priority")
    public NewTestCasePage setPriority(String priority) {
        new DropDown(driver,"Priority").select(priority);
        return this;
    }

    @Step("Set '{estimate}' into field Estimate")
    public NewTestCasePage setEstimate(String estimate) {
        driver.findElement(ESTIMATE_INPUT).clear();
        driver.findElement(ESTIMATE_INPUT).sendKeys(estimate);
        return this;
    }

    @Step("Set '{references}' into field References")
    public NewTestCasePage setReferences(String references) {
        driver.findElement(REFERENCES_INPUT).clear();
        driver.findElement(REFERENCES_INPUT).sendKeys(references);
        return this;
    }

    @Step("Set '{automationType}' into dropdown Automation Type")
    public NewTestCasePage setAutomationType(String automationType) {
        new DropDown(driver,"Automation Type").select(automationType);
        return this;
    }

    @Step("Set '{preconditions}' into field Preconditions")
    public NewTestCasePage setPreconditions(String preconditions) {
        driver.findElement(PRECONDITIONS_TEXTAREA).clear();
        driver.findElement(PRECONDITIONS_TEXTAREA).sendKeys(preconditions);
        return this;
    }

    @Step("Set '{steps}' into field References")
    public NewTestCasePage setSteps(String steps) {
        driver.findElement(STEPS_TEXTAREA).clear();
        driver.findElement(STEPS_TEXTAREA).sendKeys(steps);
        return this;
    }

    @Step("Set '{expectedResult}' into field Expected Result")
    public NewTestCasePage setExpectedResult(String expectedResult) {
        driver.findElement(EXPECTED_RESULT_TEXTAREA).clear();
        driver.findElement(EXPECTED_RESULT_TEXTAREA).sendKeys(expectedResult);
        return this;
    }

    @Step("Click button 'Add Test Case'")
    public ViewTestCasePage clickButtonAddTestCase() {
        driver.findElement(ADD_TEST_CASE_BUTTON).click();
        return new ViewTestCasePage(driver);
    }

    @Step("Click button 'Delete this test case'")
    public DeleteTestCaseModal clickButtonDelete() {
        driver.findElement(DELETE_BUTTON).click();
        return new DeleteTestCaseModal(driver);
    }
}
