package pages.testcase;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;
import utils.Utils;

public class ViewTestCasePage extends BasePage {

    public static final By TITLE_OF_TEST_CASE = By.cssSelector(".page_title");
    public static final String BLOCK_OF_TEST_CASE = "//label[contains(text(),'%s')]/ancestor::td";
    public static final String PRECONDITION_OF_TEST_CASE = "//*[@id='content-inner']/div[4]/div/p";
    public static final String STEPS_OF_TEST_CASE = "//*[@id='content-inner']/div[6]/div/p";
    public static final String EXPECTED_RESULT_OF_TEST_CASE = "//*[@id='content-inner']/div[8]/div/p";


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
        String s = driver.findElement(By.xpath(String.format(BLOCK_OF_TEST_CASE,name))).getText();
        return Utils.parseStr(s,name);
    }

    public String getPriority(String name) {
        String s = driver.findElement(By.xpath(String.format(BLOCK_OF_TEST_CASE,name))).getText();
        return Utils.parseStr(s,name);
    }

    public String getEstimate(String name) {
        return  Utils.parseStr(driver.findElement(By.xpath(String.format(BLOCK_OF_TEST_CASE,name))).getText(),name);
    }

    public String getReferences(String name) {
        return Utils.parseStr(driver.findElement(By.xpath(String.format(BLOCK_OF_TEST_CASE,name))).getText(),name);
    }

    public String getAutomationType(String name) {
        String s = driver.findElement(By.xpath(String.format(BLOCK_OF_TEST_CASE,name))).getText();
        return Utils.parseStr(s,name);
    }

    public String getPreconditions() {
        return driver.findElement(By.xpath(PRECONDITION_OF_TEST_CASE)).getText();
    }

    public String getSteps() {
        return driver.findElement(By.xpath(STEPS_OF_TEST_CASE)).getText();
    }

    public String getExpectedResult() {
        return driver.findElement(By.xpath(EXPECTED_RESULT_OF_TEST_CASE)).getText();
    }
}
