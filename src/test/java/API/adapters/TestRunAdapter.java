package API.adapters;

import API.modelsAPI.TestRunAPI;
import API.modelsAPI.TestRunsAPI;

public class TestRunAdapter extends BaseAdapter {

    String uriGetRun = "get_run/";
    String uriAddTestRun = "add_run/";
    String uriCloseTestRun = "close_run/";

    public TestRunsAPI getTestRun(int testRunId) {
        return
                converter.fromJson(get(uriGetRun + testRunId), TestRunsAPI.class);
    }

    public int addTestRun(int projectID, TestRunAPI testRunAPI) {
        return
                post(uriAddTestRun + projectID, converter.toJson(testRunAPI))
                        .body().path("id");
    }

    public void closeTestRun(int testRunId) {
        post(uriCloseTestRun + testRunId, "");
    }
}
