package tests;

import API.modelsAPI.ProjectAPI;
import models.Project;
import org.testng.annotations.Test;

public class ProjectTest extends Authorization {

    Project newProject = new Project("New Project", "Project for test",
            true, "Use a single repository for all cases (recommended)");
    ProjectAPI newProjectAPI = ProjectAPI.builder()
            .name("Marys Project")
            .announcement("project fo test create project")
            .showAnnouncement(true)
            .suiteMode(1)
            .build();

    @Test(description = "Create new project")
    public void isNewProjectCreated() {
        projectSteps
                .createProject(newProject)
                .openProjectsPage()
                .validateExistentProject(newProject)
                .deleteProjectAPI(newProject);
    }

    @Test(description = "Delete project")
    public void isProjectDeleted() {
        projectSteps
                .createProjectAPI(newProjectAPI);
        projectSteps
                .openProjectsPage()
                .deleteProject(newProjectAPI)
                .openProjectsPage()
                .validateNonexistentProject(newProjectAPI);
    }

    @Test(description = "Update project")
    public void isProjectUpdated() {
        projectSteps.createProjectAPI(newProjectAPI);
        projectSteps
                .openProjectsPage()
                .updateProject(newProjectAPI, newProject)
                .openProjectsPage()
                .validateExistentProject(newProject);
        projectSteps.deleteProjectAPI(newProject);
    }
}
