package bdd.stepDefinitions;

import org.openqa.selenium.WebDriver;

import context.ScenarioContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import managers.TestContextManager;
import pageObjects.DashBoard;
import pageObjects.HomePage;
import webUtil.SeleniumActions;

public class LoginSteps {

	private WebDriver driver;
	private HomePage homePage;
	private DashBoard dashBoard;
	private ScenarioContext scenarioContext;

	public LoginSteps() {
		this.driver = TestContextManager.getContext().getDriver();
		this.homePage = TestContextManager.getContext().getPageObjectManager().getHomePage();
		this.dashBoard=TestContextManager.getContext().getPageObjectManager().getDashBoard();
		this.scenarioContext=TestContextManager.getContext().getScenarioContext();
	}

	@Given("user is on login page")
	public void user_is_on_login_page() {
		SeleniumActions.launchUrl(driver, "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	}

	@When("user enters username {string}")
	public void user_enters_username(String username) {
		SeleniumActions.enterValue(driver, homePage.userName, username);
	}

	@When("user enters password {string}")
	public void user_enters_password(String password) {
		// SeleniumActions.findElement(driver, homePage.password).sendKeys(password);
		SeleniumActions.enterValue(driver, homePage.password, password);
	}

	@When("user clicks on login button")
	public void user_clicks_on_login_button() {
		SeleniumActions.click(driver, homePage.submitBtn);
	}

	@Then("user should be logged in successfully")
	public void user_should_be_logged_in_successfully() {
		// Example validation (adjust as per your app)
		// You can check URL, title, dashboard element, etc.
		// Keeping it simple for now
		SeleniumActions.acceptAlert(driver);
		SeleniumActions.findElement(driver, homePage.sucessLogin);
	}
	
	@Then("user should not login successfully")
	public void invalidLogin() {
		SeleniumActions.acceptAlert(driver);
		SeleniumActions.findElement(driver, homePage.loginErr);
	}
}
