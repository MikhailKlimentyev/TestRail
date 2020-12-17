package tests;

import API.adapters.ProjectAdapter;
import API.models.Project;
import API.models.Projects;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class ProjectTest extends Authorization {

    Gson gson = new GsonBuilder()
            .excludeFieldsWithModifiers()
            .create();

    @Test(description = "Create new project")
    public void isNewProjectCreated() {

        projectSteps.createProject("New Project","Project for test",true,"Use a single repository for all cases (recommended)");
        projectsPage
                .openProjectsPage()
                .isPageOpened()
                .validateExistentProject("New Project");

        //очень некрасиво, подумать как сделать лучше
        String s = new ProjectAdapter().getProjects();
        List<Projects> projects = gson.fromJson(s, new TypeToken<List<Projects>>(){}.getType());

        int id = new ProjectAdapter().getIdProject(projects,"New Project");
        new ProjectAdapter().deleteProject(id);
    }

    @Test(description = "Delete project")
    public void isProjectDeleted() {

        Project project = Project.builder()
                .name("Marys project")
                .announcement("The best project")
                .showAnnouncement(true)
                .suiteMode(1)
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
                .showAnnouncement(true)
                .suiteMode(1)
                .build();

        new ProjectAdapter().addProject(project);

        projectSteps.updateProject("Marys project","Updated project","update project",false,"");

        projectPage
                .returnToDashboardPage()
                .openProject("Updated project");

        //дописать асерт

        String s = new ProjectAdapter().getProjects();
        List<Projects> projects = gson.fromJson(s, new TypeToken<List<Projects>>(){}.getType());

        int id = new ProjectAdapter().getIdProject(projects,"Updated project");
        new ProjectAdapter().deleteProject(id);
    }
}
