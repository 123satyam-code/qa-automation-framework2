package pageObjects;

import org.openqa.selenium.By;

public class DashBoard {
	
	public By adminBtn=By.xpath("//a[.//span[normalize-space()='Admin']]");
	
	public By addbtn=By.xpath("(//*[@class='orangehrm-paper-container']//button)[1]");
		
	public By userRole=By.xpath("//*[@class='oxd-form']//*[*[*[contains(text(),'User Role')]]]//i");
	
	public By getOption(String option) {
		return By.xpath("//*[@role='option' and *[contains(text(),'"+option+"')]]");
	}
	
	public By status=By.xpath("//*[@class='oxd-form']//*[*[*[contains(text(),'Status')]]]//i");
	
	public By pass=By.xpath("(//*[@class='oxd-form']//*[*[*[contains(text(),'Password')]]]//input)[1]");
	
	public By confPass=By.xpath("(//*[@class='oxd-form']//*[*[*[contains(text(),'Confirm Password')]]]//input)[1]");
	
	public By userName=By.xpath("(//*[@class='oxd-form']//*[*[*[contains(text(),'Username')]]]//input)[1]");
	
	public By employeeName=By.xpath("(//*[@class='oxd-form']//*[*[*[contains(text(),'Employee Name')]]]//input)[1]");
	
	public By saveBtn=By.xpath("//button[@type='submit']");
	
	

}
