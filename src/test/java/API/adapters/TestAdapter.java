package API.adapters;

import API.modelsAPI.TestsAPI;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.NoSuchElementException;

public class TestAdapter extends BaseAdapter {

    String uriGetAll = "get_tests/";

    /**
     * Gets all tests.
     * <p>
     * Call get_tests endpoint, which returns a list of tests for a test run
     * https://www.gurock.com/testrail/docs/api/reference/tests#gettests
     *
     * @param testRunId the test run id
     * @return the all tests
     */
    public List<TestsAPI> getAllTests(int testRunId) {
        String getAllTestsJsonResponse = get(uriGetAll + testRunId);
        Type type = new TypeToken<List<TestsAPI>>() {
        }.getType();
        return converter.fromJson(getAllTestsJsonResponse, type);
    }

    public int getTestID(int testCaseId, int testRunId) {
        List<TestsAPI> tests = getAllTests(testRunId);
        for (TestsAPI test : tests) {
            if (test.getCaseId() == testCaseId) {
                return test.getId();
            }
        }
        throw new NoSuchElementException("Test not found");
    }
}
