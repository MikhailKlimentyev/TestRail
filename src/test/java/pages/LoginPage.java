package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.PropertyReader;

public class LoginPage extends BasePage {

    public static final By EMAIL_INPUT = By.id("name");
    public static final By PASSWORD_INPUT = By.id("password");
    public static final By LOGIN_BUTTON = By.id("button_primary");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public LoginPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
        return this;
    }

    @Step("Open TestRail")
    public LoginPage openPage() {
        driver.get(System.getenv().getOrDefault("url", PropertyReader.getProperty("url")));
        return this;
    }

    @Step("Input '{email}'")
    public LoginPage setEmail(String email) {
        driver.findElement(EMAIL_INPUT).sendKeys(email);
        return this;
    }

    @Step("Input '{password}'")
    public LoginPage setPassword(String password) {
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        return this;
    }

    @Step("Click button 'Log In'")
    public DashboardPage clickLoginButton() {
        driver.findElement(LOGIN_BUTTON).click();
        return new DashboardPage(driver);
    }
}
