package utils;

import API.adapters.ProjectAdapter;
import API.adapters.ResultsAdapter;
import API.adapters.TestAdapter;
import API.adapters.TestRunAdapter;
import API.modelsAPI.TestResultAPI;
import API.modelsAPI.TestRunAPI;
import API.modelsAPI.TestRunsAPI;
import io.qameta.allure.Attachment;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Log4j2
public class TestListener implements ITestListener {

    public static int testRailTestRunId;

    private static int createTestRun(String nameOfProject) {
        TestRunAPI testRun = TestRunAPI.builder()
                .name(Utils.generateNameOfTestRun())
                .description("test")
                .includeAll(true)
                .build();

        ProjectAdapter projectAdapter = new ProjectAdapter();
        int projectID = projectAdapter.getProjectID(nameOfProject);

        TestRunAdapter testRunAdapter = new TestRunAdapter();
        return testRunAdapter.addTestRun(projectID, testRun);
    }

    /**
     * Method runs before first test's run.
     * And creates test run for project with name 'TestRail' in Test Rail system
     * <p>
     * If there are no test runs then test first test run is created.
     * Else method checks if existed test runs completed
     * if so another test run is created.
     *
     * @param iTestContext
     */
    @Override
    public void onStart(ITestContext iTestContext) {
        log.debug("onStart");
        if (testRailTestRunId == 0) {
            testRailTestRunId = createTestRun("TestRail");
        } else {
            TestRunAdapter testRunAdapter = new TestRunAdapter();
            TestRunsAPI testRun = testRunAdapter.getTestRunId(testRailTestRunId);
            if (testRun.isCompleted()) {
                testRailTestRunId = createTestRun("TestRail");
            }
        }
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        log.debug("Starting test " + iTestResult.getName());
    }

    /**
     * Matches test cases in 'TestRail' project of TestRail system
     * with being run autotests which annotated with @TestRail() annotation
     * with specified test case number of TestRail system as annotation field.
     * Adds passed result into test cases' runs in TestRail system
     *
     * @param iTestResult
     */
    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        log.debug("Finished test " + iTestResult.getName() + " duration: " + getExecutionTime(iTestResult));
        ResultsAdapter resultsAdapter = new ResultsAdapter();
        TestAdapter testAdapter = new TestAdapter();
        TestResultAPI testResultPassed = TestResultAPI.builder()
                .statusID(1)
                .build();
        int[] testCaseIds = getTestCaseIdMatchedWithTestMethod(iTestResult);
        int testID;
        for (int testCaseID : testCaseIds) {
            testID = testAdapter.getTestID(testCaseID, testRailTestRunId);
            resultsAdapter.addResult(testID, testResultPassed);
        }
    }

    /**
     * Matches test cases in 'TestRail' project of TestRail system
     * with being run autotests which annotated with @TestRail() annotation
     * with specified test case number of TestRail system as annotation field.
     * Adds failed result into test cases' runs in TestRail system
     *
     * @param iTestResult
     */
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        log.debug("Failed test " + iTestResult.getName() + " duration: " + getExecutionTime(iTestResult));

        takeScreenshot(iTestResult);

        String errorMessage = iTestResult.getThrowable().toString();
        ITestNGMethod testNGMethod = iTestResult.getMethod();
        String testRailComment = "Test - FAILED\n\nTest method name = " + testNGMethod.getMethodName() + "\n\nFailure Exception = " + errorMessage;

        ResultsAdapter resultsAdapter = new ResultsAdapter();
        TestAdapter testAdapter = new TestAdapter();
        TestResultAPI testResultFailed = TestResultAPI.builder()
                .statusID(5)
                .comment(testRailComment)
                .build();

        for (int testCaseID : getTestCaseIdMatchedWithTestMethod(iTestResult)) {
            resultsAdapter.addResult(testAdapter.getTestID(testCaseID, testRailTestRunId), testResultFailed);
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        log.debug("Skipping test " + iTestResult.getName());
        takeScreenshot(iTestResult);
    }

    /**
     * Method runs after last test's run.
     * Gets test run by id
     * Checks if the amount of tests in the test run marked as untested equals to 0
     * If so closes the test run.
     *
     * @param iTestContext
     */
    @Override
    public void onFinish(ITestContext iTestContext) {
        log.debug("onFinish");
        TestRunAdapter testRunAdapter = new TestRunAdapter();
        TestRunsAPI testRun = testRunAdapter.getTestRunId(testRailTestRunId);
        if (testRun.getUntestedCount() == 0) {
            testRunAdapter.closeTestRun(testRailTestRunId);
        }
    }

    private long getExecutionTime(ITestResult iTestResult) {
        return TimeUnit.MILLISECONDS.toSeconds(iTestResult.getEndMillis() - iTestResult.getStartMillis());
    }

    /**
     * Get test case id matched with test method.
     * <p>
     * Retrieve numbers supposed to be test cases numbers in project with name 'TestRail' inside TestRail system.
     * These numbers are specified inside @TestRail() annotation.
     * Test method annotated with @TestRail() annotation is retrieved from ITestResult result.
     *
     * @param result the ITestResult result
     * @return the int [] of numbers supposed to be test cases numbers in TestRail system
     */
    private int[] getTestCaseIdMatchedWithTestMethod(ITestResult result) {
        ITestNGMethod testNGMethod = result.getMethod();
        Method method = testNGMethod.getConstructorOrMethod().getMethod();
        TestRail testRailAnnotation = method.getAnnotation(TestRail.class);
        int[] testCaseIDs = new int[]{};
        try {
            testCaseIDs = testRailAnnotation.testCaseID();
        } catch (Exception e) {
            log.error(String.format("Exception occurs %s on attempt to getTestCaseID with result %s",
                    Arrays.toString(e.getStackTrace()), result));
        }
        return testCaseIDs;
    }

    @Attachment(value = "Last screen state", type = "image/png")
    private byte[] takeScreenshot(ITestResult iTestResult) {
        ITestContext context = iTestResult.getTestContext();
        try {
            WebDriver driver = (WebDriver) context.getAttribute("driver");
            if (driver != null) {
                return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            } else {
                return new byte[]{};
            }
        } catch (NoSuchSessionException | IllegalStateException ex) {
            return new byte[]{};
        }
    }
}
