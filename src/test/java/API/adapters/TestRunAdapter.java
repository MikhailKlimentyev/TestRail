package API.adapters;

import API.modelsAPI.TestRunAPI;

public class TestRunAdapter extends BaseAdapter{

    String uriAddTestRun = "add_run/";
    String uriCloseTestRun = "close_run/";

    public int addTestRun(int projectID, TestRunAPI testRunAPI) {
        return
                post(uriAddTestRun + projectID, converter.toJson(testRunAPI))
                        .body().path("id");
    }

    public void closeTestRun(int testRunId) {
                post(uriCloseTestRun + testRunId, "");
    }
}
