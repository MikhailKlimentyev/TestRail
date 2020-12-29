package API.adapters;

import API.modelsAPI.TestSuitesAPI;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.NoSuchElementException;

public class TestSuitAdapter extends BaseAdapter {

    String uriGetAllTestsSuites = "get_suites/";
    //String uriAddTestRun = "add_run/:project_id";
    // String uriDelTestCase = "delete_case/";

   /* public void addTestRun(int projectID) {
        post((uriDelTestCase + projectID), "");
    }*/

    public List<TestSuitesAPI> getAllTestSuites(int projectID) {
        return
                converter.fromJson(get(uriGetAllTestsSuites + projectID), new TypeToken<List<TestSuitesAPI>>() {
                }.getType());
    }

    public int getTestSuiteID(int projectID, String name) {

        List<TestSuitesAPI> testSuites = getAllTestSuites(projectID);

        for (TestSuitesAPI testSuite : testSuites) {
            if (testSuite.getName().equals(name)) {
                return testSuite.getId();
            }
        }
        throw new NoSuchElementException("Test Suite not found");
    }

   /* public void deleteTestCase(int projectID, String name) {
        post((uriDelTestCase + getTestCaseID(projectID, name)), "");
    }*/
}
