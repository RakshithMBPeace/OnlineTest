package listeners.extent;

import Core.TestBase;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ExtentReportListener extends TestBase implements ITestListener {

    protected static WebDriver driver;
    protected static ExtentReports reports;
    protected static ExtentTest test;
    public void onTestStart(ITestResult result) {
        System.out.println("on test start");
        test = reports.startTest(result.getMethod().getMethodName());
        test.log(LogStatus.INFO, result.getMethod().getMethodName() + "test is started");
    }
    public void onTestSuccess(ITestResult result) {
        System.out.println("on test success");
        test.log(LogStatus.PASS, result.getMethod().getMethodName() + "test is passed");
    }
    public void onTestFailure(ITestResult result) {
        System.out.println("on test failure");
        test.log(LogStatus.FAIL, result.getMethod().getMethodName() + "test is failed");
}
    public void onTestSkipped(ITestResult result) {
        System.out.println("on test skipped");
        test.log(LogStatus.SKIP, result.getMethod().getMethodName() + "test is skipped");
    }
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("on test sucess within percentage");
    }
    public void onStart(ITestContext context) {
        System.out.println("on start");
        driver = getDriver(); // Set the drivers path in environment variables to avoid code(System.setProperty())
        reports = new ExtentReports(new SimpleDateFormat("yyyy-MM-dd hh-mm-ss-ms").format(new Date()) + "reports.html");
    }
    public void onFinish(ITestContext context) {
        System.out.println("on finish");
        reports.endTest(test);
        reports.flush();
    }
}