package test.basetest;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import managers.TestContextManager;

public class baseTwo {
	
//  @Parameters({ "os", "browser" })String os, String browser
    @BeforeMethod
    public void setUp() throws Exception {
    	System.out.println("SetUp Started...............");
    	String browser="chrome";
        switch (browser.toLowerCase()) {

            case "chrome":
            	TestContextManager.getContext().setDriver(new ChromeDriver());
            	System.out.println("browser ="+browser);
                break;

            case "firefox":
            	TestContextManager.getContext().setDriver(new FirefoxDriver());
            	System.out.println("browser ="+browser);
                break;

            case "edge":
            	TestContextManager.getContext().setDriver(new EdgeDriver());
            	System.out.println("browser ="+browser);
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        System.out.println("Driver Started → " + browser);

        
    }


    // ==============================
    // AFTER CLASS → CLEANUP
    // ==============================
    @AfterMethod
    public void tearDown() {
    	TestContextManager.getContext().getDriver().quit();
    	TestContextManager.clear();
        System.out.println("Driver Closed.");
    }
    
    // ==============================
    // SCREENSHOT UTILITY
    // ==============================
    public static String captureScreen(String tname) throws IOException {
    	WebDriver driver=TestContextManager.getContext().getDriver();
        String timestamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String targetPath = System.getProperty("user.dir")
                + "\\screenshots\\" + tname + "_" + timestamp + ".png";

        File target = new File(targetPath);
        src.renameTo(target);

        return targetPath;
    }

}
