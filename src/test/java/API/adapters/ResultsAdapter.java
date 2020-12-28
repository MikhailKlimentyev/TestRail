package API.adapters;

import API.modelsAPI.TestResultAPI;
import API.modelsAPI.TestRunAPI;

public class ResultsAdapter extends BaseAdapter{

    String uriAddResult = "add_result/";

    public void addResult(int testID, TestResultAPI testResultAPI) {
                post(uriAddResult + testID, converter.toJson(testResultAPI));
    }
}
