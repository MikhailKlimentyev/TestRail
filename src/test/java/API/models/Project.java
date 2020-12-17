package API.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Project {
    String name;
    String announcement;
    boolean show_announcement;
    int suite_mode;
}
