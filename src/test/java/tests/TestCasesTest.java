package tests;

import API.modelsAPI.ProjectAPI;
import models.TestCase;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestCasesTest extends Authorization {

    ProjectAPI newProjectAPI = ProjectAPI.builder()
            .name("Project For Test Test Cases")
            .announcement("project fo test create project")
            .showAnnouncement(true)
            .suiteMode(1)
            .build();

    TestCase testCase1 = new TestCase("First Test Case", "Functional", "Critical", "2 hours",
            "link for wiki", "None",
            "Authorize in APP",
            "Go to link",
            "Page is added");
    TestCase testCase2 = new TestCase("Second Test Case", "Regression", "High", "1 hour",
            "link for wiki", "Ranorex",
            "Authorize in APP",
            "Go to APP",
            "Page is added");
    TestCase testCase3 = new TestCase("Third Test Case", "Acceptance", "Medium", "30 minutes",
            "link for wiki", "None",
            "Precondition",
            "Click button",
            "Page is added");
    TestCase testCase4 = new TestCase("Fourth Test Case", "Usability", "Low", "15 minutes",
            "link for wiki", "Ranorex",
            "Precondition",
            "Log out",
            "Page is added");
    TestCase testCase5 = new TestCase("Fifth Test Case", "Compatibility", "Medium", "4 hours",
            "link for wiki", "Ranorex",
            "Create admin",
            "Authorize",
            "Page is added");

    TestCase testCaseUpdated = new TestCase("Updated Test Case", "Security", "Critical", "7 hours",
            "new link for wiki", "None",
            "UPD Create admin",
            "UPD Click tab",
            "UPD Page is added");

    @BeforeClass
    public void createProject() {
        projectSteps
                .createProjectAPI(newProjectAPI);
    }

    @Test(description = "Create new test case", dataProvider = "Data fo test creating test cases")
    public void isTestCaseCreated(TestCase testCase) {
        testCasesSteps
                .openTestCasesPage(newProjectAPI)
                .createTestCase(testCase)
                .openViewTestCasePage(testCase)
                .validateTestCase(testCase)
                .deleteTestCaseAPI(newProjectAPI, testCase);
    }

    @DataProvider(name = "Data fo test creating test cases")
    public Object[][] dataForTestCreatingTestCases() {
        return new Object[][]{
                {testCase1},
                {testCase2},
                {testCase3},
                {testCase4},
                {testCase5}
        };
    }

    @Test(description = "Delete test case")
    public void isTestCaseDeleted() {
        testCasesSteps
                .openTestCasesPage(newProjectAPI)
                .createTestCase(testCase1)
                .openViewTestCasePage(testCase1)
                .deleteTestCase(testCase1)
                .validateIsTestCaseDeleted(testCase1);
    }

    @Test(description = "Update test case")
    public void isTestCaseUpdated() {
        testCasesSteps
                .openTestCasesPage(newProjectAPI)
                .createTestCase(testCase1)
                .openViewTestCasePage(testCase1)
                .updateTestCase(testCaseUpdated)
                .validateTestCase(testCaseUpdated)
                .deleteTestCaseAPI(newProjectAPI, testCaseUpdated);
    }

    @AfterClass
    public void deleteProject() {
        projectSteps
                .deleteProjectAPI(newProjectAPI);
    }

}
