package modals;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DeleteModal extends BaseModal {

    public static final By CHECKBOX = By.xpath("//div[contains(@id, 'deleteDialog')]//input[contains(@name,'deleteCheckbox')]");
    public static final By BUTTON_OK = By.xpath("//div[contains(@id, 'deleteDialog')]//a[contains(@class,'button-ok')]");

    public DeleteModal(WebDriver driver) {
        super(driver);
    }

    @Step("Activate checkbox")
    public DeleteModal activateCheckbox() {
        driver.findElement(CHECKBOX).click();
        return this;
    }

    @Step("Click button OK")
    public DeleteModal clickButtonOk() {
        driver.findElement(BUTTON_OK).click();
        return this;
    }
}
