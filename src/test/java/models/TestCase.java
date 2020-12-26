package models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TestCase {
    String title;
    String type;
    String priority;
    String estimate;
    String references;
    String automationType;
    String preconditions;
    String steps;
    String expectedResult;

}
