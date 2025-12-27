package pageObjects;

import org.openqa.selenium.By;

public class HomePage {
	
	public By userName=By.cssSelector("[name='username']");
	public By password=By.cssSelector("[name='password']");
	public By submitBtn=By.cssSelector("button[type='submit']");
	
	public By sucessLogin=By.xpath("//*[@class='oxd-topbar-header']//span//h6");
	public By loginErr=By.xpath("//*[@class='orangehrm-login-error']");
	
	
	
	
	

}
