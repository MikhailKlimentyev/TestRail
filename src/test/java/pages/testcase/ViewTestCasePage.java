package pages.testcase;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;
import utils.Utils;

import java.util.NoSuchElementException;

import static java.lang.Thread.sleep;

public class ViewTestCasePage extends BasePage {

    public static final By TITLE_OF_TEST_CASE = By.cssSelector(".page_title");
    public static final String BLOCK_OF_TEST_CASE = "//label[contains(text(),'%s')]/ancestor::td";
    public static final String PRECONDITION_OF_TEST_CASE = "//p[contains(text(),'%s')]";
    public static final String STEPS_OF_TEST_CASE = "//p[contains(text(),'%s')]";
    public static final String EXPECTED_RESULT_OF_TEST_CASE = "//p[contains(text(),'%s')]";
    public static final By EDIT_BUTTON = By.xpath("//a[contains(@class,'button-edit')]");

    public ViewTestCasePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public ViewTestCasePage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE_OF_TEST_CASE));
        return this;
    }

    public String getTitle() {
       return driver.findElement(TITLE_OF_TEST_CASE).getText();
    }

    public String getType(String name) {
            return Utils.parseStr(driver.findElement(By.xpath(String.format(BLOCK_OF_TEST_CASE, name))).getAttribute("innerText"), name);
    }

    public String getPriority(String name) {
            return Utils.parseStr(driver.findElement(By.xpath(String.format(BLOCK_OF_TEST_CASE, name))).getAttribute("innerText"), name);
    }

    public String getEstimate(String name){
            return Utils.parseStr(driver.findElement(By.xpath(String.format(BLOCK_OF_TEST_CASE, name))).getAttribute("innerText"), name);
    }

    public String getReferences(String name) {
            return Utils.parseStr(driver.findElement(By.xpath(String.format(BLOCK_OF_TEST_CASE, name))).getAttribute("innerText"), name);
    }

    public String getAutomationType(String name) {
            return Utils.parseStr(driver.findElement(By.xpath(String.format(BLOCK_OF_TEST_CASE, name))).getAttribute("innerText"), name);
    }

    public String getPreconditions(String name) {
        if(driver.findElement(By.xpath(String.format(PRECONDITION_OF_TEST_CASE, name))).getText().equals(name)){
            return driver.findElement(By.xpath(String.format(PRECONDITION_OF_TEST_CASE, name))).getText();
        }
        throw new NoSuchElementException("Preconditions not found");
    }

    public String getSteps(String name) {
        if(driver.findElement(By.xpath(String.format(STEPS_OF_TEST_CASE, name))).getText().equals(name)){
            return driver.findElement(By.xpath(String.format(STEPS_OF_TEST_CASE, name))).getText();
        }
        throw new NoSuchElementException("Steps not found");
    }

    public String getExpectedResult(String name) {
        if(driver.findElement(By.xpath(String.format(EXPECTED_RESULT_OF_TEST_CASE, name))).getText().equals(name)){
            return driver.findElement(By.xpath(String.format(EXPECTED_RESULT_OF_TEST_CASE, name))).getText();
        }
        throw new NoSuchElementException("Expected Result not found");
    }

    @Step("Click button 'Edit'")
    public NewTestCasePage clickButtonEdit() {
        driver.findElement(EDIT_BUTTON).click();
        return new NewTestCasePage(driver);
    }

}
