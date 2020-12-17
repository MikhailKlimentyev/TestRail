package API.adapters;

import API.models.Project;

public class ProjectAdapter extends BaseAdapter {

    String uri = "v2/add_project";

    public int addProject(Project project) {
        return
                post(uri, converter.toJson(project))
                        .body().path("id");
    }

}
