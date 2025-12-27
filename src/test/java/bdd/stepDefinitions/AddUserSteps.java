package bdd.stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import managers.TestContextManager;
import pageObjects.DashBoard;
import webUtil.SeleniumActions;

public class AddUserSteps {
	private WebDriver driver;
	private DashBoard dashboard;
	
	public AddUserSteps() {
		driver=TestContextManager.getContext().getDriver();
		dashboard=TestContextManager.getContext().getPageObjectManager().getDashBoard();
	}
	
	
	  @When("user navigates to Admin and Add User page")
	    public void user_navigates_to_admin_and_add_user_page() {
	        SeleniumActions.click(driver, dashboard.adminBtn);
	        SeleniumActions.click(driver, dashboard.addbtn);
	    }

	    @And("user selects User Role as {string}")
	    public void user_selects_user_role(String role) {
	        SeleniumActions.click(driver, dashboard.userRole);
	        SeleniumActions.click(driver, dashboard.getOption(role));
	    }

	    @And("user enters Employee Name as {string}")
	    public void user_enters_employee_name(String empName) {
	        SeleniumActions.enterValue(driver, dashboard.employeeName, empName);
	    }

	    @And("user selects Status as {string}")
	    public void user_selects_status(String status) {
	        SeleniumActions.click(driver, dashboard.status);
	        SeleniumActions.click(driver, dashboard.getOption(status));
	    }

	    @And("user enters Username as {string}")
	    public void user_enters_username(String username) {
	        SeleniumActions.enterValue(driver, dashboard.userName, username);
	    }

	    @And("user enters Password as {string}")
	    public void user_enters_password(String password) {
	        SeleniumActions.enterValue(driver, dashboard.pass, password);
	    }

	    @And("user confirms Password as {string}")
	    public void user_confirms_password(String confirmPassword) {
	        SeleniumActions.enterValue(driver, dashboard.confPass, confirmPassword);
	    }

	    @And("user clicks on Save button")
	    public void user_clicks_on_save_button() {
	        SeleniumActions.click(driver, dashboard.saveBtn);
	    }
	    
	    @Then("user should see user created successfully message")
	    public void user_should_see_user_created_successfully_message() {
//	        // Example – update locator as per app
//	        By successMsg = By.xpath("//*[contains(text(),'Successfully Saved')]");
//	        SeleniumActions.waitForElementVisible(driver, successMsg);
	    	Assert.assertTrue(false);
	    }

}
