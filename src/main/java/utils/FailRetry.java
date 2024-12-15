package utils;
//https://automationreinvented.blogspot.com/2020/08/how-to-rerun-only-failed-test-cases.html
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class FailRetry implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int maxRetryCount = 2; // How many times you want to retry

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            System.out.println("Retrying test: " + result.getName() + " | Attempt: " + (retryCount + 1));
            return true;
        }
        return false;
    }
}
