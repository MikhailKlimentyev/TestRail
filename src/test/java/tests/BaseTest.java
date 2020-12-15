package tests;

import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.DashboardPage;
import pages.LoginPage;
import pages.ProjectPage;
import utils.CapabilitiesGenerator;

public class BaseTest {
    WebDriver driver;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    ProjectPage projectPage;

    @BeforeClass
    public void setUp() {
        try {
            driver = new ChromeDriver(CapabilitiesGenerator.getChromeOptions());
        } catch (SessionNotCreatedException ex) {
            Assert.fail("Браузер не был открыт. Проверьте, что используется корректная версия драйвера");
            //log.fatal(ex.getLocalizedMessage());
        }
        //steps = new GoogleSteps(driver);
        //String variable = "driver";
        //System.out.println("Setting driver into context with variable name " + variable);
        //context.setAttribute(variable, driver);

        //driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        projectPage = new ProjectPage(driver);
    }

    @AfterClass(description = "Close Browser")
    public void closeBrowser() {
        driver.quit();
    }
}
