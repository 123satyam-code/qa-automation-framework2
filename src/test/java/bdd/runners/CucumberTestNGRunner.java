package bdd.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {
                "bdd.stepDefinitions",
                "bdd.hooks",
                "context"
        },
        plugin = {
                "pretty",
                "util.listeners.CucumberExecutionListener",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "html:target/cucumber-report.html",
                "json:target/cucumber-report.json"
        },
        		// tags = "@smoke",
        monochrome = true
        
)
public class CucumberTestNGRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
    
    // if you keep parallel = false scenarios will execute in sequence

    
   /*
    
    @Parameters({"browser"})
    @BeforeClass(alwaysRun = true)
    public void setUp(String browser) {
        System.setProperty("browser", browser);
    }
    
    */
}
