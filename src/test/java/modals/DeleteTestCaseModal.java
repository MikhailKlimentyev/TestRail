package modals;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.testcase.TestCasesPage;

public class DeleteTestCaseModal extends BaseModal {

    public static final By BUTTON_OK = By.xpath("//div[contains(@id,'deleteDialog')]//a[contains(@class,'button-ok')]");

    public DeleteTestCaseModal(WebDriver driver) {
        super(driver);
    }

    @Override
    public DeleteTestCaseModal isModalOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(BUTTON_OK));
        return this;
    }

    @Step("Click button 'OK' on modal 'Confirmation'")
    public TestCasesPage clickButtonOk() {
        driver.findElement(BUTTON_OK).click();
        return new TestCasesPage(driver);
    }

}
