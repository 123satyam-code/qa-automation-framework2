package util.listeners;

import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.*;
import io.qameta.allure.Attachment;
import managers.TestContextManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CucumberExecutionListener implements EventListener {

    @Override
    public void setEventPublisher(EventPublisher publisher) {

        publisher.registerHandlerFor(TestRunStarted.class, this::onTestRunStarted);
        publisher.registerHandlerFor(TestCaseStarted.class, this::onScenarioStarted);
        publisher.registerHandlerFor(TestCaseFinished.class, this::onScenarioFinished);
        publisher.registerHandlerFor(TestRunFinished.class, this::onTestRunFinished);
    }

    // ================= RUN =================
    private void onTestRunStarted(TestRunStarted event) {
        System.out.println("========== CUCUMBER TEST RUN STARTED ==========");
    }

    private void onTestRunFinished(TestRunFinished event) {
        System.out.println("========== CUCUMBER TEST RUN FINISHED ==========");
    }

    // ================= SCENARIO =================
    private void onScenarioStarted(TestCaseStarted event) {
        System.out.println("▶ SCENARIO STARTED : " +
                event.getTestCase().getName());
    }

    private void onScenarioFinished(TestCaseFinished event) {

        Status status = event.getResult().getStatus();
        String scenarioName = event.getTestCase().getName();

        System.out.println("▶ SCENARIO FINISHED : " + scenarioName +
                " | STATUS : " + status);

        if (status == Status.FAILED) {
            WebDriver driver = TestContextManager.getContext().getDriver();

            if (driver != null) {
                attachScreenshot(driver);
            } else {
                System.out.println("⚠ Driver is NULL. Screenshot skipped.");
            }
        }
    }

    // ================= ALLURE =================
    @Attachment(value = "Scenario Failure Screenshot", type = "image/png")
    public byte[] attachScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES);
    }
}
