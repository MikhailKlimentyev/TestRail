package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static data.TestData.EMAIL;
import static data.TestData.PASSWORD;

public class AuthorizationTest extends BaseTest{

    @Test
    public void authorization () {
        loginPage.openPage()
                .isPageOpened()
                .setEmail(EMAIL)
                .setPassword(PASSWORD)
                .clickLoginButton();
    }
}
