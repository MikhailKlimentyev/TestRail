package tests;

import API.modelsAPI.ProjectAPI;
import models.Project;
import org.testng.annotations.Test;
import utils.TestRail;

public class ProjectTest extends Authorization {



    Project newProject = new Project("New Project", "Project for test",
            true, "Use a single repository for all cases (recommended)");
    ProjectAPI newProjectAPI = ProjectAPI.builder()
            .name("New Project from API")
            .announcement("project fo test create project")
            .showAnnouncement(true)
            .suiteMode(1)
            .build();

    @TestRail(testCaseID = {6})
    @Test(description = "Create new project")
    public void isNewProjectCreated() {
        projectSteps
                .createProject(newProject)
                .openProjectsPage()
                .validateIsProjectExisted(newProject)
                .deleteProjectAPI(newProject);
    }

    @TestRail(testCaseID = {7})
    @Test(description = "Delete project")
    public void isProjectDeleted() {
        projectSteps
                .createProjectAPI(newProjectAPI);
        projectSteps
                .openProjectsPage()
                .deleteProject(newProjectAPI)
                .openProjectsPage()
                .validateIsProjectNotExisted(newProjectAPI);
    }

    @TestRail(testCaseID = {8})
    @Test(description = "Update project")
    public void isProjectUpdated() {
        projectSteps.createProjectAPI(newProjectAPI);
        projectSteps
                .openProjectsPage()
                .updateProject(newProjectAPI, newProject)
                .openProjectsPage()
                .openProject(newProject)
                .validateIsProjectUpdated(newProject);
        projectSteps.deleteProjectAPI(newProject);
    }
}
