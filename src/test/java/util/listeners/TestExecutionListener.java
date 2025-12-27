package util.listeners;

import io.qameta.allure.Attachment;
import managers.TestContextManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;

public class TestExecutionListener implements ITestListener, ISuiteListener {

    // ================= SUITE =================
    @Override
    public void onStart(ISuite suite) {
        System.out.println("========== SUITE STARTED : " + suite.getName() + " ==========");
    }

    @Override
    public void onFinish(ISuite suite) {
        System.out.println("========== SUITE FINISHED : " + suite.getName() + " ==========");
    }

    // ================= TEST =================
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("▶ TEST STARTED : " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("✔ TEST PASSED : " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("✖ TEST FAILED : " + result.getMethod().getMethodName());
        System.out.println("REASON : " + result.getThrowable());

        WebDriver driver = TestContextManager.getContext().getDriver();

        if (driver != null) {
            attachScreenshot(driver);
        } else {
            System.out.println("⚠ Driver is NULL. Screenshot skipped.");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("⚠ TEST SKIPPED : " + result.getMethod().getMethodName());
    }

    // ================= ALLURE =================
    @Attachment(value = "Failure Screenshot", type = "image/png")
    public byte[] attachScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES);
    }
}
