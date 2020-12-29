package API.modelsAPI;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestRunsAPI {
    @SerializedName("untested_count")
    int untestedCount;
    @SerializedName("is_completed")
    boolean isCompleted;
}
