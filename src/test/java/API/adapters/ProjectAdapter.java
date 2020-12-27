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

    public int addProject(ProjectAPI project) {
        return
                post(uriAdd, converter.toJson(project))
                        .body().path("id");
    }

    public List<ProjectsAPI> getAllProjects() {
        return
                converter.fromJson(get(uriGetAll), new TypeToken<List<ProjectsAPI>>() {}.getType());
    }

    public int getProjectID (String name) {

        List<ProjectsAPI> projects = getAllProjects();

        for (ProjectsAPI project : projects) {
            if ((project.getName()).equals(name)) {
                return project.getId();
            }
        }
        throw new NoSuchElementException ("Project not found");

    }

    public void deleteProject(String name) {
        post((uriDel + getProjectID(name)), converter.toJson(getProjectID(name)));
    }

}
