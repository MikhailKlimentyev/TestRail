package steps;

import API.modelsAPI.ProjectAPI;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.Project;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertEquals;

@Log4j2
public class ProjectSteps extends BaseSteps {

    public ProjectSteps(WebDriver driver) {
        super(driver);
    }

    @Step("Open page with all projects")
    public ProjectSteps openProjectsPage() {
        log.info("Open page with all projects");
        projectsPage
                .openProjectsPage()
                .isPageOpened();
        return this;
    }

    @Step("Open project '{project.nameOfProject}'")
    public ProjectSteps openProject(Project project) {
        log.info("Open project(create from UI) " + project.getNameOfProject());
        projectsPage
                .openProject(project.getNameOfProject())
                .isPageOpened();
        return this;
    }

    @Step("Open project '{projectAPI.name}'")
    public ProjectSteps openProject(ProjectAPI projectAPI) {
        log.info("Open project(create from API) " + projectAPI.getName());
        projectsPage
                .openProject(projectAPI.getName())
                .isPageOpened();
        return this;
    }

    @Step("Create new project '{project.nameOfProject}'")
    public ProjectSteps createProject(Project project) {
        log.info("Create new project " + project.getNameOfProject());
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
        log.info("Delete project(created from API) " + projectAPI.getName());
        projectsPage
                .clickDelete(projectAPI.getName())
                .isModalOpened()
                .activateCheckbox()
                .clickButtonOk();
        return this;
    }

    @Step("Update project '{projectAPI.name}'")
    public ProjectSteps updateProject(ProjectAPI projectAPI, Project project) {
        log.info("Update project(created from API) " + projectAPI.getName());
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
        log.info("Create project " + projectAPI.getName() + " from API");
        projectAdapter.addProject(projectAPI);
    }

    public void deleteProjectAPI(Project project) {
        log.info("Delete project(created from UI) " + project.getNameOfProject());
        projectAdapter.deleteProject(project.getNameOfProject());
    }

    public void deleteProjectAPI(ProjectAPI projectAPI) {
        log.info("Delete project(created from API) " + projectAPI.getName());
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
        Project factProject = new Project(projectPage.getNameOfProject(), projectPage.getAnnouncement(), projectPage.getStatusCheckbox(), projectPage.getValueOfRadiobutton());
        assertEquals(project, factProject);
        return this;
    }
}
