package context;

import managers.PageObjectManager;
import org.openqa.selenium.WebDriver;

public class TestContext {

    private WebDriver driver;
    private ScenarioContext scenarioContext;
    private PageObjectManager pageObjectManager;
    private String browser;

    public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public TestContext() {
        scenarioContext = new ScenarioContext();
        this.pageObjectManager = new PageObjectManager();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
        
    }

    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }

    public PageObjectManager getPageObjectManager() {
        return pageObjectManager;
    }
}
