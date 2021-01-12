package API.modelsAPI;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestsAPI {

    int id;
    @SerializedName("case_id")
    int caseId;
    @SerializedName("status_id")
    int statusId;
}
