package API.adapters;

import API.modelsAPI.ProjectAPI;
import API.modelsAPI.ProjectsAPI;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.NoSuchElementException;

public class ProjectAdapter extends BaseAdapter {

    String uriAdd = "add_project";
    String uriGetAll = "get_projects";
    String uriDel = "delete_project/";

    /**
     * Add project int.
     * <p>
     * Calls creates a new project endpoint
     * https://www.gurock.com/testrail/docs/api/reference/projects#addproject
     * Returns created project id
     *
     * @param project the project
     * @return the int
     */
    public int addProject(ProjectAPI project) {
        String body = converter.toJson(project);
        return post(uriAdd, body)
                .getBody()
                .path("id");
    }

    public List<ProjectsAPI> getAllProjects() {
        return converter.fromJson(get(uriGetAll), new TypeToken<List<ProjectsAPI>>() {
        }.getType());
    }

    public int getProjectID(String projectName) {
        List<ProjectsAPI> projects = getAllProjects();
        for (ProjectsAPI project : projects) {
            if ((project.getName()).equals(projectName)) {
                return project.getId();
            }
        }
        throw new NoSuchElementException("Project not found");
    }

    public void deleteProject(String name) {
        post((uriDel + getProjectID(name)), converter.toJson(getProjectID(name)));
    }

}
