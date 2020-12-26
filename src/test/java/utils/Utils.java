package utils;

import models.TestCase;

import java.util.NoSuchElementException;

public class Utils {

    public static String parseStr(String s, String name) {
        String[] text = s.split("\\n");
        for (int i = 0; i < text.length; i++) {
            if (!text[i].equals(name)){
                return text[i];
            }
        }
        throw new NoSuchElementException("Error");
    }

    public static TestCase parseTestCase(TestCase testCase) {
        if (testCase.getEstimate().equals("")) {
            testCase.setEstimate("None");
        }

        if (testCase.getReferences().equals("")){
            testCase.setReferences("None");
        }

        return testCase;
    }
}
