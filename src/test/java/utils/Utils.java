package utils;

import models.TestCase;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.NoSuchElementException;

public class Utils {

    public static String parseStr(String s, String name) {
        String[] text = s.split("\\n");
        for (int i = 0; i < text.length; i++) {
            if (!text[i].equals(name)) {
                return text[i];
            }
        }
        throw new NoSuchElementException("Error");
    }

    public static TestCase parseTestCase(TestCase testCase) {
        if (testCase.getEstimate().equals("")) {
            testCase.setEstimate("None");
        }

        if (testCase.getReferences().equals("")) {
            testCase.setReferences("None");
        }

        return testCase;
    }

    public static int numberOfIssuesByName(List<WebElement> locators, String name) {

        int count = 0;

        for (WebElement locator : locators) {
            if (locator.getText().equals(name)) {
                count++;
            }
        }
        return count;
    }
}
