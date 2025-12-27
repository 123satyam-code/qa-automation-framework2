package managers;

import org.openqa.selenium.WebDriver;

import pageObjects.DashBoard;
import pageObjects.HomePage;

public class PageObjectManager {

    private HomePage homePage;
    private DashBoard dashBoard;

    public PageObjectManager() {
        
    }
    
    

    public DashBoard getDashBoard() {
    	 if (dashBoard == null) {
    		 dashBoard = new DashBoard();
         }
		 return dashBoard;
	}


	public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }
}
