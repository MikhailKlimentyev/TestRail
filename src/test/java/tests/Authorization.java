package tests;

import org.testng.annotations.BeforeClass;

import static data.TestData.EMAIL;
import static data.TestData.PASSWORD;

public class Authorization extends BaseTest {

    @BeforeClass(description = "Authorization")
    public void authorization() {
        loginPage
                .openPage()
                .isPageOpened()
                .setEmail(EMAIL)
                .setPassword(PASSWORD)
                .clickLoginButton()
                .isPageOpened();
    }
}
