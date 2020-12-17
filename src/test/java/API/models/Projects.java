package API.models;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
public class Projects {
    ArrayList<Project> projects;
}
