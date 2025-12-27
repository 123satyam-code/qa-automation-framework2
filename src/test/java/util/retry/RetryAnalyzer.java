package util.retry;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int MAX_RETRY = 1;

    @Override
    public boolean retry(ITestResult result) {

        Throwable throwable = result.getThrowable();
        if (throwable == null) {
            return false;
        }

        // ❌ Skip retry if failure originated from validations package
        if (isValidationFailure(throwable)) {
            System.out.println("❌ No retry (validation failure)");
            return false;
        }

        // ✅ Retry only for non-validation failures
        if (retryCount < MAX_RETRY) {
            retryCount++;
            System.out.println("🔁 Retrying test: "
                    + result.getMethod().getMethodName()
                    + " | Retry count: " + retryCount);
            return true;
        }

        return false;
    }

    // =========================
    // Helper method
    // =========================
    private boolean isValidationFailure(Throwable throwable) {
        for (StackTraceElement element : throwable.getStackTrace()) {
            String className = element.getClassName();

            // matches: util.validations.*
            if (className.contains("util.validations.")) {
                return true;
            }
        }
        return false;
    }
}
