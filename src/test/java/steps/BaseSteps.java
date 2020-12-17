package steps;

import org.openqa.selenium.WebDriver;
import pages.DashboardPage;
import pages.LoginPage;
import pages.ProjectPage;
import pages.ProjectsPage;

public abstract class BaseSteps {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    ProjectPage projectPage;
    ProjectsPage projectsPage;

    public BaseSteps(WebDriver driver) {
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        projectPage = new ProjectPage(driver);
        projectsPage = new ProjectsPage(driver);
    }

}
