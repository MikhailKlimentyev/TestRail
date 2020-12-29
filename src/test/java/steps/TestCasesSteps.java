package steps;

import API.modelsAPI.ProjectAPI;
import io.qameta.allure.Step;
import models.Project;
import models.TestCase;
import org.openqa.selenium.WebDriver;
import utils.Utils;

import static org.testng.Assert.assertEquals;

public class TestCasesSteps extends BaseSteps {

    public TestCasesSteps(WebDriver driver) {
        super(driver);
    }

    @Step("Open tab TEST CASES")
    public TestCasesSteps openTestCasesPage(Project project) {
        dashboardPage
                .openDashboardPage()
                .isPageOpened()
                .openProject(project.getNameOfProject())
                .clickTabTestCases()
                .isPageOpened();
        return this;
    }

    @Step("Open tab TEST CASES")
    public TestCasesSteps openTestCasesPage(ProjectAPI projectAPI) {
        dashboardPage
                .openDashboardPage()
                .isPageOpened()
                .openProject(projectAPI.getName())
                .clickTabTestCases()
                .isPageOpened();
        return this;
    }

    @Step("Create test case 'testCase.title'")
    public TestCasesSteps createTestCase(TestCase testCase) {
        testCasesPage
                .openNewTestCasePage()
                .isPageOpened()
                .setTitle(testCase.getTitle())
                .setType(testCase.getType())
                .setPriority(testCase.getPriority())
                .setEstimate(testCase.getEstimate())
                .setReferences(testCase.getReferences())
                .setAutomationType(testCase.getAutomationType())
                .setPreconditions(testCase.getPreconditions())
                .setSteps(testCase.getSteps())
                .setExpectedResult(testCase.getExpectedResult())
                .clickButtonAddTestCase()
                .isPageOpened();
        return this;
    }

    @Step("Open page of view test case '{testCase.title}'")
    public TestCasesSteps openViewTestCasePage(TestCase testCase) {
        projectPage
                .clickTabTestCases()
                .isPageOpened()
                .openCreatedTestCase(testCase.getTitle())
                .isPageOpened();
        return this;
    }

    public TestCasesSteps validateTestCase(TestCase testCase) {

        TestCase testCaseFact = TestCase.builder()
                .title(viewTestCasePage.getTitle())
                .type(viewTestCasePage.getType("Type"))
                .priority(viewTestCasePage.getPriority("Priority"))
                .estimate(viewTestCasePage.getEstimate("Estimate"))
                .references(viewTestCasePage.getReferences("References"))
                .automationType(viewTestCasePage.getAutomationType("Automation Type"))
                .preconditions(viewTestCasePage.getPreconditions(testCase.getPreconditions()))
                .steps(viewTestCasePage.getSteps(testCase.getSteps()))
                .expectedResult(viewTestCasePage.getExpectedResult(testCase.getExpectedResult()))
                .build();

        Utils.parseTestCase(testCase);

        assertEquals(testCaseFact, testCase);
        return this;
    }

    public void deleteTestCaseAPI(ProjectAPI projectAPI, TestCase testCase) {
        testCasesAdapter.deleteTestCase(projectAdapter.getProjectID(projectAPI.getName()), testCase.getTitle());
    }

    public void deleteTestCaseAPI(Project project, TestCase testCase) {
        testCasesAdapter.deleteTestCase(projectAdapter.getProjectID(project.getNameOfProject()), testCase.getTitle());
    }

    @Step("Delete test case 'testCase.title'")
    public TestCasesSteps deleteTestCase(TestCase testCase) {
        viewTestCasePage
                .clickButtonEdit()
                .isPageOpened()
                .clickButtonDelete()
                .isModalOpened()
                .clickButtonOk()
                .isPageOpened();
        return this;
    }

    public TestCasesSteps validateIsTestCaseDeleted(TestCase testCase) {
        assertEquals(testCasesPage.numberOfTestCasesByName(testCase.getTitle()), 0);
        return this;
    }

    @Step("Update test case 'testCaseUpdate.title'")
    public TestCasesSteps updateTestCase(TestCase testCaseUpdate) {
        viewTestCasePage
                .clickButtonEdit()
                .isPageOpened()
                .setTitle(testCaseUpdate.getTitle())
                .setType(testCaseUpdate.getType())
                .setPriority(testCaseUpdate.getPriority())
                .setEstimate(testCaseUpdate.getEstimate())
                .setReferences(testCaseUpdate.getReferences())
                .setAutomationType(testCaseUpdate.getAutomationType())
                .setPreconditions(testCaseUpdate.getPreconditions())
                .setSteps(testCaseUpdate.getSteps())
                .setExpectedResult(testCaseUpdate.getExpectedResult())
                .clickButtonAddTestCase()
                .isMessageShown();
        return this;
    }


}


