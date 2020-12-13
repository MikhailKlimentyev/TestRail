package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.LoginPage;

public class BaseTest {
    WebDriver driver;
    LoginPage loginPage;

    @BeforeClass
    public void setUp() {

        if (System.getProperty("os.name").contains("Windows")){
            System.setProperty("webdriver.chrome.driver", "src/test/resources/WebDrivers/Windows/chromedriver.exe");
        } else if (System.getProperty("os.name").contains("Mac")){
            System.setProperty("webdriver.chrome.driver", "src/test/resources/WebDrivers/MAC/chromedriver");
        }
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
    }

    @AfterClass(alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
    }
}
