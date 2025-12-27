package webUtil;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SeleniumActions {

    private static WebDriverWait getWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(15));
    }
    
    public static void acceptAlert(WebDriver driver) {

       try {
    	   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

           // wait until alert is present
           Alert alert = wait.until(ExpectedConditions.alertIsPresent());

           // accept alert
           alert.accept();
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println("No Alert present");
	}
    }
    
    /* ================== LAUNCH URL & WAIT FOR PAGE LOAD ================== */
    public static void launchUrl(WebDriver driver, String url) {
        try {
            driver.get(url);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(webDriver ->
                    ((JavascriptExecutor) webDriver)
                            .executeScript("return document.readyState")
                            .equals("complete")
            );
        } catch (Exception e) {
            Assert.fail("Failed to launch URL or page not loaded: " + url + " | " + e.getMessage());
        }
    }
    
    /* ================== RETRY CLICK ================== */
    public static void retryClick(WebDriver driver, By locator, int retryCount) {
        Exception lastException = null;

        for (int i = 1; i <= retryCount; i++) {
            try {
                WebElement element =
                        getWait(driver).until(ExpectedConditions.elementToBeClickable(locator));
                highlight(driver, element);
                element.click();
                return; // success → exit
            } catch (Exception e) {
                lastException = e;
            }
        }

        Assert.fail("Click failed after " + retryCount +
                " retries for element: " + locator + " | " +
                lastException.getMessage());
    }
    
    /* ================== JS FALLBACK CLICK ================== */
    public static void jsClick(WebDriver driver, By locator) {
        try {
            WebElement element = findElement(driver, locator);
            highlight(driver, element);

            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", element);

        } catch (Exception e) {
            Assert.fail("JS click failed for element: " + locator + " | " + e.getMessage());
        }
    }

    
    
    /* ================== LAUNCH URL WITH PAGE LOAD TIMEOUT ================== */
    public static void launchUrlWithTimeout(WebDriver driver, String url, int timeoutInSeconds) {
        try {
            driver.get(url);

            new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                    .until(webDriver ->
                            ((JavascriptExecutor) webDriver)
                                    .executeScript("return document.readyState")
                                    .equals("complete"));

        } catch (TimeoutException e) {
            Assert.fail("Page load timeout after " + timeoutInSeconds + " seconds for URL: " + url);
        } catch (Exception e) {
            Assert.fail("Failed to launch URL: " + url + " | " + e.getMessage());
        }
    }


    /* ================== ENTER VALUE ================== */
    public static void enterValue(WebDriver driver, By locator, String value) {
        try {
            WebElement element =
                    getWait(driver).until(ExpectedConditions.visibilityOfElementLocated(locator));

            highlight(driver, element);
            element.clear();
            element.sendKeys(value);

        } catch (Exception e) {
            Assert.fail("Unable to enter value '" + value +
                    "' into element: " + locator + " | " + e.getMessage());
        }
    }

    /* ================== FIND ELEMENT ================== */
    public static WebElement findElement(WebDriver driver, By locator) {
        try {
            return getWait(driver)
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            Assert.fail("Element not found: " + locator + " | " + e.getMessage());
            return null;
        }
    }

    /* ================== FIND ELEMENTS ================== */
    public static List<WebElement> findElements(WebDriver driver, By locator) {
        try {
            getWait(driver)
                    .until(ExpectedConditions.presenceOfElementLocated(locator));
            return driver.findElements(locator);
        } catch (Exception e) {
            Assert.fail("Elements not found: " + locator + " | " + e.getMessage());
            return null;
        }
    }

    /* ================== CLICK ================== */
    public static void click(WebDriver driver, By locator) {
        try {
            WebElement element =
                    getWait(driver).until(ExpectedConditions.elementToBeClickable(locator));
            highlight(driver, element);
            element.click();
        } catch (Exception e) {
            Assert.fail("Unable to click element: " + locator + " | " + e.getMessage());
        }
    }

    /* ================== WAIT FOR VISIBILITY ================== */
    public static void waitForElementVisible(WebDriver driver, By locator) {
        try {
            getWait(driver)
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            Assert.fail("Element not visible: " + locator + " | " + e.getMessage());
        }
    }

    /* ================== SCROLL TO ELEMENT ================== */
    public static void scrollToElement(WebDriver driver, By locator) {
        try {
            WebElement element = findElement(driver, locator);
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView(true);", element);
        } catch (Exception e) {
            Assert.fail("Unable to scroll to element: " + locator + " | " + e.getMessage());
        }
    }

    /* ================== HIGHLIGHT ELEMENT ================== */
    public static void highlight(WebDriver driver, WebElement element) {
        try {
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].style.border='3px solid red'", element);
        } catch (Exception e) {
            // Do nothing – highlighting failure should not fail test
        }
    }

    /* ================== ACTIONS CLICK ================== */
    public static void actionsClick(WebDriver driver, By locator) {
        try {
            WebElement element = findElement(driver, locator);
            new Actions(driver).moveToElement(element).click().perform();
        } catch (Exception e) {
            Assert.fail("Actions click failed: " + locator + " | " + e.getMessage());
        }
    }
}
