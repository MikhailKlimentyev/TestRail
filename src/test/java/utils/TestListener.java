package utils;

import API.adapters.ProjectAdapter;
import API.adapters.ResultsAdapter;
import API.adapters.TestAdapter;
import API.adapters.TestRunAdapter;
import API.modelsAPI.TestResultAPI;
import API.modelsAPI.TestRunAPI;
import io.qameta.allure.Attachment;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;


public class TestListener implements ITestListener {

    public static int testRailTestRunId;

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println(String.format("===== STARTING TEST %s =====", iTestResult.getName()));
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println(String.format("===== FINISHED TEST %s Duration: %s =====", iTestResult.getName(),
                getExecutionTime(iTestResult)));
        ResultsAdapter resultsAdapter = new ResultsAdapter();
        TestAdapter testAdapter = new TestAdapter();
        TestResultAPI testResultPassed = TestResultAPI.builder()
                .statusID(1)
                .build();

        for(int testCaseID: returnTestCaseID(iTestResult)){
            resultsAdapter.addResult(testAdapter.getTestID(testCaseID,testRailTestRunId),testResultPassed);
        }
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println(String.format("===== FAILED TEST %s Duration: %s =====", iTestResult.getName(),
                getExecutionTime(iTestResult)));
        takeScreenshot(iTestResult);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println(String.format("===== SKIPPING TEST %s =====", iTestResult.getName()));
        takeScreenshot(iTestResult);
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

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
        TestRunAPI testRunAPI = TestRunAPI.builder()
                .name("New")
                .description("test")
                .includeAll(true)
                .build();

        createTestRun("TestRail", testRunAPI);
    }

    @Override
    public void onFinish(ITestContext iTestContext) {

        TestRunAdapter testRunAdapter = new TestRunAdapter();
        testRunAdapter.closeTestRun(testRailTestRunId);
    }

    private long getExecutionTime(ITestResult iTestResult) {
        return TimeUnit.MILLISECONDS.toSeconds(iTestResult.getEndMillis() - iTestResult.getStartMillis());
    }

    protected static void createTestRun(String nameOfProject, TestRunAPI testRunAPI) {
        ProjectAdapter projectAdapter = new ProjectAdapter();
        TestRunAdapter testRunAdapter = new TestRunAdapter();

        testRailTestRunId = testRunAdapter.addTestRun(projectAdapter.getProjectID(nameOfProject), testRunAPI);
    }

    private int[] returnTestCaseID(ITestResult result) {
        ITestNGMethod testNGMethod = result.getMethod();
        Method method = testNGMethod.getConstructorOrMethod().getMethod();
        TestRail testRailAnnotation = method.getAnnotation(TestRail.class);
        int[] testCaseIDs;
        try {
            testCaseIDs = testRailAnnotation.testCaseID();
        } catch (Exception e) {
            return new int[]{};
        }
        return testCaseIDs;
    }

}
