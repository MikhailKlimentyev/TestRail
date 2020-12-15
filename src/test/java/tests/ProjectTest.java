package tests;

import org.testng.annotations.Test;

public class ProjectTest extends Authorization {

    @Test(description = "Create new project")
    public void isNewProjectCreated() {
        dashboardPage
                .openProjectPage()
                .isPageOpened();
    }
}
