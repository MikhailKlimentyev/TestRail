package API.modelsAPI;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestResultAPI {

    @SerializedName("status_id")
    int statusID;
    String comment;
}
