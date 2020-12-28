package API.adapters;

import API.modelsAPI.TestCasesAPI;
import API.modelsAPI.TestRunAPI;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.NoSuchElementException;

public class TestRunAdapter extends BaseAdapter{

    String uriAddTestRun = "add_run/";
    String uriCloseTestRun = "close_run/";
   // String uriGetAllTestsCases = "get_cases/";
   // String uriDelTestCase = "delete_case/";

    public int addTestRun(int projectID, TestRunAPI testRunAPI) {
        return
                post(uriAddTestRun + projectID, converter.toJson(testRunAPI))
                        .body().path("id");
    }

    public void closeTestRun(int testRunId) {
                post(uriCloseTestRun + testRunId, "");
    }

   /* public List<TestCasesAPI> getAllTestCases(int projectID) {
        return
                converter.fromJson(get(uriGetAllTestsCases + projectID), new TypeToken<List<TestCasesAPI>>() {
                }.getType());
    }

    public int getTestCaseID(int projectID, String name) {

        List<TestCasesAPI> testCases = getAllTestCases(projectID);

        for (TestCasesAPI testCase : testCases) {
            if (testCase.getTitle().equals(name)) {
                return testCase.getId();
            }
        }
        throw new NoSuchElementException("Test Case not found");
    }

    public void deleteTestCase(int projectID, String name) {
        post((uriDelTestCase + getTestCaseID(projectID, name)), "");
    }*/

}
