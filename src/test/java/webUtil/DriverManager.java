package webUtil;

import org.openqa.selenium.WebDriver;

public class DriverManager {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverManager() {
        // prevent object creation
    }

    /* ================== SET DRIVER ================== */
    public static void setDriver(WebDriver webDriver) {
        driver.set(webDriver);
    }

    /* ================== GET DRIVER ================== */
    public static WebDriver getDriver() {
        return driver.get();
    }

    /* ================== REMOVE DRIVER ================== */
    public static void removeDriver() {
        driver.remove();
    }
}

