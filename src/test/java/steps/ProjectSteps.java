package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class ProjectSteps extends BaseSteps{

    public ProjectSteps(WebDriver driver) {
        super(driver);
    }

    @Step("Create new project '{name}'")
    public void createProject(String name, String announcement, boolean showAnnouncement, String radio) {
        dashboardPage
                .openProjectPage()
                .isPageOpened()
                .setNameOfProject(name)
                .setAnnouncement(announcement)
                .activateCheckbox(showAnnouncement)
                .chooseRadiobutton(radio)
                .clickAddProjectButton();
    }

    @Step("Delete project '{name}'")
    public void deleteProject(String name) {
        projectsPage
                .openProjectsPage()
                .isPageOpened()
                .clickDelete(name)
                .activateCheckbox()
                .clickButtonOk();
    }

    @Step("Update project '{nameOfProject}'")
    public void updateProject(String nameOfProject, String updatedName, String updatedAnnouncement, boolean updatedShowAnnouncement, String updatedRadio) {
        projectsPage
                .openProjectsPage()
                .isPageOpened()
                .clickEdit(nameOfProject)
                .setNameOfProject(updatedName)
                .setAnnouncement(updatedAnnouncement)
                .activateCheckbox(updatedShowAnnouncement)
                .chooseRadiobutton(updatedRadio)
                .clickAddProjectButton();
    }
}
