package API.adapters;

import API.modelsAPI.TestRunAPI;
import API.modelsAPI.TestRunsAPI;

public class TestRunAdapter extends BaseAdapter {

    String uriGetRun = "get_run/";
    String uriAddTestRun = "add_run/";
    String uriCloseTestRun = "close_run/";

    /**
     * Gets test run.
     * <p>
     * Returns an existing test run by run id
     * https://www.gurock.com/testrail/docs/api/reference/runs#addrun
     *
     * @param testRunId the test run id
     * @return the test run
     */
    public TestRunsAPI getTestRunId(int testRunId) {
        String uri = uriGetRun + testRunId;
        String getTestRunJsonResponse = get(uri);
        return converter.fromJson(getTestRunJsonResponse, TestRunsAPI.class);
    }

    /**
     * Add test run int.
     * <p>
     * Creates a new test run.
     * https://www.gurock.com/testrail/docs/api/reference/runs#addrun
     *
     * @param projectID  the project id
     * @param testRunAPI payload
     * @return test run id
     */
    public int addTestRun(int projectID, TestRunAPI testRunAPI) {
        String uri = uriAddTestRun + projectID;
        String body = converter.toJson(testRunAPI);
        return post(uri, body)
                .getBody()
                .path("id");
    }

    public void closeTestRun(int testRunId) {
        post(uriCloseTestRun + testRunId, "");
    }
}
