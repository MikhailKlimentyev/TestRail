package tests;

import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import pages.DashboardPage;
import pages.LoginPage;
import pages.ProjectPage;
import pages.ProjectsPage;
import steps.ProjectSteps;
import steps.TestCasesSteps;
import utils.CapabilitiesGenerator;
import utils.TestListener;

import java.util.concurrent.TimeUnit;

@Listeners(TestListener.class)
public class BaseTest {
    WebDriver driver;
    LoginPage loginPage;
    ProjectSteps projectSteps;
    TestCasesSteps testCasesSteps;

    @BeforeClass(description = "Initialized WebDriver")
    public void setUp(ITestContext context) {

        try {
            driver = new ChromeDriver(CapabilitiesGenerator.getChromeOptions());
        } catch (SessionNotCreatedException ex) {
            Assert.fail("Браузер не был открыт. Проверьте, что используется корректная версия драйвера");
            //log.fatal(ex.getLocalizedMessage());
        }

        String variable = "driver";
        //System.out.println("Setting driver into context with variable name " + variable);
        context.setAttribute(variable, driver);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        loginPage = new LoginPage(driver);
        projectSteps = new ProjectSteps(driver);
        testCasesSteps = new TestCasesSteps(driver);
    }

    @AfterClass(description = "Close Browser")
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
