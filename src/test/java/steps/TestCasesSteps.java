package steps;

import API.modelsAPI.ProjectAPI;
import io.qameta.allure.Step;
import models.TestCase;
import org.openqa.selenium.WebDriver;
import utils.Utils;

import static org.testng.Assert.assertEquals;

public class TestCasesSteps extends BaseSteps{

    public TestCasesSteps(WebDriver driver){
        super(driver);
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
    public TestCasesSteps createTestCase (TestCase testCase) {
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

        TestCase testCaseFact = new TestCase
                (viewTestCasePage.getTitle(), viewTestCasePage.getType("Type"), viewTestCasePage.getPriority("Priority"),
                viewTestCasePage.getEstimate("Estimate"), viewTestCasePage.getReferences("References"),
                viewTestCasePage.getAutomationType("Automation Type"),
                        viewTestCasePage.getPreconditions(), viewTestCasePage.getSteps(), viewTestCasePage.getExpectedResult());

        Utils.parseTestCase(testCase);

        assertEquals(testCaseFact,testCase);
        return this;
    }

}


