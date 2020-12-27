package steps;

import API.adapters.ProjectAdapter;
import API.modelsAPI.ProjectAPI;
import io.qameta.allure.Step;
import models.Project;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertEquals;

public class ProjectSteps extends BaseSteps {

    public ProjectSteps(WebDriver driver) {
        super(driver);
    }

    @Step("Open page with all projects")
    public ProjectSteps openProjectsPage() {
        projectsPage
                .openProjectsPage()
                .isPageOpened();
        return this;
    }

    @Step("Open project '{project.nameOfProject}'")
    public ProjectSteps openProject(Project project) {
        projectsPage
                .openProject(project.getNameOfProject())
                .isPageOpened();
        return this;
    }

    @Step("Create new project '{project.nameOfProject}'")
    public ProjectSteps createProject(Project project) {
        dashboardPage
                .openProjectPage()
                .isPageOpened()
                .setNameOfProject(project.getNameOfProject())
                .setAnnouncement(project.getAnnouncement())
                .activateCheckbox(project.isShowAnnouncement())
                .chooseRadiobutton(project.getRadio())
                .clickAddProjectButton();
        return this;
    }

    @Step("Delete project '{projectAPI.name}'")
    public ProjectSteps deleteProject(ProjectAPI projectAPI) {
        projectsPage
                .clickDelete(projectAPI.getName())
                .activateCheckbox()
                .clickButtonOk();
        return this;
    }

    @Step("Update project '{projectAPI.name}'")
    public ProjectSteps updateProject(ProjectAPI projectAPI, Project project) {
        projectsPage
                .clickEdit(projectAPI.getName())
                .setNameOfProject(project.getNameOfProject())
                .setAnnouncement(project.getAnnouncement())
                .activateCheckbox(project.isShowAnnouncement())
                .chooseRadiobutton(project.getRadio())
                .clickAddProjectButton();
        return this;
    }

    public void createProjectAPI(ProjectAPI projectAPI) {
        projectAdapter.addProject(projectAPI);
    }

    public void deleteProjectAPI(Project project) {
        projectAdapter.deleteProject(project.getNameOfProject());
    }

    public void deleteProjectAPI(ProjectAPI projectAPI) {
        projectAdapter.deleteProject(projectAPI.getName());
    }
  
    public ProjectSteps validateIsProjectExisted(Project project) {
        assertEquals(projectsPage.numberOfProjectsByName(project.getNameOfProject()), 1);
        return this;
    }

    public ProjectSteps validateIsProjectNotExisted(ProjectAPI projectAPI) {
        assertEquals(projectsPage.numberOfProjectsByName(projectAPI.getName()), 0);
        return this;
    }

    public ProjectSteps validateIsProjectUpdated(Project project) {
        Project factProject = new Project(projectPage.getNameOfProject(), projectPage.getAnnouncement(), projectPage.getStatusCheckbox(),projectPage.getValueOfRadiobutton());
        assertEquals(project,factProject);
        return this;
    }
}
