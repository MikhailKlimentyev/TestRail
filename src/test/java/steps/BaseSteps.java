package steps;

import API.adapters.ProjectAdapter;
import API.adapters.TestCasesAdapter;
import org.openqa.selenium.WebDriver;
import pages.*;
import pages.testcase.NewTestCasePage;
import pages.testcase.TestCasesPage;
import pages.testcase.ViewTestCasePage;

public abstract class BaseSteps {

    LoginPage loginPage;
    DashboardPage dashboardPage;
    ProjectPage projectPage;
    ProjectsPage projectsPage;
    TestCasesPage testCasesPage;
    NewTestCasePage newTestCasePage;
    ViewTestCasePage viewTestCasePage;
    ProjectAdapter projectAdapter;
    TestCasesAdapter testCasesAdapter;

    public BaseSteps(WebDriver driver) {
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        projectPage = new ProjectPage(driver);
        projectsPage = new ProjectsPage(driver);
        testCasesPage = new TestCasesPage(driver);
        newTestCasePage = new NewTestCasePage(driver);
        viewTestCasePage = new ViewTestCasePage(driver);
        projectAdapter = new ProjectAdapter();
        testCasesAdapter = new TestCasesAdapter();
    }

}
