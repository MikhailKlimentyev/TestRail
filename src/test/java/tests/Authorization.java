package tests;

import org.testng.annotations.BeforeClass;
import utils.PropertyReader;


public class Authorization extends BaseTest {

    @BeforeClass(description = "Authorization")
    public void authorization() {
        loginPage
                .openPage()
                .isPageOpened()
                .setEmail(System.getenv().getOrDefault("user", PropertyReader.getProperty("user")))
                .setPassword(System.getenv().getOrDefault("pass", PropertyReader.getProperty("pass")))
                .clickLoginButton()
                .isPageOpened();
    }
}
