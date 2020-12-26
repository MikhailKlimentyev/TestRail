package pages.testcase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

public class ViewTestCasePage extends BasePage {

    public static final By TITLE_OF_TEST_CASE = By.cssSelector(".page_title");

    public ViewTestCasePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public ViewTestCasePage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE_OF_TEST_CASE));
        return this;
    }
}
