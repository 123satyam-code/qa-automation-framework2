package managers;

import context.TestContext;

public class TestContextManager {

    private static ThreadLocal<TestContext> testContext =
            ThreadLocal.withInitial(TestContext::new);

    private TestContextManager() {}

    public static TestContext getContext() {
        return testContext.get();
    }

    public static void clear() {
        testContext.remove();
    }
}
