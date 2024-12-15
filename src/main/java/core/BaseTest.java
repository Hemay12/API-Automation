package core;

import helper.BaseTestHelper;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import com.aventstack.extentreports.Status;  // Import Status enum
import org.testng.annotations.BeforeSuite;
import utils.ExtentReport;

import java.io.IOException;

public class BaseTest {

    @BeforeSuite(alwaysRun = true)
    public void config() throws IOException {

        //Create the path in which we will create folder to keep html reports
        String subfolderpath = System.getProperty("user.dir") + "/htmlReports/" + BaseTestHelper.Timestamp();
        //create sub folder
        BaseTestHelper.CreateFolder(subfolderpath);

        ExtentReport.initialize(subfolderpath + "/" + "API_Automation.html");
    }


    @AfterMethod(alwaysRun = true)
    public void getResult(ITestResult result) {
        // Check test result status and log accordingly
        if (result.getStatus() == ITestResult.SUCCESS) {
            ExtentReport.extentlog.log(Status.PASS, "Test Case : " + result.getName() + " is passed ");

        } else if (result.getStatus() == ITestResult.FAILURE) {
            ExtentReport.extentlog.log(Status.FAIL, "Test case : " + result.getName() + " is failed ");
            ExtentReport.extentlog.log(Status.FAIL, "Test case is failed due to: " + result.getThrowable());

        } else if (result.getStatus() == ITestResult.SKIP) {
            ExtentReport.extentlog.log(Status.SKIP, "Test case is skipped " + result.getName());
        }
    }

    @AfterSuite(alwaysRun = true)
    public void endReport() {
        // Flush the report to write all logs and data to the file
        ExtentReport.extentreport.flush();  // This will save the report

    }
}
