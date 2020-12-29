package API.modelsAPI;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestRunAPI {
    // @SerializedName("suite_id")
    // int suiteID;
    String name;
    String description;
    @SerializedName("include_all")
    boolean includeAll;
    //@SerializedName("case_ids")
    //int [] caseIDs;
}
