package tests;

import API.adapters.ProjectAdapter;
import API.models.Project;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ProjectTest extends Authorization {

    @Test(description = "Create new project")
    public void isNewProjectCreated() {
        projectSteps.createProject("New Project","Project for test",true,"Use a single repository for all cases (recommended)");
        projectsPage
                .openProjectsPage()
                .isPageOpened()
                .validateExistentProject("New Project1");

        //сделать удаление через АПИ!!!

    }

    @Test(description = "Delete project")
    public void isProjectDeleted() {

        Project project = Project.builder()
                .name("Marys project")
                .announcement("The best project")
                .show_announcement(true)
                .suite_mode(1)
                .build();

        new ProjectAdapter().addProject(project);

        projectSteps.deleteProject("Marys project");
        projectsPage
                .openProjectsPage()
                .isPageOpened();
        //обработать это место получше
        assertTrue(projectsPage.validateNonexistentProject("Marys1 project"));
    }

    @Test(description = "Update project")
    public void isProjectUpdated() {

        Project project = Project.builder()
                .name("Marys project")
                .announcement("The best project")
                .show_announcement(true)
                .suite_mode(1)
                .build();

        new ProjectAdapter().addProject(project);

        projectSteps.updateProject("Marys project","Updated project","update project",false,"");

        projectPage
                .returnToDashboardPage()
                .openProject("Updated project");

        //дописать асерт
        // удалить через АПИ
    }
}
