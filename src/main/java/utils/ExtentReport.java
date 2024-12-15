package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter; // Use ExtentSparkReporter
import java.io.File;

public class ExtentReport {

    public static ExtentReports extentreport = null;
    public static ExtentTest extentlog;

    // Initialize the ExtentReports object
    public static void initialize(String path) {
        if (extentreport == null) {
            // Create an ExtentReports instance
            extentreport = new ExtentReports();

            // Create an ExtentSparkReporter and attach it to the ExtentReports instance
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(path);
            extentreport.attachReporter(sparkReporter);

            // Add system information
            extentreport.setSystemInfo("Host Name", System.getProperty("user.name"));
            extentreport.setSystemInfo("Environment", "QA");

            sparkReporter.config().setReportName("Automation Test Report");
            sparkReporter.config().setDocumentTitle("Test Execution Report");

            // Load the configuration file (if it exists)
            File configFile = new File(System.getProperty("user.dir") + "resources/extent-config.xml");
        }
    }
}
