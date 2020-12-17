package API.adapters;

import API.models.Project;
import API.models.Projects;

import java.util.List;

public class ProjectAdapter extends BaseAdapter {

    String uriAdd = "add_project";
    String uriGetAll = "get_projects";
    String uriDel = "delete_project/";

    public int addProject(Project project) {
        return
                post(uriAdd, converter.toJson(project))
                        .body().path("id");
    }

    public String getProjects() {
        return
                get(uriGetAll);
    }

   public int getIdProject(List<Projects> projects, String name) {
       for (Projects project: projects) {
           if((project.getName()).equals(name)){
               return project.getId();
           }
       }
       return 0;
    }

    public void deleteProject(int id) {
        post((String.format(uriDel+id)), converter.toJson(id));
    }

}
