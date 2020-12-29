package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Project {
    String nameOfProject;
    String announcement;
    boolean showAnnouncement;
    String radio;
}
