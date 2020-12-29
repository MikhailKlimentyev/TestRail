package API.adapters;

import API.modelsAPI.TestsAPI;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.NoSuchElementException;

public class TestAdapter extends BaseAdapter {

    String uriGetAll = "get_tests/";

    public List<TestsAPI> getAllTests(int testRunId) {
        return
                converter.fromJson(get(uriGetAll + testRunId), new TypeToken<List<TestsAPI>>() {
                }.getType());
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
