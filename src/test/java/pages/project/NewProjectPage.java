package pages.project;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

public class NewProjectPage extends BasePage {

    public static final By NAME_INPUT = By.id("name");
    public static final By ANNOUNCEMENT_TEXTAREA = By.id("announcement");
    public static final By ANNOUNCEMENT_CHECKBOX = By.id("show_announcement");
    public static final String RADIOBUTTON_GROUP = "//div[contains(@class,'row add-project-row')]/descendant::strong[contains(text(),'%s')]";
    public static final String NAME_OF_CHECKED_RADIOBUTTON = "//div[contains(@class, 'radio')]//input[contains(@checked, 'checked')]//ancestor::label/strong";
    public static final By ADD_PROJECT_BUTTON = By.id("accept");

    public NewProjectPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public NewProjectPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_PROJECT_BUTTON));
        return this;
    }

    public String getNameOfProject() {
        return driver.findElement(NAME_INPUT).getAttribute("value");
    }

    @Step("Set '{name}' into field Name")
    public NewProjectPage setNameOfProject(String name) {
        driver.findElement(NAME_INPUT).clear();
        driver.findElement(NAME_INPUT).sendKeys(name);
        return this;
    }

    public String getAnnouncement() {
        return driver.findElement(ANNOUNCEMENT_TEXTAREA).getText();
    }

    @Step("Set '{announcement}' into field Announcement")
    public NewProjectPage setAnnouncement(String announcement) {
        driver.findElement(ANNOUNCEMENT_TEXTAREA).clear();
        driver.findElement(ANNOUNCEMENT_TEXTAREA).sendKeys(announcement);
        return this;
    }

    @Step("Activate checkbox 'Show announcement'")
    public NewProjectPage activateCheckbox(boolean showAnnouncement) {
        if (showAnnouncement) {
            if (!driver.findElement(ANNOUNCEMENT_CHECKBOX).isSelected()) {
                driver.findElement(ANNOUNCEMENT_CHECKBOX).click();
            }
        } else {
            if (driver.findElement(ANNOUNCEMENT_CHECKBOX).isSelected()) {
                driver.findElement(ANNOUNCEMENT_CHECKBOX).click();
            }
        }
        return this;
    }

    public boolean getStatusCheckbox() {
        return driver.findElement(ANNOUNCEMENT_CHECKBOX).isSelected();
    }

    @Step("Choose radiobutton '{radio}'")
    public NewProjectPage chooseRadiobutton(String radio) {
        driver.findElement(By.xpath(String.format(RADIOBUTTON_GROUP, radio))).click();
        return this;
    }

    public String getValueOfRadiobutton() {
        return driver.findElement(By.xpath(NAME_OF_CHECKED_RADIOBUTTON)).getText();
    }

    @Step("Click button 'Add Project'")
    public NewProjectPage clickAddProjectButton() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView()", driver.findElement(ADD_PROJECT_BUTTON));
        driver.findElement(ADD_PROJECT_BUTTON).click();
        return this;
    }
}
