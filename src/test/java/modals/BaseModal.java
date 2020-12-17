package modals;

import org.openqa.selenium.WebDriver;

public abstract class BaseModal {
    public WebDriver driver;

    public BaseModal(WebDriver driver) {
        this.driver = driver;
    }
}
