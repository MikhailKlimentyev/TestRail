package API.models;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Project {
    String name;
    String announcement;
    @SerializedName("show_announcement")
    boolean showAnnouncement;
    @SerializedName("suite_mode")
    int suiteMode;
}
