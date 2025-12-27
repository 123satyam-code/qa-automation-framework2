package bdd.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import managers.TestContextManager;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.io.ByteArrayInputStream;  // ⭐ REQUIRED

public class Hooks {

    /*@Before
    public void setUp() {
    	 String browser = TestContextManager.getContext().getBrowser();
    	    WebDriver driver;

    	    if ("chrome".equalsIgnoreCase(browser)) {
    	        driver = new ChromeDriver();
    	    } else if ("edge".equalsIgnoreCase(browser)) {
    	        driver = new EdgeDriver();
    	    } else {
    	        throw new RuntimeException("Unsupported browser: " + browser);
    	    }

    	    driver.manage().window().maximize();
    	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

    	    TestContextManager.getContext().setDriver(driver);
    }*/
	
	@Before
	public void setUp() {

	    String browser = System.getProperty("browser");
	    TestContextManager.getContext().setBrowser(browser);
	    ChromeOptions options = new ChromeOptions();
	    Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);
	    WebDriver driver=new ChromeDriver();  
	    driver.manage().window().maximize();
	    TestContextManager.getContext().setDriver(driver);
	}


	@After
	public void tearDown(Scenario scenario) {

	    WebDriver driver = TestContextManager.getContext().getDriver();

	    if (scenario.isFailed() && driver != null) {

	        byte[] screenshot =
	                ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

	        Allure.addAttachment(
	                "Failure Screenshot",
	                new ByteArrayInputStream(screenshot)
	        );
	    }

	    if (driver != null) {
	        driver.quit();
	    }

	    TestContextManager.clear(); 
	}
}

/*
 “In a Cucumber-TestNG framework, screenshots on failure are captured inside a Cucumber @After hook.
 We check scenario.isFailed(), take the screenshot using WebDriver, and attach it to the Cucumber 
 report using scenario.attach(). This approach is parallel-safe and follows best practices.”
 */
 