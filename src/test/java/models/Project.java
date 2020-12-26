package models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {
    String nameOfProject;
    String announcement;
    boolean showAnnouncement;
    String radio;
}
